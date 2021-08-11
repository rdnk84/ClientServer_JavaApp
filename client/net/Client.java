package client.net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends JFrame implements Runnable {
    private Socket clientSocket;

    private ObjectInputStream read = null;
    private ObjectOutputStream write = null;

    private JButton buttonSend;
    private JTextField textField;
    private JPanel contentManager;
    private JTextArea textArea;


    public static void main(String[] args) throws IOException {
        new Thread(new Client()).start();
    }

    ;

    public Client() throws IOException {
        super("Client");

        buttonSend = new JButton("Send");
        textField = new JTextField(30);
//        contentManager = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(600, 300);
        setVisible(true);
//        setContentPane(contentManager);
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonSend) {
                    try {
                        sendToServer(textField.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(buttonSend);
        add(textField);
//        add(contentManager);
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 9000);
            while (true) {
                read = new ObjectInputStream(clientSocket.getInputStream());
                write = new ObjectOutputStream(clientSocket.getOutputStream());
                JOptionPane.showMessageDialog(null, (String) read.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
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

    public void sendToServer(String message) throws IOException {
        write.writeObject(message);
        write.flush();
    }

};

//public class Client {
//    private Socket clientSocket;
//    private int serverPort;
//    private ObjectInputStream read = null;
//    private ObjectOutputStream write = null;
//
//    private ServerListener serverListener;
//
//    public static void main(String[]args) throws IOException {
//       Client client = new Client ("localhost", 9000);
//        Scanner scanner = new Scanner(System.in);
//        while (true){
//            String input = scanner.nextLine();
//            client.sendToServer(input);
//        }
//
//    };
//
//
////конструктор
//    public Client (String host, int serverPort) throws IOException {
//        this.serverPort = serverPort;
//        clientSocket = new Socket(host, serverPort);
//
//
//        read = new ObjectInputStream(clientSocket.getInputStream());
//        write = new ObjectOutputStream(clientSocket.getOutputStream());
//
//        serverListener = new ServerListener();
//    }
//
//    public void sendToServer (String message) throws IOException {
//        write.writeObject(message);
//        write.flush();
//    }
//
//    class ServerListener extends Thread {
//        public ServerListener () {
//            this.start();
//        }
//
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
//
//};


