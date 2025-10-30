package kz.aitu.weather.core.observer;

import kz.aitu.weather.core.model.WeatherData;

public class PhoneAppObserver implements WeatherObserver {
    private final String user;

    public PhoneAppObserver(String user) {
        this.user = user;
    }

    @Override
    public void onWeatherUpdate(WeatherData data) {
        System.out.printf("[PhoneApp:%s]  New weather: %s%n", user, data);
    }
}
