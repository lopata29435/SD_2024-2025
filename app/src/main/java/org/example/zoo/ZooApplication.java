package com.example.zoo;

import com.example.zoo.config.AppConfig;
import com.example.zoo.controller.ZooController;
import com.example.zoo.model.Monkey;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ZooApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ZooController zooController = context.getBean(ZooController.class);

        Monkey monkey = new Monkey(5, 1, true, 7);
        Monkey monkey2 = new Monkey(5, 2, true, 3);
        zooController.addAnimal(monkey);
        zooController.addAnimal(monkey2);

        zooController.printTotalFoodConsumption();
        zooController.printAnimalsForContactZoo();
        zooController.printInventorization();
    }
}