package com.example.zoo.service;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class ConsoleService {
    private final Scanner scanner = new Scanner(System.in);

    public void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public boolean readBoolean(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextBoolean()) {
            System.out.println("Invalid input. Please enter 'true' or 'false'.");
            scanner.next();
        }
        boolean input = scanner.nextBoolean();
        scanner.nextLine();
        return input;
    }

    public void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}