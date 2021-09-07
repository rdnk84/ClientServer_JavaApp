package server;

import client.transport.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        System.out.println("New client is connected (включился класс ClientHandler)");
        try {
            while (true) {
                Message message = (Message) in.readObject();

                System.out.printf("client: %s\n", message);

                //как-то надо получить список всех клиентов (собранных сервером в hashmap)и дальше через for отправить сообщение каждому)


                //считываю сообщение (ну пока из консоли)
                Scanner scanner = new Scanner(System.in);
                String msgFromSrv = scanner.nextLine();

//                out.writeObject(new Message("Some answer"));
                out.writeObject(new Message(msgFromSrv));
                out.flush();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}