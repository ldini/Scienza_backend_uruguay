package ar.com.scienza.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.FreeMarkerConfig;
import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.ProcedureCreateRequestDto;
import ar.com.scienza.dto.ProcedureCreateResponseDto;
import ar.com.scienza.dto.ProcedureResponseDto;
import ar.com.scienza.dto.ProcedureResultResponseDto;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Email;
import ar.com.scienza.entity.Tramite;
import ar.com.scienza.entity.TramiteArchivo;
import ar.com.scienza.enumerator.TipoEmailEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.mailsender.MailSender;
import ar.com.scienza.remote.json.dto.AffiliateDataEmailRequestDto;
import ar.com.scienza.repository.EmailRepository;
import ar.com.scienza.repository.TramiteArchivoRepository;
import ar.com.scienza.repository.TramiteRepository;
import ar.com.scienza.service.IProcedureService;
import ar.com.scienza.transformer.TramiteTRF;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
@Transactional(rollbackFor = ServiceException.class)
public class ProcedureService extends FileService implements IProcedureService {

    @Autowired
    private TramiteRepository tramiteRepository;

    @Autowired
    private TramiteArchivoRepository tramiteArchivoRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Autowired
    private EmailRepository emailRepository;

    @Value("${mail.environment}")
    String mailEnvironment;


    @Override
    public ProcedureResponseDto getProcedure(Integer tramiteId, String token, Long sapId, String deviceType) throws ServiceException {

        ProcedureResponseDto response = new ProcedureResponseDto();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Afiliado afiliado = getUserWithSession(token, sapId);

            Tramite tramite = tramiteRepository.findOneByIdAndAfiliadoId(tramiteId, afiliado.getId());
            if (tramite == null) {
                throw new Exception();
            }

            response.setTramiteId(tramite.getId());
            response.setFechaTramite(sdf.format(tramite.getFechaInsert()));
            response.setComentario(tramite.getDescripcion());

            List<String> pathArchivo = new ArrayList<>();

            for (TramiteArchivo tramiteArc : tramite.getTramiteArchivoList()) {
                pathArchivo.add(buildResourceUrl(tramiteArc.getFilename()));
            }

            response.setFileList(pathArchivo);
        } catch (Exception e) {
            ScienzaLogger.logError(e);
            throw new ServiceException("Error al obtener la solicitud del paciente");
        }

