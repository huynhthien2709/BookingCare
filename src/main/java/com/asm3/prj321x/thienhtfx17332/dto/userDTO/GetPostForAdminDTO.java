package com.asm3.prj321x.thienhtfx17332.dto.userDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostForAdminDTO {

    private String patientName;
    private String title;
    private int forDoctorId;
    private int forSpecializationId;
    private int forClinicId;
    private int patientId;




}
