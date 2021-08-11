package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static final int PORT = 9000;
    private ServerSocket serverSocket;

    private ArrayList<ClientThread> clients;

    public static void main(String[] args) throws IOException {
        new Server();
        System.out.println("THE END!!!");

    }

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
        clients = new ArrayList<>();
        while (true) {
            ClientThread client = new ClientThread(serverSocket.accept());
            client.start();
            clients.add(client);
        }
    }

    //вложенный класс, запускается при подключении нового клиента к серверу
    class ClientThread extends Thread {
        private Socket clientSocket;
        private ObjectInputStream read = null;
        private ObjectOutputStream write = null;

        private int id;

        //конструктор
        public ClientThread(Socket clientSocket) throws IOException {

            this.id = clients.size();

            this.clientSocket = clientSocket;
            /*TODO: understand the difference with ObjectInputStream*/
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String readedMessage = reader.readLine();
            System.out.println(readedMessage);

            write = new ObjectOutputStream(clientSocket.getOutputStream());
            read = new ObjectInputStream(clientSocket.getInputStream());

        }

        @Override
        public void run() {
            System.out.println("Подключился новый клиент");

            try {
                while (true) {

                    String message = (String) read.readObject();
                    System.out.printf("client.net.Client %d: %s\n", id, message);
                    if(message.toLowerCase().compareTo("hello") == 0) {
                        write.writeObject("blabla");
                        write.flush();
                    }
                }

            }
            catch (IOException e) {
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


