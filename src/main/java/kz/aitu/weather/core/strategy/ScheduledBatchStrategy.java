package kz.aitu.weather.core.strategy;

import kz.aitu.weather.core.model.WeatherData;


public class ScheduledBatchStrategy implements UpdateStrategy {

    @Override
    public WeatherData fetchData() {
        long t = System.currentTimeMillis() / 1000;
        double baseTemp = 18.0 + (t % 600) / 600.0 * 4.0; // 18..22
        double baseHum  = 45.0 + (t % 900) / 900.0 * 10.0; // 45..55
        double basePres = 1005.0 + (t % 1200) / 1200.0 * 4.0; // 1005..1009

        return WeatherData.builder()
                .temperature(round1(baseTemp))
                .humidity(round1(baseHum))
                .pressure(round1(basePres))
                .build();
    }

    private double round1(double v) {
        return Math.round(v * 10.0) / 10.0;
    }
}
