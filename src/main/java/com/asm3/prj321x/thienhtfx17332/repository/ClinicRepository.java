package com.asm3.prj321x.thienhtfx17332.repository;

import com.asm3.prj321x.thienhtfx17332.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    @Query(value = "select count(p.for_clinic_id) as sl, c.id, c.name, c.image, c.description, c.address " +
            "from posts p " +
            "inner join clinics c on p.for_clinic_id = c.id " +
            "group by p.for_clinic_id " +
            "order by sl desc limit 5", nativeQuery = true)
    List<Map<String, Object>> getClinic();

    @Query(value = "select c.id, c.name, c.phone, c.address, c.description, c.image " +
            "from clinics c " +
            "join specializations s on c.spec_id = s.id " +
            "where LOWER(c.name) like :keyword or LOWER(c.address) like :keyword or LOWER(s.name) like :keyword " +
            "group by c.id",
             nativeQuery = true)
    List<Map<String, Object>> search(@Param("keyword") String keyword);

    Clinic findClinicsById(int clinId);


}
