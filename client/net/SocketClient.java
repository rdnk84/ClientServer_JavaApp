package client.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;


public class SocketClient implements Serializable {
    private Socket socket;
    private int serverPort;
    private int id;

    private ObjectInputStream read = null;
    private ObjectOutputStream write = null;

//    private ServerListener serverListener;

//    public static void main(String[]args) throws IOException {
//       SocketClient client = new SocketClient ("localhost", 9000);
////        Scanner scanner = new Scanner(System.in);
//        while (true){
////            String input = scanner.nextLine();
////            client.sendToServer(input);
//        }
//
//    };

    //конструктор
    public SocketClient(int id, String host, int serverPort) throws IOException {
        this.serverPort = serverPort;
        socket = new Socket(host, serverPort);
        this.id = id;

        read = new ObjectInputStream(socket.getInputStream());
        write = new ObjectOutputStream(socket.getOutputStream());

//        serverListener = new ServerListener();
    }

    public ObjectInputStream getRead() {
        return read;
    }

    public void setRead(ObjectInputStream read) {
        this.read = read;
    }

    public ObjectOutputStream getWrite() {
        return write;
    }

    public void setWrite(ObjectOutputStream write) {
        this.write = write;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void sendMessage(Message message) throws IOException {
        write.writeObject(message);
        write.flush();
    }

    public void messageFromServer() {

    }

};


