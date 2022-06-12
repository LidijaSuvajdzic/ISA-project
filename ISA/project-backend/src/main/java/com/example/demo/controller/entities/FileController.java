package com.example.demo.controller.entities;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping(value = "api/file")
public class FileController {

@PostMapping("/upload")
    public ResponseEntity<Void> upload(@RequestParam("file")MultipartFile file) throws IOException {
    String Path_Directory = "C:\\Users\\Lidija";
    Files.copy(file.getInputStream(), Paths.get(Path_Directory+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

    return  new ResponseEntity<>(HttpStatus.OK);
}

}