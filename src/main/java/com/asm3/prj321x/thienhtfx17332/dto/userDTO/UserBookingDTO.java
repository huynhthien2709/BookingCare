package com.asm3.prj321x.thienhtfx17332.dto.userDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBookingDTO {

    private  String title;
    private  String contentMarkdown;
    private  String contentHtml;
    private  int forDoctorId;
    private int forSpecializationId;
    private int forClinicId;
}
