package org.example.business;

import org.example.business.interfaces.CommandInterface;
import org.example.model.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.model.enums.Commands.*;

public class CommandsContainer {
    private final Map<String, CommandInterface> commands = new HashMap<>();

    public CommandsContainer() {
        commands.put(HELLO.command, (message, commandsManager, ip, _) -> commandsManager.handleHelloMessage(message, ip));
        commands.put(GROUP.command, (message, commandsManager, _, _) -> commandsManager.handleCreateGroupMessage(message));
        commands.put(ACK.command, (message, commandsManager, _, wsService) -> commandsManager.handleAckMessage(message, commandsManager, wsService));
        commands.put(ACKGROUP.command, (message, commandsManager, ip, wsService) -> commandsManager.handleAckGroupMessage(message, ip, commandsManager, wsService));
        commands.put(BYE.command, (message, commandsManager, _, wsService) -> commandsManager.handleByeMessage(message, commandsManager, wsService));
        commands.put(BYEBYE.command, (message, commandsManager, _, wsService) -> commandsManager.handleByeByeMessage(message, commandsManager, wsService));
        commands.put(INVITE.command, (message, commandsManager, ip, _) -> commandsManager.handleInviteMessage(message, ip));
        commands.put(UPDATE.command, (message, commandsManager, _, wsService) -> commandsManager.handleUpdateMessage(message, commandsManager, wsService));
    }

    public CommandInterface getCommand(Message message) {
        return commands.get(message.getMessage());
    }

    public static List<String> getAllCommands() {
        return List.of(HELLO.command, GROUP.command, ACK.command, ACKGROUP.command, BYE.command, BYEBYE.command, INVITE.command, UPDATE.command);
    }
}
