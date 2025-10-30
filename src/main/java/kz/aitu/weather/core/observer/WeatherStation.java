package kz.aitu.weather.core.observer;

import kz.aitu.weather.core.model.WeatherData;
import kz.aitu.weather.core.strategy.UpdateStrategy;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements WeatherSubject {

    // Singleton
    private static WeatherStation INSTANCE;


    public static synchronized WeatherStation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WeatherStation();
        }
        return INSTANCE;
    }

    // State & Observers
    private final List<WeatherObserver> observers = new ArrayList<>();
    private UpdateStrategy strategy;           // текущая стратегия
    private WeatherData latestData;            // последнее состояние

    private WeatherStation() {}

    @Override
    public void registerObserver(WeatherObserver o) {
        if (o != null && !observers.contains(o)) observers.add(o);
    }

    @Override
    public void removeObserver(WeatherObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        if (latestData == null) return;
        for (WeatherObserver o : observers) {
            o.onWeatherUpdate(latestData);
        }
    }

    // API станции
    public void setStrategy(UpdateStrategy strategy) {
        this.strategy = strategy;
    }

    public UpdateStrategy getStrategy() {
        return this.strategy;
    }

    public void updateWeather() {
        if (strategy == null) {
            throw new IllegalStateException("UpdateStrategy is not set");
        }
        this.latestData = strategy.fetchData();
        notifyObservers();
    }

    public WeatherData getLatestData() {
        return latestData;
    }

    public void addObserver(WeatherObserver o) {
        observers.add(o);
    }



}
