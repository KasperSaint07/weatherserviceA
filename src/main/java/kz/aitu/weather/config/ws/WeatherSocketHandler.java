package kz.aitu.weather.config.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class WeatherSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
        System.out.println("[WS] disconnected: " + session.getId());
    }

    public void broadcast(String json) {
        for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
                try { s.sendMessage(new TextMessage(json)); }
                catch (IOException ignored) {}
            }
        }
    }

    public int size() { return sessions.size(); }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("[WS] connected: " + session.getId());

        try {
            var latest = kz.aitu.weather.core.observer.WeatherStation.getInstance().getLatestData();
            if (latest != null) {
                var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                session.sendMessage(new TextMessage(mapper.writeValueAsString(latest)));
            }
        } catch (Exception ignored) {}
    }

}