        return response;
    }


    @Override
    public List<ProcedureResultResponseDto> getProcedureList(String token, Long sapId, String deviceType) throws ServiceException {

        List<ProcedureResultResponseDto> responseList = new ArrayList<ProcedureResultResponseDto>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            Afiliado afiliado = getUserWithSession(token, sapId);

            List<TramiteTRF> tramiteTRFList = tramiteRepository.findAllTRFByAfiliadoId(afiliado.getId());

            for (TramiteTRF tramiteTRF : tramiteTRFList) {
                Tramite tramite = tramiteRepository.findOne(tramiteTRF.getId());

                ProcedureResultResponseDto response = new ProcedureResultResponseDto();

                response.setTramiteId(tramite.getId());
                response.setComentario(tramite.getDescripcion());
                response.setFechaTramite(sdf.format(tramite.getFechaInsert()));
                response.setCantidadArchivos(tramite.getTramiteArchivoList().size());

                responseList.add(response);

            }


        } catch (Exception e) {
            ScienzaLogger.logError(e);
            throw new ServiceException("Error al obtener la lista de solicitudes");
        }


        return responseList;
    }


    @Override
    public ProcedureCreateResponseDto createProcedure(ProcedureCreateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

        ProcedureCreateResponseDto response = new ProcedureCreateResponseDto();

        try {
            Afiliado afiliado = getUserWithSession(token, sapId);

            Tramite tramite = new Tramite();
            tramite.setAfiliado(afiliado);
            tramite.setDescripcion(request.getDescripcion());
            tramite.setFechaInsert(new Date());
            tramite.createOnRepository(tramiteRepository);

            List<File> files = parseFiles("SOLIC-" + tramite.getId() + "_IMAGEN_", request.getImagenes());

            List<File> sendFiles = new ArrayList<>();

            Integer i = 0 ;

            if (!files.isEmpty()) {
                String pdfImagesFile = "SOLIC-" + tramite.getId() + "-" + i + ".pdf";
                sendFiles.add(createImagePdf(pdfImagesFile, files));

                // Guarda un archivo PDF con las imágenes
                TramiteArchivo tramArc = new TramiteArchivo();
                tramArc.setTramite(tramite);
                tramArc.setPath(locationFiles + pathPdf + pdfImagesFile);
                tramArc.setFilename(pdfImagesFile);
                tramArc.createOnRepository(tramiteArchivoRepository);
                i++;
            }

            // Pdfs
            for (String base64File : request.getPdfs()) {
                // crea el archivo de cada pdf
                String pdfFile = "SOLIC-" + tramite.getId() + "-" + i + ".pdf";
                sendFiles.add(savePdfFile(base64File, pdfFile));

                // Guarda cada pdf.
                TramiteArchivo tramArchivo = new TramiteArchivo();
                tramArchivo.setTramite(tramite);
                tramArchivo.setPath(locationFiles + pathPdf + pdfFile);
                tramArchivo.setFilename(pdfFile);
                tramArchivo.createOnRepository(tramiteArchivoRepository);
                i++;
            }

            response.setTramiteId(tramite.getId());

            sendProcedureEmail(tramite , sendFiles);

        } catch (Exception e) {
            ScienzaLogger.logError(e);
            throw new ServiceException("Error al crear la solicitud del paciente");
        }

        return response;
    }

    private void sendProcedureEmail(Tramite docu , List<File> archivos) throws IOException, MessagingException, TemplateException {

        Map<Object, Object> root = new HashMap<Object, Object>();

        AffiliateDataEmailRequestDto emailRequestDto = new AffiliateDataEmailRequestDto();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String docNUM = " " + docu.getId();

        emailRequestDto.setCaseType("Solicitud realizada por el paciente");
        emailRequestDto.setMessage(docu.getDescripcion());
        emailRequestDto.setName(docu.getAfiliado().getApellido() + " , " + docu.getAfiliado().getNombre());
        emailRequestDto.setDateCase(sdf.format(docu.getFechaInsert()));
        emailRequestDto.setSapId(docu.getAfiliado().getSapId().toString());
        emailRequestDto.setIdentification(docu.getAfiliado().getcedulaIdentidad());

        if (docu.getAfiliado().getTelefonoCelular() != null){

            emailRequestDto.setCellphone(docu.getAfiliado().getTelefonoCelular());

        }
        else

            emailRequestDto.setCellphone("");

        if(docu.getAfiliado().getTelefonoParticular() != null){

            emailRequestDto.setTelephone(docu.getAfiliado().getTelefonoParticular());
        }
        else
        if(docu.getAfiliado().getTelefonoLaboral() != null) {

            emailRequestDto.setTelephone(docu.getAfiliado().getTelefonoLaboral());
        }

        else

            emailRequestDto.setTelephone("");

        if(docu.getAfiliado().getEmail() != null){

            emailRequestDto.setEmail(docu.getAfiliado().getEmail());
        }
        else {

            emailRequestDto.setEmail("");
        }


        root.put("titulo", emailRequestDto.getCaseType());
        root.put("comentario", emailRequestDto.getMessage());
        root.put("fechacreacion",emailRequestDto.getDateCase());
        root.put("nombreCompleto", emailRequestDto.getName());
        root.put("sapId", emailRequestDto.getSapId());
        root.put("cedulaIdentidad", emailRequestDto.getIdentification());
        root.put("cellPhone", emailRequestDto.getCellphone());
        root.put("telephone", emailRequestDto.getTelephone());
        root.put("email", emailRequestDto.getEmail());

        Template template = freeMarkerConfig.getCfg().getTemplate("html/email-tramite-alta.html");
        StringWriter stringWriter = new StringWriter();
        template.process(root, stringWriter);

        String message = stringWriter.toString();

        Email email = this.emailRepository.findByTipoEmail(TipoEmailEnum.TRAMITE_SOLICITUD.getCodigo());

        mailSender.sendMail(email, email.getSubject() + docNUM , message, archivos);

    }
}
