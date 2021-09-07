package server;

import server.model.ClientInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServerApplication {

    public static final int PORT = 9000;

    private ServerSocket serverSocket;

    //    private Map<SocketAddress, ClientInfo> clients = new HashMap<>();
    private Map<Integer, ClientInfo> clients = new HashMap<>();


    public ServerApplication() throws IOException {
        serverSocket = new ServerSocket(PORT);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("the connection is established");


            final SocketAddress remoteSocketAddress = clientSocket.getRemoteSocketAddress();
            final int clientPort = clientSocket.getPort();
//


//            System.out.println("client from port:" + remoteSocketAddress);

//            ClientInfo clientInfo = new ClientInfo(remoteSocketAddress, clientSocket);
            ClientInfo clientInfo = new ClientInfo(clientPort, clientSocket);


//            clients.put(remoteSocketAddress, clientInfo);
            clients.put(clientPort, clientInfo);

            ClientHandler client = new ClientHandler(clientSocket);
            client.start();

            //экспериментирую..
//            System.out.println(clients.get(remoteSocketAddress));
            System.out.println(clients.keySet());
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Server application started. Waiting for a client...");
        new ServerApplication();

        System.out.println("THE END!!!");
    }
}


