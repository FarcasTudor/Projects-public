package org.example.business;

import org.example.business.interfaces.CommandInterface;
import org.example.model.Constants;
import org.example.model.Message;
import org.example.tcp.TcpServer;
import org.example.websocket.WebSocketService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Scope("singleton")
public class CommandsManager {
    private final ServerSocket serverSocket = new ServerSocket(Constants.PORT);
    public static final Map<String, String> senderIpMap = new ConcurrentHashMap<>();
    private final Set<String> receivers = new ConcurrentSkipListSet<>();
    public final Map<String, Socket> receiversSocketMap = new ConcurrentHashMap<>();
    private final Map<String, List<String>> groupIpsMap = new ConcurrentHashMap<>();
    private final Queue<String> pendingInvitations = new ConcurrentLinkedQueue<>();
    private final CommandsContainer commandsContainer;

    public CommandsManager() throws IOException {
        commandsContainer = new CommandsContainer();
    }

    public static List<String> getGroups() {
        return new ArrayList<>(senderIpMap.keySet());
    }

    public void handleMessage(Message message, String ip, WebSocketService webSocketService) throws IOException {
        CommandInterface commandInterface = commandsContainer.getCommand(message);
        if (commandInterface != null) {
            commandInterface.execute(message, this, ip, webSocketService);
        }
    }

    public void handleHelloMessage(Message message, String ip) {
        senderIpMap.putIfAbsent(message.getSender(), ip);
    }

    public void handleAckMessage(Message message, CommandsManager commandsManager, WebSocketService wsService) throws IOException {
        receivers.add(message.getReceiver());
        if(message.getReceiver().equals(Constants.NICKNAME)) {
            handleReceivedAck(message, commandsManager, wsService);
        } else {
            handleSentAck(message, senderIpMap.get(message.getReceiver()), commandsManager, wsService);
        }
    }

    private void handleSentAck(Message message, String ip, CommandsManager commandsManager, WebSocketService wsService) throws IOException {
        if(!receiversSocketMap.containsKey(message.getReceiver())) {
            Socket socket = new Socket(ip, Constants.PORT);
            receiversSocketMap.put(message.getReceiver(), socket);
            initiateTcpConnection(socket, commandsManager, wsService);
        }
    }

    private void handleReceivedAck(Message message, CommandsManager commandsManager, WebSocketService wsService) throws IOException {
        if(!receiversSocketMap.containsKey(message.getSender())) {
            Socket socket = serverSocket.accept();
            receiversSocketMap.put(message.getSender(), socket);
            initiateTcpConnection(socket, commandsManager, wsService);
        }
    }

    private void initiateTcpConnection(Socket socket, CommandsManager commandsManager, WebSocketService wsService) {
        TcpServer tcpServer = new TcpServer(socket, commandsManager, wsService);
        System.out.println("Starting TCP server...");
        tcpServer.start();
        System.out.println("TCP server started.");
    }

    public void handleCreateGroupMessage(Message message) {
        groupIpsMap.put(message.getGroup(), new ArrayList<>());
        addSenderToGroup(message);
        System.out.println("The map contains the next groups:  " + groupIpsMap);
    }

    public void handleAckGroupMessage(Message message, String ip, CommandsManager commandsManager, WebSocketService wsService) throws IOException {
        System.out.println("MESSAGE: " + message);
        if(isFromCurrentUser(message)) {
            handleSentAck(message, ip, commandsManager, wsService);
        } else {
            handleReceivedAckGroup(message, commandsManager, wsService);
        }
    }

    private void handleReceivedAckGroup(Message message, CommandsManager commandsManager, WebSocketService wsService) throws IOException {
        addSenderToGroup(message);
        handleReceivedAck(message, commandsManager, wsService);
    }

    private void addSenderToGroup(Message message) {
        groupIpsMap.get(message.getGroup())
                .add(message.getSender());
    }

    public void handleByeMessage(Message message, CommandsManager commandsManager, WebSocketService wsService) {

    }

    public void handleByeByeMessage(Message message, CommandsManager commandsManager, WebSocketService wsService) {

    }

    public void handleInviteMessage(Message message, String ip) {
        if (isFromCurrentUser(message)) {
            pendingInvitations.add(message.getReceiver());
        } else {
            handleReceivedInvite(message, ip);
        }

    }

    private static boolean isFromCurrentUser(Message message) {
        return message.getSender().equals(Constants.NICKNAME);
    }

    private void handleReceivedInvite(Message message, String ip) {
        senderIpMap.putIfAbsent(message.getSender(), ip);
        groupIpsMap.put(message.getGroup(), new ArrayList<>());
        addSenderToGroup(message);
    }

    public void handleUpdateMessage(Message message, CommandsManager commandsManager, WebSocketService wsService) {

    }
}
