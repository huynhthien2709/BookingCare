package com.asm3.prj321x.thienhtfx17332.service;

import com.asm3.prj321x.thienhtfx17332.dto.MailDTO.MailDTO;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    public void sendMail(MailDTO mailDTO);

    public void sendMailWithAttachment(MailDTO mailDTO);
}
