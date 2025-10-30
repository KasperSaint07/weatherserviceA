package kz.aitu.weather.core.strategy;

import kz.aitu.weather.core.model.WeatherData;

public interface UpdateStrategy {
    WeatherData fetchData();
}
