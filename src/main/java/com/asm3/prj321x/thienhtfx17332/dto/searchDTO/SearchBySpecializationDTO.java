package com.asm3.prj321x.thienhtfx17332.dto.searchDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchBySpecializationDTO {
    private int specId;
    private  String specName;
    private int docId;
    private String docName;

    public SearchBySpecializationDTO(String patientName, String gender, String address, String description, String historyBreath) {
    }
}
