package org.example.business.interfaces;

import org.example.business.CommandsManager;
import org.example.model.Message;
import org.example.websocket.WebSocketService;

import java.io.IOException;

public interface CommandInterface {
    void execute(Message message, CommandsManager commandsManager, String ip, WebSocketService webSocketService) throws IOException;
}
