package kz.aitu.weather.core.observer;

import kz.aitu.weather.core.model.WeatherData;

public class DisplayBoardObserver implements WeatherObserver {
    private final String location;

    public DisplayBoardObserver(String location) {
        this.location = location;
    }

    @Override
    public void onWeatherUpdate(WeatherData data) {
        System.out.printf("[Display:%s]  %s%n", location, data);
    }
}
