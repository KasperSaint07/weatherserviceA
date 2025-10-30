package kz.aitu.weather.core.strategy;

import kz.aitu.weather.core.model.WeatherData;

import java.util.concurrent.ThreadLocalRandom;


public class RealTimeSensorStrategy implements UpdateStrategy {

    @Override
    public WeatherData fetchData() {
        double t = ThreadLocalRandom.current().nextDouble(-15.0, 35.0);   // °C
        double h = ThreadLocalRandom.current().nextDouble(20.0, 90.0);    // %
        double p = ThreadLocalRandom.current().nextDouble(985.0, 1035.0); // hPa
        return WeatherData.builder()
                .temperature(round1(t))
                .humidity(round1(h))
                .pressure(round1(p))
                .build();
    }

    private double round1(double v) {
        return Math.round(v * 10.0) / 10.0;
    }
}
