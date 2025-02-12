package com.example.zoo.model;

public abstract class Animal implements IAlive, IInventory {
    private int food;
    private int number;
    private boolean isHealthy;

    public Animal(int food, int number, boolean isHealthy) {
        this.food = food;
        this.number = number;
        this.isHealthy = isHealthy;
    }

    @Override
    public int getFood() {
        return food;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public boolean isHealthy() {
        return isHealthy;
    }
}