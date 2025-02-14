package com.example.zoo.model;

public class Thing implements IInventory {
    private int number;

    public Thing(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}