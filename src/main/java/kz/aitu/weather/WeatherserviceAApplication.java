package kz.aitu.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WeatherserviceAApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherserviceAApplication.class, args);
    }
}
