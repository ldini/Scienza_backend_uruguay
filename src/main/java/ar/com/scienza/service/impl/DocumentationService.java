package ar.com.scienza.service.impl;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.xml.bind.DatatypeConverter;

import ar.com.scienza.config.FreeMarkerConfig;
import ar.com.scienza.dto.*;
import ar.com.scienza.entity.*;
import ar.com.scienza.enumerator.TipoEmailEnum;
import ar.com.scienza.mailsender.MailSender;
import ar.com.scienza.remote.json.dto.AffiliateDataEmailRequestDto;
import ar.com.scienza.repository.EmailRepository;
import ar.com.scienza.repository.TipoDocumentacionRepository;
import ar.com.scienza.transformer.DocumentacionTRF;
import ar.com.scienza.transformer.TramiteTRF;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.enumerator.TipoArchivoEnum;
import ar.com.scienza.enumerator.TipoDocumentacionEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.DocumentacionRepository;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.service.IDocumentationService;


@Service
@Transactional(rollbackFor = ServiceException.class)
public class DocumentationService extends FileService implements IDocumentationService {

    @Autowired
    private DocumentacionRepository documentationRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private TipoDocumentacionRepository documentationTypeRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Autowired
    private EmailRepository emailRepository;

    @Value("${mail.environment}")
    String mailEnvironment;

    @Override
    public DocumentationResponseDto getDocumentation(Integer documentationId, String token, Long sapId, String deviceType) throws ServiceException {

        DocumentationResponseDto response = new DocumentationResponseDto();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Afiliado afiliado = getUserWithSession(token, sapId);

            Documentacion documentation = documentationRepository.findOneByIdAndAfiliadoId(documentationId, afiliado.getId());
            if (documentation == null) {
                throw new Exception();
            }

            response.setDocumentationId(documentation.getId());
            response.setFechaTramite(sdf.format(documentation.getFechaInsert()));
            response.setTipoDocumentacion(documentation.getTipoDocumentacion().getDescripcion());

            List<String> pathArchivo = new ArrayList<>();

            for (DocumentacionArchivo docArc : documentation.getDocumentacionArchivoArchivoList()) {
                pathArchivo.add(buildResourceUrl(docArc.getFilename()));
            }

            response.setFileList(pathArchivo);
        } catch (Exception e) {
            ScienzaLogger.logError(e);
            throw new ServiceException("Error al obtener la documentación del paciente");
        }

        return response;
    }


    @Override
    public List<DocumentationResultRespDto> getDocumentationList(TipoDocumentacionEnum type, String token, Long sapId, String deviceType) throws ServiceException {

        List<DocumentationResultRespDto> responseList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
            if (afiliado == null) {
                throw new Exception();
            }

            List<DocumentacionTRF> docTRFList = documentationRepository.findAllTRFByAfiliadoId(afiliado.getId());

            for (DocumentacionTRF docTRF : docTRFList) {

                Documentacion documentation = documentationRepository.findOne(docTRF.getId());

                DocumentationResultRespDto response = new DocumentationResultRespDto();

                response.setDocumentationId(documentation.getId());
                response.setTipoDocumentacion(documentation.getTipoDocumentacion().getDescripcion());
                response.setFechaTramite(sdf.format(documentation.getFechaInsert()));
                response.setCantidadArchivos(documentation.getDocumentacionArchivoArchivoList().size());

                responseList.add(response);
            }

        } catch (Exception e) {
            ScienzaLogger.logError(e);
            throw new ServiceException("Error al obtener la lista de documentos");
        }

        return responseList;
    }


    @Override
    public DocumentationCreateResponseDto createDocumentation(DocumentationCreateRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

        DocumentationCreateResponseDto response = new DocumentationCreateResponseDto();

        try 
        {
            Afiliado afiliado = getUserWithSession(token, sapId);

            TipoDocumentacion tipoDocumentacion = documentationTypeRepository.findOneByCodigoAndFechaDeleteIsNullOrderByIdAsc(request.getTipoDocumentacion());

            if (request.getPdfs().size() > 0 || request.getImagenes().size() > 0) {

                Documentacion documentacion = new Documentacion();
                documentacion.setAfiliado(afiliado);
                documentacion.setFechaInsert(new Date());
                documentacion.setTipoDocumentacion(tipoDocumentacion);
                documentacion.createOnRepository(documentationRepository);

                List<File> files = parseFiles("DOCUM-" + documentacion.getId() + "_IMAGEN_", request.getImagenes());

                List<File> sendFiles = new ArrayList<>();

                Integer i = 0 ;

                if (!files.isEmpty()) {
                    String pdfImagesFile = "DOCUM-" + documentacion.getId() + "-" + i + ".pdf";
                    sendFiles.add(createImagePdf(pdfImagesFile, files));

                    // Creo la entrada del pdf de imágenes.
                    DocumentacionArchivo docArc = new DocumentacionArchivo();
                    docArc.setDocumentacion(documentacion);
                    docArc.setPath(pathPdf + pdfImagesFile);
                    docArc.setFilename(pdfImagesFile);
                    docArc.createOnRepository(documentationRepository);
                    i++;
                }

                // Pdfs
                for (String base64File : request.getPdfs()) {
                    String pdfFile = "DOCUM-" + documentacion.getId() +  "-" + i + ".pdf";
                    sendFiles.add(savePdfFile(base64File, pdfFile));
                    
                    DocumentacionArchivo docuArc = new DocumentacionArchivo();
                    docuArc.setDocumentacion(documentacion);
                    docuArc.setPath(pathPdf + pdfFile);
                    docuArc.setFilename(pdfFile);
                    docuArc.createOnRepository(documentationRepository);
                    i++;
                }

                response.setDocumentationId(documentacion.getId());
                sendDocumentationEmail(documentacion , sendFiles) ;
            }
            else
            {
                throw new ServiceException("Debe adjuntar al menos una imagen o pdf.");

            }

        } catch (Exception e) {
            ScienzaLogger.logError(e);
            throw new ServiceException("Error al crear la documentación del paciente");
        }

        return response;
    }

    
    private void sendDocumentationEmail(Documentacion docu , List<File> archivos) throws IOException, MessagingException, TemplateException {

        Map<Object, Object> root = new HashMap<Object, Object>();

        AffiliateDataEmailRequestDto emailRequestDto = new AffiliateDataEmailRequestDto();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String docNUM = " " + docu.getId();

        emailRequestDto.setCaseType("Documento enviada por el paciente");
        emailRequestDto.setCategory(docu.getTipoDocumentacion().getDescripcion());
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
        root.put("categoria", emailRequestDto.getCategory());
        root.put("fechacreacion",emailRequestDto.getDateCase());
        root.put("nombreCompleto", emailRequestDto.getName());
        root.put("sapId", emailRequestDto.getSapId());
        root.put("cedulaIdentidad", emailRequestDto.getIdentification());
        root.put("cellPhone", emailRequestDto.getCellphone());
        root.put("telephone", emailRequestDto.getTelephone());
        root.put("email", emailRequestDto.getEmail());

        Template template = freeMarkerConfig.getCfg().getTemplate("html/email-docum-alta.html");
        StringWriter stringWriter = new StringWriter();
        template.process(root, stringWriter);

        String message = stringWriter.toString();

        Email email = this.emailRepository.findByTipoEmail(TipoEmailEnum.DOCUMENTACION_SOLICITUD.getCodigo());

        mailSender.sendMail(email, email.getSubject() + docNUM, message, archivos);

    }
}
