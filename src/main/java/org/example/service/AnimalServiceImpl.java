package org.example.service;

import org.example.Animal;
import org.example.repository.AnimalRepository;

public class AnimalServiceImpl implements AnimalService {
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

    @Override
    public Animal parseAnimalInfoFromFile(String fileContext) {
        String[] parts = fileContext.split(",");

        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid format in the file");
        }

        String name = parts[0].trim();
        String sexStr = parts[1].trim();
        String typeSts = parts[2].trim();
        int weight = Integer.parseInt(parts[3].trim());
        double cost = Double.parseDouble(parts[4].trim());

        //enums
        Animal.Sex sex = Animal.Sex.valueOf(sexStr.toUpperCase());
        Animal.Type type = Animal.Type.valueOf(typeSts.toUpperCase());

        return new Animal(name, type, sex, weight, cost);
    }
}
