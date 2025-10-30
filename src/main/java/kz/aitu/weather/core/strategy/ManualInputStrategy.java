package kz.aitu.weather.core.strategy;

import kz.aitu.weather.core.model.WeatherData;

public class ManualInputStrategy implements UpdateStrategy {
    private WeatherData next;

    public ManualInputStrategy() {
        this.next = new WeatherData(0.0, 0.0, 1013.0);
    }
    public ManualInputStrategy(double t, double h, double p) {
        this.next = new WeatherData(t, h, p);
    }
    public void set(double t, double h, double p) {
        this.next = new WeatherData(t, h, p);
    }
    @Override public WeatherData fetchData() { return next; }
}
