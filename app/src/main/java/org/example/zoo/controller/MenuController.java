package com.example.zoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

import com.example.zoo.model.Animal;
import com.example.zoo.model.Monkey;
import com.example.zoo.model.Rabbit;
import com.example.zoo.model.Tiger;
import com.example.zoo.model.Wolf;
import com.example.zoo.service.ZooService;
import com.example.zoo.service.ConsoleService;
import com.example.zoo.service.TablePrinterService;

@Controller
public class MenuController {
    private ZooService zooService;
    private ConsoleService consoleService;
    private TablePrinterService  tablePrinterService ;

    @Autowired
    public MenuController(ZooService zooService, ConsoleService consoleService, TablePrinterService tablePrinterService) {
        this.zooService = zooService;
        this.consoleService = consoleService;
        this.tablePrinterService = tablePrinterService;
    }

    public void startMenu() {
        int choice;

        do {
            consoleService.clearConsole();
            printWelcomeMessage();
            printMenu();
            choice = consoleService.readInt("\nPlease select an option: ");

            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    printTotalFoodConsumption();
                    break;
                case 3:
                    printAnimalsForContactZoo();
                    break;
                case 4:
                    printInventorization();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (choice != 5) {
                consoleService.waitForEnter();
            }

        } while (choice != 5);

        consoleService.close();
    }

    private void printWelcomeMessage() {
        System.out.println("====================================");
        System.out.println("  Welcome to the Zoo Management System!");
        System.out.println("====================================");
    }

    private void printMenu() {
        List<String> headers = List.of("Option", "Description");
        List<List<String>> rows = List.of(
                List.of("1", "Add Animal"),
                List.of("2", "Print Total Food Consumption"),
                List.of("3", "Print Animals for Contact Zoo"),
                List.of("4", "Print Inventorization"),
                List.of("5", "Exit")
        );

        System.out.println("\n====== MENU ======");
        tablePrinterService.printTable(headers, rows);
    }

    private void addAnimal() {
        consoleService.clearConsole();
        System.out.println("Available animals:\n");
    
        List<String> headers = List.of("ID", "Animal");
        List<List<String>> rows = List.of(
                List.of("1", "Monkey"),
                List.of("2", "Rabbit"),
                List.of("3", "Tiger"),
                List.of("4", "Wolf")
        );
    
        tablePrinterService.printTable(headers, rows);
    
        System.out.println("\nAdding a new animal...\n");
    
        int animalType = consoleService.readInt("Enter the animal ID: ");
        int food = consoleService.readInt("Enter food amount: ");
        int number = consoleService.readInt("Enter number: ");
        boolean isHealthy = consoleService.readBoolean("Is the animal healthy? (true/false): ");
        int kindnessLevel = 0;
        Animal animal = null;
    
        switch (animalType) {
            case 1:
                kindnessLevel = consoleService.readInt("Enter kindness level (1-10): ");
                animal = new Monkey(food, number, isHealthy, kindnessLevel);
                break;
            case 2:
                kindnessLevel = consoleService.readInt("Enter kindness level (1-10): ");
                animal = new Rabbit(food, number, isHealthy, kindnessLevel);
                break;
            case 3:
                animal = new Tiger(food, number, isHealthy);
                break;
            case 4:
                animal = new Wolf(food, number, isHealthy);
                break;
            default:
                System.out.println("Invalid animal ID.");
                return;
        }
    
        zooService.addAnimal(animal);
        System.out.println(animal.getClass().getSimpleName() + " added successfully!");
    }

    private void printTotalFoodConsumption() {
        System.out.println("\nTotal Food Consumption:");
        System.out.println("All animals consume " + zooService.getTotalFoodConsumption() + " kg of food per day.");
    }

    private void printAnimalsForContactZoo() {
        consoleService.clearConsole();
        List<List<String>> rows = zooService.getAnimalsForContactZoo();
    
        if (rows.isEmpty()) {
            System.out.println("\nNo animals available for the contact zoo.");
            return;
        }
    
        List<String> headers = List.of("Animal", "Food (kg)", "Number", "Kindness Level");
    
        System.out.println("\nAnimals for Contact Zoo:");
        tablePrinterService.printTable(headers, rows);
    }

    private void printInventorization() {
        consoleService.clearConsole();
        List<List<String>> inventory = zooService.getZooInventory();
    
        if (inventory.isEmpty()) {
            System.out.println("\nNo items in the inventory.");
            return;
        }
    
        List<String> headers = List.of("Item", "Number");
        
        System.out.println("\nInventorization:");
        tablePrinterService.printTable(headers, inventory);
    }
}