package com.asm3.prj321x.thienhtfx17332.dto.userDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUserDTO {
    private int doctorId;
    private int clinId;
    private  int specId;
    private String overview;
    private String trainingProcess;
    private String achievements;
}

