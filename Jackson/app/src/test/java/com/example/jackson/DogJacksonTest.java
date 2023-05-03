package com.example.jackson;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DogJacksonTest {

    @Test
    public void testDogToJson() throws Exception {
        Dog dog1 = new Dog("Tommy", 4);
        ObjectMapper map = new ObjectMapper();
        String expectedOutput = map.writeValueAsString(dog1);
        String actualOutput = DogJackson.dogToJson(dog1);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDogEmptyName() throws Exception{
        Dog dog2 = new Dog("", -6);
        ObjectMapper map = new ObjectMapper();
        String expectedOutput = map.writeValueAsString(dog2);
        String actualOutput = DogJackson.dogToJson(dog2);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDogNullName() throws Exception{
        Dog dog3 = new Dog(null, 0);
        ObjectMapper map = new ObjectMapper();
        String expectedOutput = map.writeValueAsString(dog3);
        String actualOutput = DogJackson.dogToJson(dog3);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test(expected = IOException.class)
    public void testDogToJsonIOException() throws IOException{
        Dog dog4 = null;
        String json = DogJackson.dogToJson(dog4);
    }
}
