package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    private static final Properties properties = new Properties();
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(input);
            String a = properties.getProperty("x-api-key");
            System.out.println(a);
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
//            logger.error("Error loading properties file: " + e.getMessage());
        }
    }


}