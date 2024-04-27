package org.example.service;

import org.example.Animal;
import org.example.repository.AnimalRepository;

public class AnimalServiceImpl implements AnimalService{
    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id);
    }

    @Override
    public void saveAnimal(Animal animal) {
        animalRepository.save(animal);
    }
    @Override
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
