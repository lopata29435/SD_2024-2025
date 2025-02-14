package com.example.zoo.service;

import com.example.zoo.model.Monkey;
import com.example.zoo.model.Rabbit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.zoo.service.ZooService;
import com.example.zoo.service.VetService;
import com.example.zoo.service.ConsoleService;
import com.example.zoo.service.TablePrinterService;
import com.example.zoo.controller.MenuController;
import com.example.zoo.model.Animal;
import com.example.zoo.model.Monkey;

class VetServiceTest {

    private final VetService vetService = new VetService();

    @Test
    void testCheckHealth_HealthyAnimal() {
        Monkey monkey = new Monkey(5, 1, true, 7);
        boolean isHealthy = vetService.checkHealth(monkey);
        assertThat(isHealthy).isTrue();
    }

    @Test
    void testCheckHealth_UnhealthyAnimal() {
        Rabbit rabbit = new Rabbit(3, 2, false, 8);
        boolean isHealthy = vetService.checkHealth(rabbit);
        assertThat(isHealthy).isFalse();
    }
}
