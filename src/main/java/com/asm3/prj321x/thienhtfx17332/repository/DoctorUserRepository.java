package com.asm3.prj321x.thienhtfx17332.repository;

import com.asm3.prj321x.thienhtfx17332.entity.DoctorUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DoctorUserRepository extends JpaRepository<DoctorUser, Integer> {

    @Query(value = "select  (u.name) as patientName, (u.gender) as gender, (u.address) as address, (p.description) as description , (e.historyBreath) as historyBreath " +
            "from users u " +
            "join patients p on u.id = p.patient_id " +
            "join doctor_users d on p.doctor_id = d.id " +
            "left join extrainfos e on e.patient_id = p.id " +
            "where d.doctor_id =:doctorId", nativeQuery = true)
    List<Map<String, Object>> getPatientList(@Param("doctorId") int doctorId);

    DoctorUser findDoctorUserById(int doctorId);
    DoctorUser findDoctorUserByUserId(int doctorId);



}
