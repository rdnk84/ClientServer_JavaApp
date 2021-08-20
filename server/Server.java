package server;

import client.net.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    public static final int PORT = 9000;
    private ServerSocket serverSocket;

   // private ArrayList<ClientThread> clients;

    private HashMap<String,Integer> clients;

    public static void main(String[] args) throws IOException {
        System.out.println("Is waiting for a client...");
        new Server();
        System.out.println("THE END!!!");
    }

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);

        //в переменной clients наш сервер собирает всех клиентов (в arrayList), которые подключаются к серверу
       // clients = new ArrayList<>();

//        HashMap<String,Object> clients = new HashMap<>();
        while (true) {

            ClientThread client = new ClientThread(serverSocket.accept());

            client.start();
           // clients.put(client); - не понимаю как мне положить
        }
    }

    //сериализация входящего клиента
//    private static void serData() {
//ObjectOutputStream write = new ObjectOutputStream();
//    }

    //вложенный класс, запускается при подключении нового клиента к серверу
    class ClientThread extends Thread {
        private Socket clientSocket;
        private ObjectInputStream read = null;
        private ObjectOutputStream write = null;

        private int id;

        //конструктор
        public ClientThread(Socket clientSocket) throws IOException {

//            this.id = clients.size();

            this.clientSocket = clientSocket;

            write = new ObjectOutputStream(clientSocket.getOutputStream());
            read = new ObjectInputStream(clientSocket.getInputStream());

        }

        @Override
        public void run() {
            System.out.println("Подключился новый клиент");

            try {
                while (true) {

                    Message message = (Message) read.readObject();


                    System.out.printf("client %d: %s\n", id, message);
//                    if (message.getPayload().toLowerCase().compareTo("hello") == 0) {
                        write.writeObject("blabla");
                        write.flush();
//                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                    read.close();
                    write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}


