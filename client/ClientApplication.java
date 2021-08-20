package client;

import client.transport.ServerListener;
import client.transport.SocketConnection;
import client.ui.ClientMainFrame;

import java.io.IOException;

public class ClientApplication {

    public static final String HOST = "127.0.0.1";
    public static final int SERVER_PORT = 9000;

    public ClientApplication() throws IOException {
        SocketConnection socketConnection = new SocketConnection(HOST, SERVER_PORT);

        ServerListener serverListener = new ServerListener(socketConnection);
        serverListener.start();

        new ClientMainFrame(socketConnection);
    }

    public static void main(String[] args) throws IOException {
        new ClientApplication();
    }

}
