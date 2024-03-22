package com.asm3.prj321x.thienhtfx17332.service;

import com.asm3.prj321x.thienhtfx17332.dto.clinicDTO.GetClinicDTO;
import com.asm3.prj321x.thienhtfx17332.dto.searchDTO.SearchBySpecializationDTO;
import com.asm3.prj321x.thienhtfx17332.dto.searchDTO.SearchDTO;
import com.asm3.prj321x.thienhtfx17332.dto.specializationDTO.GetSpecDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeService {
    List<GetSpecDTO> getSpecialization();

    List<GetClinicDTO> getClinics();

    List<SearchDTO> search(@Param("keyword") String keyword);

    List<SearchBySpecializationDTO> searchBySpecialization(@Param("keyword") String keyword);


}
