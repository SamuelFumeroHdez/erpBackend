package com.erp.backend.service;

import com.erp.backend.exception.ActivationException;
import com.erp.backend.models.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {

    JavaMailSender javaMailSender;
    MailBuilder mailBuilder;

    @Async
    void sendEmail(NotificationEmail notificationEmail) throws ActivationException, MessagingException {
        /*MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("activation@leosamerp.com");
            messageHelper.setTo(notificationEmail.getRecepient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText("<h1>Hola!!</h1>");
            messageHelper.setText(mailBuilder.build(notificationEmail.getBody()));*/
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(notificationEmail.getRecepient());
        helper.setFrom("activation@leosamerp.com");

        helper.setSubject(notificationEmail.getSubject());

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1 style=\"text-align:center\"> " + notificationEmail.getBody() + "</h1>", true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        //helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

        };
        /*try{
            javaMailSender.send(messagePreparator);
            System.out.printf("Activation message sent");
        }catch (MailException e){
            throw new ActivationException("Error sending Activation Email to " + notificationEmail.getRecepient());
        }*/
    }


