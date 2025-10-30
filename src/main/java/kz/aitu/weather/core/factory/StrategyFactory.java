package kz.aitu.weather.core.factory;

import kz.aitu.weather.core.strategy.ManualInputStrategy;
import kz.aitu.weather.core.strategy.RealTimeSensorStrategy;
import kz.aitu.weather.core.strategy.ScheduledBatchStrategy;
import kz.aitu.weather.core.strategy.UpdateStrategy;

public class StrategyFactory {

    //realtime | batch | manual
    public static UpdateStrategy create(String type) {
        if (type == null) throw new IllegalArgumentException("Strategy type must not be null");
        return switch (type.toLowerCase()) {
            case "realtime"           -> new RealTimeSensorStrategy();
            case "batch", "scheduled" -> new ScheduledBatchStrategy();
            case "manual"             -> new ManualInputStrategy();
            default -> throw new IllegalArgumentException("Unknown strategy: " + type);
        };
    }

    public static UpdateStrategy manual(double t, double h, double p) {
        return new ManualInputStrategy(t, h, p);
    }
}
