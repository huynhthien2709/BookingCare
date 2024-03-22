package com.asm3.prj321x.thienhtfx17332.dto.MailDTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String to;
    private String subject;
    private String content;
//    private Map<String, Object> props;
    private String file;

}
