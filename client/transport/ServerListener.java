package client.transport;

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
                ObjectInputStream in = socketConnection.getIn();
                Object message = in.readObject();
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