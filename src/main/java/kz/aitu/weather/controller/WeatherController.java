package kz.aitu.weather.controller;

import kz.aitu.weather.core.model.WeatherData;
import kz.aitu.weather.core.history.HistoryService;
import kz.aitu.weather.service.WeatherStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherStationService service;
    private final HistoryService history;

    public WeatherController(WeatherStationService service, HistoryService history) {
        this.service = service;
        this.history = history;
    }

    @GetMapping("/current")
    public WeatherData getCurrentWeather() {
        return service.getCurrentData();
    }

    @RequestMapping(value = "/update", method = {GET, POST})
    public WeatherData updateWeather() {
        return service.updateWeather();
    }

    @RequestMapping(value = "/strategy", method = {GET, POST})
    public String changeStrategy(@RequestParam String type) {
        return service.changeStrategy(type);
    }

    @RequestMapping(value = "/manual", method = {GET, POST})
    public WeatherData setManual(@RequestParam double t,
                                 @RequestParam double h,
                                 @RequestParam double p) {
        return service.setManualData(t, h, p);
    }

    @GetMapping("/history")
    public List<WeatherData> history(@RequestParam(defaultValue = "50") int limit) {
        return history.last(limit);
    }

    @GetMapping("/stats")
    public HistoryService.Stats stats() {
        return history.stats();
    }
}
