package com.asm3.prj321x.thienhtfx17332.repository;

import com.asm3.prj321x.thienhtfx17332.entity.Post;
import com.asm3.prj321x.thienhtfx17332.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    @Query(value = "SELECT count(s.id) as specId, s.id, s.name, s.description, s.image " +
            "from specializations s " +
            "join posts p ON p.for_specialization_id = s.id " +
            "group by s.id " +
            "order by specId desc limit 5"
            , nativeQuery = true)
    List<Map<String, Object>> getSpecializations();

    @Query(value = "select (s.id) as spec_id, (s.name) as spec_name, (d.id) as doc_id, (u.name) as doc_name " +
            "from specializations s " +
            "join doctor_users d on s.id = d.specialization_id " +
            "join users u on d.doctor_id = u.id " +
            "where lower(s.name) like  %:keyword% or lower(u.name) like %:keyword% " +
            "group by d.id", nativeQuery = true)
    List<Map<String, Object>> searchSpecialization(@Param("keyword") String keyword);

    Specialization findSpecializationById(int specId);


}
