package server.transport.messages;

import client.transport.messages.Message;
import server.model.ClientInfo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MessageSender {

    public static void sendMessage(Message message, ClientInfo clientInfo) throws IOException {
        ObjectOutputStream out = clientInfo.getOut();
        out.writeObject(message);
        out.flush();
    }
}
