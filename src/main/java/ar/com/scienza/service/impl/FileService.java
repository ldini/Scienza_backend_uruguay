package ar.com.scienza.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.repository.SesionRepository;

public abstract class FileService {

    @Autowired
    protected SesionRepository sesionRepository;


    /*** Arhcivos y recursos ***/
    @Value("${server.protocol}")
    protected String protocol;

    @Value("${server.host}")
    protected String host;

    @Value("${server.port}")
    protected String port;

    @Value("${server.contextPath}")
    protected String contextPath;

    @Value("${location.files}")
    protected String locationFiles;

    @Value("${path.pdf}")
    protected String pathPdf;

    @Value("${path.originalImage}")
    protected String pathOriginalImage;

    @Value("${mail.environment}")
    protected String mailEnvironment;


    protected Afiliado getUserWithSession(String token, Long sapId) throws Exception {
        Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
        if(afiliado == null) {
            throw new Exception();
        }
        return afiliado;
    }

    protected String buildResourceUrl(String filename){
    	//Atada de alambre por limitacion de puertos en DESA
    	String customPort = host.endsWith("fusap.com.ar") ? "" : ":" + port;
        return protocol + "://" + host + customPort + contextPath + "/resources/" + pathPdf + filename;
    }

    protected ArrayList<File> parseFiles(String prefix, List<String> base64Files) throws IOException {
        ArrayList<File> files = new ArrayList<>();

        for (String imagen : base64Files) {
            String base64Image = imagen.split(",")[1];
            String fileName = prefix + "-" + base64Files.indexOf(imagen);
            files.add(saveImageFile(base64Image, fileName));
        }
        return files;
    }

    protected File createImagePdf(String outputFile, List<File> files) throws IOException, DocumentException {
        File filePdf = new File(locationFiles + pathPdf + outputFile );
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePdf));
        document.open();

        for (File f : files) {
            document.newPage();
            Image image = Image.getInstance(f.getAbsolutePath());
            image.scaleToFit(PageSize.A4.getWidth() - 100, PageSize.A4.getHeight() - 100);
            image.setAbsolutePosition(50, PageSize.A4.getHeight() - image.getScaledHeight() - 50);
            image.setBorderWidth(0);
            document.add(image);
        }
        document.close();
        return filePdf;
    }

    protected File savePdfFile(String base64File, String filename) throws IOException {
        File file = new File(locationFiles + pathPdf + filename);
        String base64Image = base64File.split(",")[1];
        byte[] decoder = Base64.getDecoder().decode(base64Image);
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(decoder);
        fop.flush();
        fop.close();
        return file;
    }

    protected File saveImageFile(String base64Image, String filename) throws IOException {
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        File imageFile = new File(locationFiles + pathOriginalImage + filename);
        ImageIO.write(bufferedImage, "png", imageFile);
        return imageFile;
    }
}
