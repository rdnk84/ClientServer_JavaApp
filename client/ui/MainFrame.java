package client.ui;

import client.net.ServerListener;
import client.net.SocketClient;
import client.net.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class MainFrame extends JFrame {
    private final SocketClient socketClient;

    private JButton buttonSend;
    private JTextField textField;
    private JPanel contentManager;
    private JTextArea textArea;

    private Integer clientId;


    public MainFrame() throws IOException {
        super("SocketClient");

        Random random = new Random();
        clientId = random.nextInt();

        buttonSend = new JButton("Send");
        textField = new JTextField(30);
        contentManager = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        socketClient = new SocketClient(15, "127.0.0.1", 9000);
        setSize(600, 300);

        setContentPane(contentManager);
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonSend) {
                    String s = textField.getText();
                    try {
                        socketClient.sendMessage(new Message(clientId, s));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        add(buttonSend);
        add(textField);
        //хочу получить ответ от серсера
       // JOptionPane.showMessageDialog(null, (String)"Вы прислали:" + socketClient.sendToServer(););
        setVisible(true);

        ServerListener serverListener = new ServerListener(socketClient);
        serverListener.run();
    }


    public static void main(String[] args) throws IOException {
        MainFrame mf = new MainFrame();
    }
//    private void createFrame() {
//        buttonSend = new JButton("Send");
//        textField = new JTextField(30);
//       // textArea = new JTextArea();
//
//    }

}
