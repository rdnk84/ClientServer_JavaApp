package server.model;

import java.net.Socket;
import java.net.SocketAddress;

public class ClientInfo {

    //    private SocketAddress remoteSocketAddress;
    private Socket clientSocket;
    private int localPort;

//    public ClientInfo(SocketAddress remoteSocketAddress, Socket clientSocket) {
//        this.remoteSocketAddress = remoteSocketAddress;
//        this.clientSocket = clientSocket;
//    }

    public ClientInfo(int localPort, Socket clientSocket) {
        this.localPort = localPort;
        this.clientSocket = clientSocket;
    }

    public int getLocalPort() {
        return localPort;
    }

    //    public SocketAddress getRemoteSocketAddress() {
//        return remoteSocketAddress;
//    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
