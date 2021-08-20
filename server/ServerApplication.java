package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;

public class ServerApplication {

    public static final int PORT = 9000;

    private ServerSocket serverSocket;

    private HashMap<String, Integer> clients;

    public ServerApplication() throws IOException {
        serverSocket = new ServerSocket(PORT);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            
            // please check what socket address do you have for several clients
            // maybe there is a point to store clients and don`t need to send id in message :)
            final SocketAddress remoteSocketAddress = clientSocket.getRemoteSocketAddress();

            ClientHandler client = new ClientHandler(clientSocket);
            client.start();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Server application started. Waiting for a client...");
        new ServerApplication();
        System.out.println("THE END!!!");
    }
}


