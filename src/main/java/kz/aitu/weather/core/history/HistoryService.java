package kz.aitu.weather.core.history;

import kz.aitu.weather.core.model.WeatherData;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class HistoryService {
    private final Deque<WeatherData> ring = new ArrayDeque<>();
    private final int capacity;

    public HistoryService() {
        this(500);
    }
    public HistoryService(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void add(WeatherData d) {
        if (ring.size() == capacity) ring.removeFirst();
        ring.addLast(d);
    }

    public synchronized List<WeatherData> last(int limit) {
        int n = Math.min(limit, ring.size());
        var tmp = new ArrayList<WeatherData>(n);
        var it = ring.descendingIterator();
        while (it.hasNext() && tmp.size() < n) tmp.add(it.next());
        return tmp;
    }

    public static class Stats {
        public int count;
        public double avgTemp, minTemp, maxTemp;
        public double avgHum,  minHum,  maxHum;
        public double avgPres, minPres, maxPres;
    }

    public synchronized Stats stats() {
        var s = new Stats();
        if (ring.isEmpty()) return s;

        s.count = ring.size();
        s.minTemp = s.minHum = s.minPres = Double.POSITIVE_INFINITY;
        s.maxTemp = s.maxHum = s.maxPres = Double.NEGATIVE_INFINITY;

        for (var d : ring) {
            s.avgTemp += d.getTemperature();
            s.avgHum  += d.getHumidity();
            s.avgPres += d.getPressure();

            s.minTemp = Math.min(s.minTemp, d.getTemperature());
            s.maxTemp = Math.max(s.maxTemp, d.getTemperature());
            s.minHum  = Math.min(s.minHum , d.getHumidity());
            s.maxHum  = Math.max(s.maxHum , d.getHumidity());
            s.minPres = Math.min(s.minPres, d.getPressure());
            s.maxPres = Math.max(s.maxPres, d.getPressure());
        }
        s.avgTemp /= s.count;
        s.avgHum  /= s.count;
        s.avgPres /= s.count;
        return s;
    }
}
