package org.example.tcp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.business.CommandsManager;
import org.example.model.Constants;
import org.example.model.Message;
import org.example.websocket.WebSocketService;

import java.io.IOException;
import java.net.Socket;

public class TcpServer extends Thread {
    private final ObjectMapper objectMapper;
    private final Socket socket;
    private final CommandsManager commandsManager;
    private final WebSocketService webSocketService;

    public TcpServer(Socket socket, CommandsManager commandsManager, WebSocketService webSocketService) {
        this.objectMapper = new ObjectMapper();
        this.socket = socket;
        this.commandsManager = commandsManager;
        this.webSocketService = webSocketService;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String receivedData = readDataFromSocket();
                System.out.println("Received data inside TCP server: " + receivedData);
                if (receivedData == null) break;
                processMessage(receivedData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            handleCloseSocket();
        }
    }

    private String readDataFromSocket() throws IOException {
        byte[] buffer = new byte[Constants.BUFFER_SIZE];
        int bytesToRead = socket.getInputStream().read(buffer);
        return bytesToRead == -1 ? null : new String(buffer, 0, bytesToRead).trim();
    }

    private void processMessage(String receivedData) throws IOException {
        Message messageObj = objectMapper.readValue(receivedData, Message.class);
        System.out.println("Received: " + receivedData + "\n");
        webSocketService.sendMessage(receivedData);
        commandsManager.handleMessage(messageObj, CommandsManager.senderIpMap.get(messageObj.getReceiver()), webSocketService);
        // TODO: updateGroupIpsMap here
    }

    private void handleCloseSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Socket connection was reset: ");
        }
    }
}
