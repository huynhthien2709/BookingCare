package com.asm3.prj321x.thienhtfx17332.service;

import com.asm3.prj321x.thienhtfx17332.dto.MailDTO.MailDTO;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(@RequestBody MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailDTO.getTo());
            message.setSubject(mailDTO.getSubject());
            message.setText(mailDTO.getContent());
            mailSender.send(message);
    }

    @Override
    public void sendMailWithAttachment(@RequestBody MailDTO mailDTO)
    {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            public void prepare(MimeMessage mimeMessage) throws Exception
            {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailDTO.getTo()));
                mimeMessage.setSubject(mailDTO.getSubject());
                mimeMessage.setText(mailDTO.getContent());

                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File(mailDTO.getFile()));
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(attachmentPart);
                mimeMessage.setContent(multipart);
            }
        };

        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
