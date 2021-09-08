package server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Objects;

public class ClientInfo {

    //    private SocketAddress remoteSocketAddress;
    private Socket clientSocket;
    private int localPort;

    private ObjectInputStream in;
    private ObjectOutputStream out;

//    public ClientInfo(SocketAddress remoteSocketAddress, Socket clientSocket) {
//        this.remoteSocketAddress = remoteSocketAddress;
//        this.clientSocket = clientSocket;
//    }

    public ClientInfo(int localPort, Socket clientSocket) throws IOException {
        this.localPort = localPort;
        this.clientSocket = clientSocket;
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        this.in = new ObjectInputStream(clientSocket.getInputStream());
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


    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientInfo that = (ClientInfo) o;
        return localPort == that.localPort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(localPort);
    }
}
