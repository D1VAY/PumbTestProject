package org.example.service;

import org.example.Animal;

public interface AnimalService {
    Animal findById(Long id);
    void saveAnimal(Animal animal);
    void deleteAnimal(Long id);
}
