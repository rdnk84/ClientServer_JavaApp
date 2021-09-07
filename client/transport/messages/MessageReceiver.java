package client.transport.messages;


import client.transport.SocketConnection;

import java.io.IOException;
import java.io.ObjectInputStream;

public class MessageReceiver {


    //не понимаю что здесь (не конструктор, и метод, но раз возвращает - значит метод)
    public static Object receiveMessage(SocketConnection socketConnection) throws IOException, ClassNotFoundException {
        ObjectInputStream in = socketConnection.getIn();
        Object message = in.readObject();
       // System.out.println(message);

        return message;
    }

}
