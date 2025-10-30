package kz.aitu.weather.core.observer;

import kz.aitu.weather.core.history.HistoryService;
import kz.aitu.weather.core.model.WeatherData;

public class HistoryObserver implements WeatherObserver {
    private final HistoryService history;
    public HistoryObserver(HistoryService history) {
        this.history = history;
    }
    @Override
    public void onWeatherUpdate(WeatherData data) {
        history.add(data);
    }
}
