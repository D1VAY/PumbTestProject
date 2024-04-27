package org.example.repository;

import org.example.Animal;

public interface AnimalRepository {
    Animal findById(Long id);
    void save(Animal animal);
    void deleteById(Long id);
}
