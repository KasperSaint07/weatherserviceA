    package kz.aitu.weather.core.observer;

    import kz.aitu.weather.core.model.WeatherData;

    public interface WeatherObserver {
        void onWeatherUpdate(WeatherData data);
    }
