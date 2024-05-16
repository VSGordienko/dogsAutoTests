package org.example.util;

import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
@NoArgsConstructor
public class TestConfiguration {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static String getDogApiUrl() {
        return properties.getProperty("dogApiUrl");
    }

    public static String getDogApiKey() {
        return properties.getProperty("dogApiKey");
    }

    public static String getDogApiValue() {
        return properties.getProperty("dogApiValue");
    }

    public static String getDogApiUserId() {
        return properties.getProperty("user_id");
    }

    public static String getDogApiImage(String image_id) {
        return String.format("https://cdn2.thedogapi.com/images/%s.jpg", image_id);
    }

}
