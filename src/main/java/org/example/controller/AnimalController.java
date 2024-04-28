package org.example.controller;

import org.example.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;
    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<String>("File is empty", HttpStatus.BAD_REQUEST);
        }
        try {
            String stringType = file.getContentType();

            if (stringType != null && (stringType.equals("text/csv") || stringType.equals("application/xml"))){
                if (stringType.equals("text/csv")) {
                    animalService.processCsvFile(file.getInputStream());
                } else if (stringType.equals("application/xml")) {
                    animalService.processXmlFile(file.getInputStream());
                }
                return new ResponseEntity<String>("File uploaded successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Unsupported file format. Only CSV or XML files are allowed",
                        HttpStatus.BAD_REQUEST);
            }
        }
        catch (IOException e) {
            return new ResponseEntity<Object>("Failed to upload file" + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
