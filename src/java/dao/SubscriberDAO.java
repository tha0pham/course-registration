/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Subscriber;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import resource.JPAResource;

/**
 *
 * @author Administrator
 */
public class SubscriberDAO extends JPAResource {

    public SubscriberDAO() {
    }
    
     public String sendEmail(String from, String to, String subject, String mailText, String fromPassword) {
        String temp = "Send email successful!!!";
        String smtpServer = "mail.saigontech.edu.vn";
        String userName = "thaopdp13@saigontech.edu.vn";
        String password = "123abc";
        if (fromPassword != null) {
            password = fromPassword;
        }
        if ((to == null) && (subject == null)) {
            temp = "tonull";
        }
        if (mailText == null) {
            mailText = "";
        }
        if (from == null) {
            from = "thaopdp13@saigontech.edu.vn";
        }
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpServer);
            //SMTP server authentication is set to false, by default. Setting it to true as shown below
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            //Setting the 'from', 'to', 'cc' addresses and the 'subject'
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            //Making the mail body as inline and of html type
            MimeMultipart mp = new MimeMultipart();
            MimeBodyPart text = new MimeBodyPart();
            //text.setDisposition(Part.INLINE);
            text.setContent(mailText, "text/html");
            mp.addBodyPart(text);
            message.setContent(mp);
            //SMTP authentication
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpServer, userName, password);
            message.saveChanges();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            //temp = "0k";
            // return temp;
        } catch (Exception e) {
            temp = e.toString();
        }
        //temp = "kkk";
        return temp;
    }

    public List<Subscriber> findAllSubscribers() {
        List<Subscriber> customers = null;
        EntityManager em = this.getEMF().createEntityManager();
        try {
            String sql = "select c from Subscriber c";
            Query query = em.createQuery(sql);
            customers = query.getResultList();
            return customers;
        } finally {
            em.close();
        }

    }
    
}
