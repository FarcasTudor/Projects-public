package org.example.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketService {
    private final List<WebSocketSession> sessions;

    public void sendMessage(String s) {
        for (WebSocketSession session : sessions) {
            try {
                if (session == null || !session.isOpen()) {
                    continue;
                }
                System.out.println("Sending message to WebSocket client");
                session.sendMessage(new TextMessage(s));
                System.out.println("Message sent to WebSocket client");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addSession(WebSocketSession session) {
        this.sessions.add(session);
    }

    public WebSocketService() {
        this.sessions = new ArrayList<>();
    }

}
