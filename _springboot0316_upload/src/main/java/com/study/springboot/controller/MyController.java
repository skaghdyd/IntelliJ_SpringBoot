package com.study.springboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Log4j2
public class MyController {
    @GetMapping("/uploadEx")
    public void uplaodEx() {
        log.info("처음");
    }

    @PostMapping("/uploadAjax")
    public ResponseEntity<String> uploadFile(MultipartFile[] uploadFiles) throws IOException {
        for (MultipartFile uploadFile : uploadFiles) {
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("fileName: " + fileName);
            Path savePath = Paths.get("c:\\upload\\" + fileName);
            uploadFile.transferTo(savePath);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
