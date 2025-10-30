package kz.aitu.weather.config;

import kz.aitu.weather.core.observer.WeatherStation;
import kz.aitu.weather.core.strategy.ScheduledBatchStrategy;
import kz.aitu.weather.core.strategy.UpdateStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchTicker {
    private static final Logger log = LoggerFactory.getLogger(BatchTicker.class);

    private final WeatherStation station = WeatherStation.getInstance();

    @Scheduled(fixedRate = 5000)
    public void tick() {
        UpdateStrategy s = station.getStrategy();
        if (s instanceof ScheduledBatchStrategy) {
            station.updateWeather();
            log.info("[BatchTicker] auto update triggered");
        }
    }
}
