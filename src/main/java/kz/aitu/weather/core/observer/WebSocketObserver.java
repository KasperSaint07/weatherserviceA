package kz.aitu.weather.core.observer;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.aitu.weather.config.ws.WeatherSocketHandler;
import kz.aitu.weather.core.model.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketObserver implements WeatherObserver {
    private static final Logger log = LoggerFactory.getLogger(WebSocketObserver.class);

    private final WeatherSocketHandler handler;
    private final ObjectMapper mapper = new ObjectMapper();

    public WebSocketObserver(WeatherSocketHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onWeatherUpdate(WeatherData data) {
        try {
            String json = mapper.writeValueAsString(data);
            handler.broadcast(json);
            log.info("[WS] pushed to {} client(s): {}", handler.size(), json);
        } catch (Exception e) {
            log.error("[WS] serialization error", e);
        }
    }
}
