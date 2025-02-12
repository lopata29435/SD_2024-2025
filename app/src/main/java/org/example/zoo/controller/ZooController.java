package com.example.zoo.controller;

import com.example.zoo.model.Animal;
import com.example.zoo.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ZooController {
    @Autowired
    private ZooService zooService;

    public void addAnimal(Animal animal) {
        zooService.addAnimal(animal);
    }

    public void printTotalFoodConsumption() {
        System.out.println("Total food consumption: " + zooService.getTotalFoodConsumption() + " kg");
    }

    public void printAnimalsForContactZoo() {
        List<Animal> animals = zooService.getAnimalsForContactZoo();
        System.out.println("Animals for contact zoo:");
        animals.forEach(animal -> System.out.println(animal.getClass().getSimpleName() + ", Inventory Number: " + animal.getNumber()));
    }

    public void printInventorization() {
        List<String> animals = zooService.getZooInventory();
        System.out.println("Inventorization:");
        animals.forEach(item -> System.out.println(item));
    }
}