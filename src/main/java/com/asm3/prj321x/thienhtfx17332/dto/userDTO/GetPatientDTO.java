package com.asm3.prj321x.thienhtfx17332.dto.userDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPatientDTO {
    private String patientName;
    private String gender;
    private String address;
    private String description;
    private String historyBreath;
}

