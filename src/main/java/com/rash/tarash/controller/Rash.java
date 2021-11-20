package com.rash.tarash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
@RequestMapping("/rash")
public class Rash {

    @GetMapping("/ta")
    public String printMsg() {
        return loadProperties();
    }

    private String loadProperties() {
        try (InputStream inputStream = new FileInputStream("src/main/resources/sample.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            if (properties.getProperty("name") == null || properties.getProperty("name").isEmpty()) {
                properties.setProperty("name", "Empty string from property file");
            }
            System.out.println("Date from the prop file: " +properties.getProperty("name"));
            String output = "<h1> "+properties.getProperty("name")+"</h1>";
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
