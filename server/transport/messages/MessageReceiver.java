package server.transport.messages;


import server.model.ClientInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageReceiver {


    //не понимаю что здесь (не конструктор, и метод, но раз возвращает - значит метод)
    public static Object receiveMessage(ClientInfo clientInfo) throws IOException, ClassNotFoundException {
        ObjectInputStream in = clientInfo.getIn();
        Object message = in.readObject();
       // System.out.println(message);

        return message;
    }

}
