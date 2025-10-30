package kz.aitu.weather.config;

import jakarta.annotation.PostConstruct;
import kz.aitu.weather.config.ws.WeatherSocketHandler;
import kz.aitu.weather.core.history.HistoryService;
import kz.aitu.weather.core.observer.WeatherStation;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import kz.aitu.weather.core.observer.HistoryObserver;
import kz.aitu.weather.core.observer.WebSocketObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class BootstrapConfig {

    private final WeatherSocketHandler socketHandler;

    private static final Logger log = LoggerFactory.getLogger(BootstrapConfig.class);

    public BootstrapConfig(WeatherSocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Bean
    public HistoryService historyService() {
        return new HistoryService(500);
    }

    @PostConstruct
    public void init() {
        WeatherStation station = WeatherStation.getInstance();

        station.addObserver(new WebSocketObserver(socketHandler));


        System.out.println("[Bootstrap] Observers registered, strategy=Realtime (+WebSocket)");
    }

    // пример фрагмента в BootstrapConfig
    @Bean
    public CommandLineRunner bootstrap(WeatherSocketHandler wsHandler,
                                       HistoryService history) {
        return args -> {
            var station = WeatherStation.getInstance();
            station.registerObserver(new WebSocketObserver(wsHandler));
            station.registerObserver(new HistoryObserver(history));
            System.out.println("[Bootstrap] ✅ Observers registered, strategy=Realtime (+WebSocket+History)");
        };
    }

}
