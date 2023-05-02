package com.example.javaspace;

public class Dog {
    private String name;
    private int age;
    Object x = null;

    public Dog(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }
    boolean alive = true;
    double hungerLevel = 5.0;
    boolean isHungry() {
        if (hungerLevel <= 0.0){
            System.out.println("The dog is dead. Congratulations");
            alive = false;
            return true;
        } else if (hungerLevel < 2.0){
            System.out.println("The dog is hungry, please feed it.");
            return true;
        } else {
            return false;
        }
    }
    void feedTheDog(){
        hungerLevel += 1.0;
    }
    void setHungerLevel(double x){
        hungerLevel = x;
    }
    double getHungerLevel(){
        return hungerLevel;
    }

    String bark() {
        hungerLevel -= 0.5;
        return "Woof";
    }

    void goForAWalk() {
        hungerLevel -= 1.0;
    }
}
