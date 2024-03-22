package com.asm3.prj321x.thienhtfx17332.controller.publicApi;

import com.asm3.prj321x.thienhtfx17332.dto.clinicDTO.GetClinicDTO;
import com.asm3.prj321x.thienhtfx17332.dto.searchDTO.SearchBySpecializationDTO;
import com.asm3.prj321x.thienhtfx17332.dto.searchDTO.SearchDTO;
import com.asm3.prj321x.thienhtfx17332.dto.specializationDTO.GetSpecDTO;
import com.asm3.prj321x.thienhtfx17332.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class HomeRestController {
    @Autowired
    private HomeService homeService;

    @GetMapping("/getSpec")
    public List<GetSpecDTO> getSpecialization(){
        return homeService.getSpecialization();
    }

    @GetMapping("/getClinic")
    public List<GetClinicDTO> getClinicDTOS(){
        return homeService.getClinics();
    }

    @GetMapping("/search")
    public List<SearchDTO> search(@Param("keyword") String keyword){
        return homeService.search(keyword);
    }

    @GetMapping("/searchBySpecialization")
    public List<SearchBySpecializationDTO> searchBySpecialization(@Param("keyword") String keyword){
        return homeService.searchBySpecialization(keyword);
    }

}
