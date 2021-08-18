package client.net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;



public class Client implements Serializable{
    private Socket clientSocket;
    private int serverPort;


    private ObjectInputStream read = null;
    private ObjectOutputStream write = null;

//    private ServerListener serverListener;

//    public static void main(String[]args) throws IOException {
//       Client client = new Client ("localhost", 9000);
////        Scanner scanner = new Scanner(System.in);
//        while (true){
////            String input = scanner.nextLine();
////            client.sendToServer(input);
//        }
//
//    };


//конструктор
    public Client (String host, int serverPort) throws IOException {
        this.serverPort = serverPort;
        clientSocket = new Socket(host, serverPort);


        read = new ObjectInputStream(clientSocket.getInputStream());
        write = new ObjectOutputStream(clientSocket.getOutputStream());

//        serverListener = new ServerListener();
    }

    public void sendToServer (String message) throws IOException {
        write.writeObject(message);
        write.flush();
    }

//    class ServerListener extends Thread {
//        public ServerListener () {
//            this.start();
//        }

//        @Override
//        public void run() {
//            try {
//                while (true) {
//
//                    String message = (String) read.readObject();
//                    System.out.println(message);
//                }
//
//            }
//            catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    clientSocket.close();
//                    read.close();
//                    write.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }

};


