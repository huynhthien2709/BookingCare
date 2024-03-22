package com.asm3.prj321x.thienhtfx17332.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "date")
    private  String date;

    @Column(name = "time")
    private  String time;

    @Column(name = "max_booking")
    private  String maxBooking;

    @Column(name = "sum_booking")
    private  String sumBooking;

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
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private DoctorUser doctorUser;



}
