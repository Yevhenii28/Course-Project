package kostomarov.courseproject.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kostomarov.courseproject.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService implements EmailRepository {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendTextMail(String to, String subject, String text) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("hr.management.15@gmail.com");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setSubject(subject);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMailWithAttachment(String to, String subject, String text, String pathToAttachment) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("hr.management.15@gmail.com");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setSubject(subject);
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


