package com.asm3.prj321x.thienhtfx17332.repository;

import com.asm3.prj321x.thienhtfx17332.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PostRepository extends JpaRepository<Post, Integer> {


    @Query(value = "select (p.id) as postId, (p.title) as title, (p.for_specialization_id) as specId, (p.for_clinic_id) as clinId, (p.writer_id) as writerId, (p.confirm_by_doctor) as confirm, (p.cancel) as cancel " +
            "from posts p " +
            "join doctor_users d on p.for_doctor_id = d.id " +
            "where d.doctor_id =:doctorId", nativeQuery = true)
    List<Map<String, Object>> getPostForDoctor(@Param("doctorId") int doctorId);

    Post findPostById(int postId);

    @Query(value = "select pa.name as patientName, p.title as title, p.for_doctor_id as forDoctorId, p.for_specialization_id as forSpecializationId, p.for_clinic_id as forClinicId, pa.patient_id as patientId " +
            "from posts p " +
            "join patients pa on pa.patient_id = p.writer_id " +
            "where p.confirm_by_doctor = 1 and pa.patient_id =:patientId", nativeQuery = true)
    List<Map<String, Object>> getPostForAdmin(@Param("patientId") int patientId);

    @Query(value = "select u.name as doctorName, p.title as title, p.for_doctor_id as doctorId, p.for_specialization_id as specId, p.for_clinic_id as clinId, p.writer_id as patientId,\n" +
                   " p.confirm_by_doctor as confirm, p.cancel as cancel " +
            "from posts p " +
            "join doctor_users d on p.for_doctor_id = d.id " +
            "join users u on d.doctor_id = u.id " +
            "where p.for_doctor_id =:doctorId", nativeQuery = true)
    List<Map<String, Object>> getDoctorSchedule(@Param("doctorId") int doctorId);



}
