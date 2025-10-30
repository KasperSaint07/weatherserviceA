package kz.aitu.weather.core.observer;

import kz.aitu.weather.core.model.WeatherData;

public class AnalyticsObserver implements WeatherObserver {
    private WeatherData last;

    @Override
    public void onWeatherUpdate(WeatherData data) {
        if (last != null) {
            double dTemp = data.getTemperature() - last.getTemperature();
            if (Math.abs(dTemp) >= 3.0) {
                System.out.printf("[Analytics]Temp jump: %.1f°C → %.1f°C (Δ=%.1f)%n",
                        last.getTemperature(), data.getTemperature(), dTemp);
            } else {
                System.out.printf("[Analytics] Trend OK: %s%n", data);
            }
        } else {
            System.out.printf("[Analytics] First sample: %s%n", data);
        }
        last = data;
    }
}
