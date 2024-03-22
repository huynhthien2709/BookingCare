package com.asm3.prj321x.thienhtfx17332.dto.userDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetDoctorScheduleDTO {
    private String doctorName;
    private String title;
    private int forDoctorId;
    private int forSpecializationId;
    private int forClinicId;
    private int patientId;
    private int confirm;
    private String cancel;
}
