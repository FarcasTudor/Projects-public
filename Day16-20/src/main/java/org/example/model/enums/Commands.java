package org.example.model.enums;

public enum Commands {
    HELLO("!hello"),
    GROUP("!group"),
    ACK("!ack"),
    ACKGROUP("!ackg"),
    BYE("!bye"),
    BYEBYE("!byebye"),
    INVITE("!invite"),
    UPDATE("!update");

    public final String command;

    Commands(String s) {
        this.command = s;
    }
}
