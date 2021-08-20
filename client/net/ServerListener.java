package client.net;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerListener extends Thread {

    private final SocketClient socketClient;

    public ServerListener (SocketClient socketClient) {
        this.socketClient = socketClient;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                ObjectInputStream read = socketClient.getRead();

                if (read.available() != 0){
                    String message = (String) socketClient.getRead().readObject();
                    System.out.println(message);
                }
            }

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socketClient.getSocket().close();
                socketClient.getRead().close();
                socketClient.getWrite().close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}