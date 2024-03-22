package com.asm3.prj321x.thienhtfx17332.service;

import com.asm3.prj321x.thienhtfx17332.dto.clinicDTO.GetClinicDTO;
import com.asm3.prj321x.thienhtfx17332.dto.searchDTO.SearchBySpecializationDTO;
import com.asm3.prj321x.thienhtfx17332.dto.searchDTO.SearchDTO;
import com.asm3.prj321x.thienhtfx17332.dto.specializationDTO.GetSpecDTO;
import com.asm3.prj321x.thienhtfx17332.repository.ClinicRepository;
import com.asm3.prj321x.thienhtfx17332.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private ClinicRepository clinicRepository;


    @Override
    public List<GetSpecDTO> getSpecialization() {
        List<Map<String, Object>> resultList = specializationRepository.getSpecializations();
        List<GetSpecDTO> specs = new ArrayList<>();
        for (Map element : resultList) {
            int id = (int) element.get("id");
            String name = (String) element.get("name");
            String description = (String) element.get("description");
            String image = (String) element.get("image");
            specs.add(new GetSpecDTO(id, name, description, image));
        }
        return specs;
    }

    @Override
    public List<GetClinicDTO> getClinics() {
        List<Map<String, Object>> resultList = clinicRepository.getClinic();
        List<GetClinicDTO> cli = new ArrayList<>();
        for (Map e : resultList) {
            int id = (int) e.get("id");
            String name = (String) e.get("name");
            String description = (String) e.get("description");
            String image = (String) e.get("image");
            String address = (String) e.get("address");
            cli.add(new GetClinicDTO(id, name, description, image, address));

        }
        return cli;
    }

    @Override
    public List<SearchDTO> search(String keyword) {
        keyword =  keyword.toLowerCase();
        System.out.println(keyword);
        List<Map<String, Object>> resultList = clinicRepository.search(keyword);
        List<SearchDTO> searchList = new ArrayList<>();
        for (Map e : resultList) {
            int id = (int) e.get("id");
            String name = (String) e.get("name");
            String phone = (String) e.get("phone");
            String address = (String) e.get("address");
            String description = (String) e.get("description");
            String image = (String) e.get("image");
            searchList.add(new SearchDTO(id, name, phone, address, description, image));
        }
        return searchList;
    }

    @Override
    public List<SearchBySpecializationDTO> searchBySpecialization(String keyword) {
        keyword = keyword.toLowerCase();
        List<Map<String, Object>> resultList = specializationRepository.searchSpecialization(keyword);
        List<SearchBySpecializationDTO> searchList = new ArrayList<>();
        for (Map e : resultList) {
            int specId = (int) e.get("spec_id");
            String specName = (String) e.get("spec_name");
            int docId = (int) e.get("doc_id");
            String docName = (String) e.get("doc_name");
            searchList.add(new SearchBySpecializationDTO(specId, specName, docId, docName));
        }
        return searchList;
    }
}
