package org.example.model;

import lombok.*;

import java.util.List;

@Getter
public class Message {
    private String sender;
    private String receiver;
    private String message;
    private String group;
    private List<String> ips;

    public Message(String sender, String receiver, String message, String group, List<String> ips) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.group = group;
        this.ips = ips;
    }

    public Message() {}

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }
}
