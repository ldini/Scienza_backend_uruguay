package ar.com.scienza.mailsender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.scienza.entity.Email;

@Component
public class MailSender {

    @Value("${email.smtp}")
    String emailSmtp;

    @Value("${email.port}")
    String emailPort;

    @Value("${email.alias}")
    String emailAlias;

    @Value("${email.username}")
    String emailUsername;

    @Value("${email.password}")
    String emailPassword;


    public void sendMail(Email email, String subject, String message, List<File> attachments) throws MessagingException, FileNotFoundException, IOException
    {
        //replace to valid character separator
        String TO = email.getTO().replace(";", ",");
        String CC = (email.getCC() != null) ? email.getCC().replace(";", ",") : null;
        String CCO = (email.getCCO() != null) ? email.getCCO().replace(";", ",") : null;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", emailSmtp);
        props.put("mail.smtp.port", emailPort);
//        props.put("mail.smtp.socketFactory.port", "587");
//        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); //Agregado
//        props.put("mail.smtp.ssl.protocols", "TLSv1.2");     //agregado


        Session session =
                Session.getInstance(
                        props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(emailUsername, emailPassword);
                            }
                        }
                );

        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(emailUsername, emailAlias));
        mimeMessage.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(TO));
        mimeMessage.setRecipients(Message.RecipientType.CC, (CC != null) ? InternetAddress.parse(CC) : null);
        mimeMessage.setRecipients(Message.RecipientType.BCC, (CCO != null) ? InternetAddress.parse(CCO) : null);
        mimeMessage.setSubject(subject);

        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html; charset=utf-8");
        multipart.addBodyPart(messageBodyPart);

        if(attachments != null && attachments.size() > 0) {
            for(File attachment : attachments)
                this.addAttachment(multipart, attachment);
        }

        mimeMessage.setContent(multipart);

        Transport.send(mimeMessage);
    }


    private void addAttachment(Multipart multipart, File attachment) throws MessagingException, FileNotFoundException, IOException
    {
        DataSource source = new ByteArrayDataSource(new FileInputStream(attachment), "application/pdf");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(attachment.getName());
        multipart.addBodyPart(messageBodyPart);
    }
}
