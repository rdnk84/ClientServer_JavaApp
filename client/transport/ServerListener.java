package client.transport;

import client.transport.messages.MessageReceiver;

import java.io.IOException;
import java.io.ObjectInputStream;


public class ServerListener extends Thread {

    private final SocketConnection socketConnection;

    public ServerListener(SocketConnection socketConnection) {
        this.socketConnection = socketConnection;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object message = MessageReceiver.receiveMessage(socketConnection);

                //теперь то,что выловил Клиент со стороны сервера нам надо вывести в консоли на экран
                System.out.println(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socketConnection.getSocket().close();
                socketConnection.getIn().close();
                socketConnection.getOut().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}