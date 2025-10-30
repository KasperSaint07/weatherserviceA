package kz.aitu.weather.core.strategy;

public enum StrategyType {
    REALTIME, BATCH, MANUAL;

    public static StrategyType from(String raw) {
        if (raw == null) throw new IllegalArgumentException("Strategy type must not be null");
        return switch (raw.trim().toLowerCase()) {
            case "realtime", "real", "rt" -> REALTIME;
            case "batch", "scheduled", "sched" -> BATCH;
            case "manual", "man" -> MANUAL;
            default -> throw new IllegalArgumentException("Unknown strategy type: " + raw);
        };
    }
}
