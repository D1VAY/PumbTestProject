package org.example.service;

import org.example.Animal;
import org.example.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
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
    @Override
    public void processCsvFile(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Обработка строки CSV файла
                String[] parts = line.split(",");
                // Создание объекта Animal из данных CSV
            }
        } catch (Exception e) {
        }
    }
    @Override
    public void processXmlFile(InputStream inputStream) {


    }
}
