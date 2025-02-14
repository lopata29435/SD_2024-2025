package com.example.zoo.service;

import com.example.zoo.model.Animal;
import org.springframework.stereotype.Service;

@Service
public class VetService {
    public boolean checkHealth(Animal animal) {
        //Не очень понятно как надо проверять, что животное здоровое.
        return animal.isHealthy();
    }
}