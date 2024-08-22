package org.example.udp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.business.CommandsManager;
import org.example.model.Constants;
import org.example.model.Message;
import org.example.websocket.WebSocketService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

@Component
public class UdpClient extends Thread {
    private final DatagramSocket clientSocket;
    private final ObjectMapper objectMapper;
    private final CommandsManager commandsManager;
    private final WebSocketService wsService;

    public UdpClient(CommandsManager commandsManager, WebSocketService wsService) throws SocketException {
        this.clientSocket = new DatagramSocket();
        this.objectMapper = new ObjectMapper();
        this.commandsManager = commandsManager;
        this.wsService = wsService;
    }

    @Override
    public void run() {
        try(Scanner scanner = new Scanner(System.in)) {
            while(true) {
                String cmd = scanner.nextLine();
                Message message = createMessage(cmd);
                sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }
    }

    public DatagramPacket sendMessage(Message message) {
        try {
            String messageString = objectMapper.writeValueAsString(message);
            byte[] data = messageString.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(Constants.IP), Constants.PORT);
            System.out.println("Sending packet to server: " + packet.getAddress().getHostAddress());
            clientSocket.send(packet);
            return packet;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message createMessage(String cmd) throws IOException {
        String[] parsedMessage = UdpUtils.parseMessage(cmd);
        Message message = new Message(Constants.NICKNAME, parsedMessage[0], parsedMessage[1], parsedMessage[2], null);
        commandsManager.handleMessage(message, "", wsService);
        return message;
    }
}
