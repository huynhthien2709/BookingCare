package com.asm3.prj321x.thienhtfx17332.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "doctor_id")
    private int doctorId;

    @NotNull(message = "name is required")
    @Column(name = "name")
    private  String name;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private DoctorUser doctorUser;

    @ManyToOne
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private Status status;

/*    @JoinColumn(name = "id")
    @OneToOne
    private User user;*/

    @OneToMany(mappedBy = "patient")
    private List<Extrainfo> extrainfos;





}
