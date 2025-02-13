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

    public List<List<String>> getAnimalsForContactZoo() {
        return zooRepository.getAnimals().stream()
            .filter(animal -> animal instanceof Herbo)
            .filter(animal -> ((Herbo) animal).canInteractWithVisitors())
            .map(animal -> List.of(
                    animal.getClass().getSimpleName(),
                    String.valueOf(animal.getFood()),
                    String.valueOf(animal.getNumber()),
                    String.valueOf(animal instanceof Herbo ? ((Herbo) animal).getKindnessLevel() : "-")
            ))
            .collect(Collectors.toList());
    }

    public List<List<String>> getZooInventory() {
        List<List<String>> inventory = new ArrayList<>();
    
        for (Animal animal : zooRepository.getAnimals()) {
            inventory.add(List.of("Animal: " + animal.getClass().getSimpleName(), String.valueOf(animal.getNumber())));
        }
    
        for (Thing thing : zooRepository.getThings()) {
            inventory.add(List.of("Thing: " + thing.getClass().getSimpleName(), String.valueOf(thing.getNumber())));
        }
    
        return inventory;
    }
}