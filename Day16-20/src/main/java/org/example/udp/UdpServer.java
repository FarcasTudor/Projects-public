package org.example.udp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.example.business.CommandsManager;
import org.example.model.Constants;
import org.example.model.Message;
import org.example.websocket.WebSocketService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Component
public class UdpServer extends Thread {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CommandsManager commandsManager;
    private final WebSocketService webSocketService;

    public UdpServer(CommandsManager commandsManager, WebSocketService webSocketService) {
        this.commandsManager = commandsManager;
        this.webSocketService = webSocketService;
    }

    @PostConstruct
    public void startServer() {
        start();
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(Constants.PORT)) {
            byte[] buffer = new byte[Constants.BUFFER_SIZE];
            while (true) {
                DatagramPacket packet = receivePacket(socket, buffer);
                assert packet != null;
                System.out.println("Received packet from: " + packet.getAddress().getHostAddress());
                handlePacket(packet, packet.getAddress().getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlePacket(DatagramPacket packet, String ip) {
        try {
            String messageString = new String(packet.getData(), 0, packet.getLength());
            Message messageObj = objectMapper.readValue(messageString, Message.class);
            commandsManager.handleMessage(messageObj, ip, webSocketService);

            System.out.println("Received message: " + messageString);
            webSocketService.sendMessage(messageString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DatagramPacket receivePacket(DatagramSocket socket, byte[] buffer) {
        try {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            return packet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
