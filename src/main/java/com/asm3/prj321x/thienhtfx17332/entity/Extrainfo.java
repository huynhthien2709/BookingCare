package com.asm3.prj321x.thienhtfx17332.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "extrainfos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Extrainfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "historyBreath")
    private  String historyBreath;

    @Column(name = "oldForms")
    private  String oldForms;

    @Column(name = "sendForms")
    private  String sendForms;

    @Column(name = "moreInfo")
    private  String moreInfo;

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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;



}
