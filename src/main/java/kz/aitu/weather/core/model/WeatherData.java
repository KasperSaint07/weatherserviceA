package kz.aitu.weather.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class WeatherData {
    private final double temperature; // °C
    private final double humidity;    // %
    private final double pressure;    // hPa

    public WeatherData(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}
