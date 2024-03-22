package com.asm3.prj321x.thienhtfx17332.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private  String title;

    @Column(name = "content_markdown")
    private  String contentMarkdown;
    @Column(name = "content_html")
    private  String contentHTML;

    @Column(name = "confirm_by_doctor")
    private  int confirmByDoctor;

    @Column(name = "writer_id")
    private  int writerId;

    @Column(name = "image")
    private  String image;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private  String createdAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private  String updateAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delete_at")
    private  String deleteAt;

    @Column(name = "cancel")
    private  String cancel;

    @ManyToOne
    @JoinColumn(name = "for_specialization_id")
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "for_clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "for_doctor_id")
    private DoctorUser doctorUser;

}
