package com.example.zoo.model;

public abstract class Herbo extends Animal {
    private int kindnessLevel;

    public Herbo(int food, int number, boolean isHealthy, int kindnessLevel) {
        super(food, number, isHealthy);
        this.kindnessLevel = kindnessLevel;
    }

    public int getKindnessLevel() {
        return kindnessLevel;
    }

    public boolean canInteractWithVisitors() {
        return kindnessLevel > 5;
    }
}