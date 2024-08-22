package org.example.udp;

import org.example.model.Constants;
import org.example.model.Message;

public class UdpUtils {
    public static String[] parseMessage(String cmd) {
        String[] tokens = cmd.split(" ");
        return new String[] {
                tokens[0],
                tokens[1],
                !tokens[tokens.length - 1].equals(tokens[1]) ? tokens[tokens.length - 1] : ""
        };
    }
}
