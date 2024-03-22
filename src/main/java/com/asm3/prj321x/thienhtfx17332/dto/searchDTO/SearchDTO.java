package com.asm3.prj321x.thienhtfx17332.dto.searchDTO;

import lombok.Data;

@Data
public class SearchDTO {
    private int clinic_id;
    private String name;
    private String phone ;
    private String description;
    private String image;
    private String address;

    public SearchDTO(int clinic_id, String name, String phone, String address, String image, String description) {
        this.clinic_id = clinic_id;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.image = image;
        this.address = address;
    }

    public int getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(int clinic_id) {
        this.clinic_id = clinic_id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
