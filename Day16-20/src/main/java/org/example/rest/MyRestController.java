package org.example.rest;

import org.example.business.CommandsContainer;
import org.example.business.CommandsManager;
import org.example.model.Constants;
import org.example.model.Message;
import org.example.udp.UdpClient;
import org.springframework.web.bind.annotation.*;

import java.net.DatagramPacket;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MyRestController {

    private final UdpClient udpClient;

    public MyRestController(UdpClient udpClient) {
        this.udpClient = udpClient;
    }

    @GetMapping("/getTeam")
    public String sayHello() {
        System.out.println("Hello from team 1");
        return "Hello from team 1";
    }

    @PostMapping("/hello/{receiver}")
    public DatagramPacket sendHello(@PathVariable("receiver") String receiver) {
        Message message = new Message(Constants.NICKNAME, receiver, "!hello", "", null);
        return udpClient.sendMessage(message);
    }

    @GetMapping("/me")
    public String getMyTeam() {
        System.out.println("My team is: " + Constants.NICKNAME);
        return Constants.NICKNAME;
    }

    @GetMapping("/getCommands")
    public List<String> getAllCommands() {
        return CommandsContainer.getAllCommands();
    }

    @GetMapping("/getGroups")
    public List<String> getGroups() {
        return CommandsManager.getGroups();
    }


}
