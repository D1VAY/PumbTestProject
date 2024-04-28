package org.example.service;

import org.example.Animal;

import java.io.InputStream;

public interface AnimalService {
    Animal findById(Long id);

    void saveAnimal(Animal animal);

    void deleteAnimal(Long id);
    void processCsvFile(InputStream inputStream);
    void processXmlFile(InputStream inputStream);
}
