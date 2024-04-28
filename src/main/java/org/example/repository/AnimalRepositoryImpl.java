package org.example.repository;

import org.example.Animal;

import java.util.HashMap;
import java.util.Map;

public class AnimalRepositoryImpl implements AnimalRepository {
    private final Map<Long, Animal> animals = new HashMap<Long, Animal>();
    @Override
    public Animal findById(Long id) {
        return animals.get(id);
    }

    @Override
    public void save(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal cannot be null");
        }

    }

    @Override
    public void deleteById(Long id) {
        animals.remove(id);
    }
}
