package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.service.SenderService;
import ua.com.alexcoffee.service.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class SenderServiceImpl implements SenderService, Runnable {

    @Autowired
    private UserService userService;

    private static String charset = "UTF-8";
    private static String encoding = "Q";

    private User admin;
    private List<User> managers;
    private Order order;

    @Override
    public void send(Order order) throws BadRequestException {
        this.order = order;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (order != null) {

            admin = userService.getAdministrator();
            managers = userService.getManagers();
            Collections.shuffle(managers);

            if (admin != null && !managers.isEmpty()) {
                try {
                    Properties properties;
                    String subject = "AlexCoffee || New Order " + order.getNumber();
                    String text = order.toString();
                    for (User manager : managers) {
                        Thread.sleep(10000);
                        try {
                            try {
                                properties = getTLSProperties();
                                sendMessage(properties, manager.getEmail(), subject, text);
                            } catch (MessagingException ex) {
                                ex.printStackTrace();

                                properties = getSSLProperties();
                                sendMessage(properties, manager.getEmail(), subject, text);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Properties for TLS level (Transport Layer Security)
    @Override
    public Properties getTLSProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    // Properties for SSL level (Secure Sockets Layer)
    @Override
    public Properties getSSLProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    @Override
    public void sendMessage(Properties properties, String toEmail, String subject, String text) throws MessagingException, UnsupportedEncodingException {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(admin.getEmail(), admin.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("support@alexcoffee.com.ua"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(MimeUtility.encodeText(subject, charset, encoding));
        message.setContent(text, "text/plain;charset=" + charset);
        message.setSentDate(new Date());

        Transport.send(message);
    }

    public static String getCharset() {
        return charset;
    }

    public static void setCharset(String charset) {
        SenderServiceImpl.charset = charset;
    }

    public static String getEncoding() {
        return encoding;
    }

    public static void setEncoding(String encoding) {
        SenderServiceImpl.encoding = encoding;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<User> getManagers() {
        return managers;
    }

    public void setManagers(List<User> managers) {
        this.managers = managers;
    }
}
