package com.asm3.prj321x.thienhtfx17332.dto.clinicDTO;

import lombok.Data;

@Data
public class GetClinicDTO {
    private int clinic_id;
    private String name ;
    private String description;
    private String image;
    private String address;

    public GetClinicDTO(int clinicId, String name, String description, String image, String address) {
        this.clinic_id = clinicId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.address = address;
    }

    public int getClinicId() {
        return clinic_id;
    }

    public void setClinicId(int clinicId) {
        this.clinic_id = clinicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
