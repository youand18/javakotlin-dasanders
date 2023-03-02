package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class DogTest {
    Dog pug = new Dog();
    @Test
    public void bark() {

        // double x = 4.0;
        pug.setHungerLevel(5.0);
        pug.bark();
        double expect = 4.5;
        double actual = pug.getHungerLevel();
        assertEquals(expect,actual,0.0);
    }
    @Test
    public void isHungry() {
        pug.setHungerLevel(10.0);
        boolean expect = false;
        boolean actual = pug.isHungry();
        assertEquals(expect, actual);
    }



}