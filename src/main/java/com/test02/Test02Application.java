package com.test02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.InternetAddress;

@SpringBootApplication
public class Test02Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Test02Application.class, args);

        String content = "Testing the Email sender";
//        sendEmail("onagoruwam@gmail.com", "Crosspoint welcomes you to our platform", "CROSSPOINT.NG", content);
        List<String> emails = new ArrayList<>();
        emails.add("audubilly89@gmail.com");
        emails.add("ogunbiyioladapo33@gmail.com");
        emails.add("ohida2001@gmail.com");
        emails.add("link4lixy@gmail.com");
        emails.add("onagoruwam@gmail.com");
        sendMultipleEmails(emails,content,"CROSSPOINT.NG", "Welcome to CrossPoint Nigeria..");
    }

    public static void sendMultipleEmails(List<String> emails, String content, String from, String subject) throws Exception {
        emails.forEach(emailAddress -> {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "localhost");
            props.setProperty("mail.user", "billy@crosspoint.com");
            props.setProperty("mail.password", "billy123!");

            Session mailSession = Session.getDefaultInstance(props, null);

            try {
                Transport transport = mailSession.getTransport();
                MimeMessage message = new MimeMessage(mailSession);
                message.addFrom(new Address[]{new InternetAddress("billy@crosspoint.com", from)});  // the reply to email address and a logical descriptor of the sender of the email!

                message.setSubject(subject);
                message.setContent(content, "text/plain");
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(emailAddress));

                transport.connect();
                transport.sendMessage(message,
                        message.getRecipients(Message.RecipientType.TO));
                transport.close();

                System.out.println("Message sent successfully...");

            } catch (NoSuchProviderException | UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (AddressException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }


        });
    }

    public static void sendEmail(String to, String subject, String from, String content) throws Exception {

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "localhost");
        props.setProperty("mail.user", "billy@crosspoint.com");
        props.setProperty("mail.password", "billy123!");

        Session mailSession = Session.getDefaultInstance(props, null);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.addFrom(new Address[]{new InternetAddress("billy@crosspoint.com", from)});  // the reply to email address and a logical descriptor of the sender of the email!

        message.setSubject(subject);
        message.setContent(content, "text/plain");
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));

        transport.connect();
        transport.sendMessage(message,
                message.getRecipients(Message.RecipientType.TO));
        transport.close();

        System.out.println("Message sent successfully...");
    }
}


