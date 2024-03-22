package com.asm3.prj321x.thienhtfx17332.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "clinics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "name is required")
    @Column(name = "name")
    private  String name;

    @Column(name = "address")
    private  String address;

    @Column(name = "phone")
    private  String phone;

    @Column(name = "introduction_html")
    private  String introductionHTML;

    @Column(name = "introduction_markdown")
    private  String introductionMarkdown;

    @Column(name = "description")
    private  String description;

    @Column(name = "image")
    private  String image;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private  String createdAt;

/*    @Column(name = "spec_id")
    private  int specId;*/

    @ManyToOne
    @JoinColumn(name = "spec_id")
    private Specialization specializations;

    @OneToMany(mappedBy = "clinic")
    private List<Post> posts;

    @OneToOne(mappedBy = "clinic")
    private DoctorUser doctorUser;



   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIntroductionHTML() {
        return introductionHTML;
    }

    public void setIntroductionHTML(String introductionHTML) {
        this.introductionHTML = introductionHTML;
    }
    public String getIntroductionMarkdown() {
        return introductionMarkdown;
    }

    public void setIntroductionMarkdown(String introductionMarkdown) {
        this.introductionMarkdown = introductionMarkdown;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
*//*
    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }*//*


    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public DoctorUser getDoctorUser() {
        return doctorUser;
    }

    public void setDoctorUser(DoctorUser doctorUser) {
        this.doctorUser = doctorUser;
    }*/
}
