package client.transport.messages;

import client.transport.SocketConnection;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageSender {

    public static void sendMessage(Message message, SocketConnection socketConnection) throws IOException {
        final ObjectOutputStream out = socketConnection.getOut();
        out.writeObject(message);
        out.flush();
    }

}
