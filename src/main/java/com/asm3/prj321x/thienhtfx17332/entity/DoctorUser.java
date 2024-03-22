package com.asm3.prj321x.thienhtfx17332.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

@Entity
@Table(name = "doctor_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    @Column(name = "overview")
    private  String overview;

    @Column(name = "training_process")
    private  String trainingProcess;

    @Column(name = "achievements")
    private  String achievements;

    @JoinColumn(name = "doctor_id")
    @OneToOne
    private User user;

    @JoinColumn(name = "clinic_id")
    @OneToOne
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @OneToMany(mappedBy = "doctorUser")
    private List<Post> posts;

    @OneToMany(mappedBy = "doctorUser")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "doctorUser")
    private List<Patient> patients;

}
