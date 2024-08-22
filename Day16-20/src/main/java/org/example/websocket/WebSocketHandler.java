package org.example.websocket;

import com.google.gson.Gson;
import org.example.business.CommandsManager;
import org.example.model.Message;
import org.example.udp.UdpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final Gson gson = new Gson();
    private final CommandsManager commandManager;
    private final UdpClient udpClient;
    private final WebSocketService webSocketService;

    @Autowired
    public WebSocketHandler(CommandsManager commandManager, UdpClient udpClient, WebSocketService webSocketService) {
        this.commandManager = commandManager;
        this.udpClient = udpClient;
        this.webSocketService = webSocketService;
    }

    public void afterConnectionEstablished(WebSocketSession session) {
        webSocketService.addSession(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        Message msg = gson.fromJson(payload, Message.class);

        if (CommandsManager.senderIpMap.get(msg.getReceiver()) != null) {
            commandManager.handleMessage(msg, CommandsManager.senderIpMap.get(msg.getReceiver()), webSocketService);
        } else {
            commandManager.handleMessage(msg, "", webSocketService);
        }
        
        if (msg.getReceiver() != null) {
            System.out.println("Sending message to UDP client");
            udpClient.sendMessage(msg);
        }
    }

}
