package com.example.zoo;

import com.example.zoo.config.AppConfig;
import com.example.zoo.controller.MenuController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ZooApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MenuController menuController = context.getBean(MenuController.class);
        menuController.startMenu();
    }
}
