package service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Gmail {
    private static String emailFrom = "techstockmaster.sistema@gmail.com";
    private static String passwordFrom = "aucumkngphkcctpb";
    private String emailTo = "arthurmik860@gmail.com";
    private String subject;
    private String content;

    private Session session;
    private MimeMessage mCorreio;

    public boolean shippingGmail(String emailto, String tolken) {
        boolean chek = false;

        subject = "Redefinir Senha!";
        content = "Sua senha: " + tolken;

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", emailFrom);
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.setProperty("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);

        try {
            mCorreio = new MimeMessage(session);
            mCorreio.setFrom(new InternetAddress(emailFrom));
            mCorreio.setRecipient(Message.RecipientType.TO, new InternetAddress(emailto));
            mCorreio.setSubject(subject);
            mCorreio.setText(content, "ISO-8859-1", "html");

            Transport mTransport = session.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreio, mCorreio.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            chek = true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return chek;
    }



    public boolean feedbackgGmail(String user, String descricao) {
        boolean chek = false;

        subject = "FeedBack do usuário!" + user;
        content = "Conteudo: " + descricao;

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", emailFrom);
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.setProperty("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);

        try {
            mCorreio = new MimeMessage(session);
            mCorreio.setFrom(new InternetAddress(emailFrom));
            mCorreio.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreio.setSubject(subject);
            mCorreio.setText(content, "ISO-8859-1", "html");

            Transport mTransport = session.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreio, mCorreio.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            chek = true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return chek;
    }
}
