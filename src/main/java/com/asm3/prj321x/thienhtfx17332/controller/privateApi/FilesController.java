package com.asm3.prj321x.thienhtfx17332.controller.privateApi;

import com.asm3.prj321x.thienhtfx17332.dto.FileDTO.FileDTO;
import com.asm3.prj321x.thienhtfx17332.dto.FileDTO.ResponseMessage;
import com.asm3.prj321x.thienhtfx17332.security.JwtProvider;
import com.asm3.prj321x.thienhtfx17332.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/file")
public class FilesController {
    @Autowired
    private FilesStorageService storageService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return null;
        String message = "";
        try {
            storageService.save(file);
            message = "src/main/resources/File/" + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<FileDTO>> getListFiles() {
        List<FileDTO> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileDTO(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<UrlResource> getFile(@PathVariable String filename) {
        UrlResource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
