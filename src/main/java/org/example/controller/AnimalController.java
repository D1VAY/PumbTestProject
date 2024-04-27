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
    public ResponseEntity<Void> createAnimal(@RequestBody Animal animal) {
        animalService.saveAnimal(animal);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
