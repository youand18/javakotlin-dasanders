package com.example.jackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


public class DogJackson {
    public static String dogToJson(Dog dog) throws IOException {
        if (dog == null) {
            throw new IOException("Dog object is null");
        }
        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(dog);
        return json;
    }

    public static void main(String[] args) throws IOException {
        Dog dog1 = new Dog("Rufus", 5);
        String json = dogToJson(dog1);
    }
}


