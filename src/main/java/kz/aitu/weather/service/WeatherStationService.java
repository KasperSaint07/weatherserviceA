package kz.aitu.weather.service;

import kz.aitu.weather.core.factory.StrategyFactory;
import kz.aitu.weather.core.model.WeatherData;
import kz.aitu.weather.core.observer.WeatherStation;
import kz.aitu.weather.core.strategy.UpdateStrategy;
import org.springframework.stereotype.Service;

@Service
public class WeatherStationService {

    private final WeatherStation station = WeatherStation.getInstance();

    // 1️⃣ Получить текущие данные
    public WeatherData getCurrentData() { return station.getLatestData(); }

    public WeatherData updateWeather() {
        station.updateWeather();
        return station.getLatestData();
    }

    public String changeStrategy(String type) {
        UpdateStrategy strategy = StrategyFactory.create(type);
        station.setStrategy(strategy);
        return "Strategy changed to: " + type;
    }

    // Ручной ввод значений и мгновенное оповещение наблюдателей
    public WeatherData setManualData(double t, double h, double p) {
        UpdateStrategy manual = StrategyFactory.manual(t, h, p);
        station.setStrategy(manual);
        station.updateWeather();
        return station.getLatestData();
    }
}
