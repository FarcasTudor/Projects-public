package org.example.tcp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Message;

import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {
    private final PrintWriter out;
    private final ObjectMapper objectMapper;

    public TcpClient(Socket socket) throws Exception {
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.objectMapper = new ObjectMapper();
    }

    public void sendMessage(Message message) throws Exception {
        String messageString = objectMapper.writeValueAsString(message);
        System.out.println("Sending message: " + messageString);
        out.println(messageString);
        out.flush();
    }
}
