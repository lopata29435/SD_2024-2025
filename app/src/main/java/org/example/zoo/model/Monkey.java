package com.example.zoo.model;

public class Monkey extends Herbo { //Строго говоря непонятно к кому надо его относить. Формально они травоядные бывают, так что пусть пойдет сюда
    public Monkey(int food, int number, boolean isHealthy, int kindnessLevel) {
        super(food, number, isHealthy, kindnessLevel);
    }
}
