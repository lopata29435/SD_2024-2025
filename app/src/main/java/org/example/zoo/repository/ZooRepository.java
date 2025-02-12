package com.example.zoo.repository;

import com.example.zoo.model.Animal;
import com.example.zoo.model.Thing;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository //тут сделан задел на будущее. Сейчас мы конечно не управляем базой данной в этом классе, 
//но тут реализовано локальное хранилище которым мы управляем, так что логика не нарушена. 
public class ZooRepository {
    private List<Animal> animals = new ArrayList<>();
    private List<Thing> things = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addThing(Thing thing) {
        things.add(thing);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Thing> getThings() {
        return things;
    }

}