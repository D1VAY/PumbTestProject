package org.example.controller;

import org.example.Animal;
import org.example.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    // Метод для создания нового животного (POST запрос)
    @PostMapping
    public ResponseEntity<?> createAnimal(@RequestBody MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return new ResponseEntity<String>("File is empty", HttpStatus.BAD_REQUEST);
        }
        try {
            String fileContent = new String(multipartFile.getBytes());

            Animal animal = animalService.parseAnimalInfoFromFile(fileContent);

            animalService.saveAnimal(animal);

            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<String>("Uploaded file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
