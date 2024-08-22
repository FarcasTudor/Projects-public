package org.example.websocket;

import org.example.business.CommandsManager;
import org.example.udp.UdpClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final CommandsManager commandManager;
    private final UdpClient udpClient;
    private final WebSocketService webSocketService;

    public WebSocketConfig(CommandsManager commandManager, UdpClient udpClient, WebSocketService webSocketService) {
        this.commandManager = commandManager;
        this.udpClient = udpClient;
        this.webSocketService = webSocketService;
        udpClient.start();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(commandManager, udpClient, webSocketService), "/ws").setAllowedOrigins("*");
    }
}
