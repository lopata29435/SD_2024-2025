package com.example.zoo.service;

import com.example.zoo.model.Animal;
import com.example.zoo.model.Thing;
import com.example.zoo.model.Herbo;
import com.example.zoo.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service //В целом этот класс занимается бизнес логикой, так что логичней обозвать его сервисом(но вообще это то же самое что и Component)
//Этот класс выполняет всю логику, репозиторий просто отдает либо принимает данные. Это сохранение единой ответственности.
public class ZooService {
    @Autowired //Прокидываем зависимость через поле
    private ZooRepository zooRepository;

    @Autowired //Прокидываем зависимость через поле
    private VetService vetService;

    public void addAnimal(Animal animal) {
        if (vetService.checkHealth(animal)) {
            zooRepository.addAnimal(animal);
        }
    }

    public int getTotalFoodConsumption() {
        return zooRepository.getAnimals().stream().mapToInt(Animal::getFood).sum();
    }

    public int getTotalAnimals() {
        return zooRepository.getAnimals().size();
    }

    public List<Animal> getAnimalsForContactZoo() {
        return zooRepository.getAnimals().stream()
                .filter(animal -> animal instanceof Herbo)
                .filter(animal -> ((Herbo) animal).canInteractWithVisitors())
                .collect(Collectors.toList());
    }

    public List<String> getZooInventory() {
        List<String> inventory = new ArrayList<>();

        for (Animal animal : zooRepository.getAnimals()) {
            inventory.add("Animal: " + animal.getClass().getSimpleName() + ", Inventory Number: " + animal.getNumber());
        }

        for (Thing thing : zooRepository.getThings()) {
            inventory.add("Thing: " + thing.getClass().getSimpleName() + ", Inventory Number: " + thing.getNumber());
        }

        return inventory;
    }
}