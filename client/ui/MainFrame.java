package client.ui;

import client.net.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;

public class MainFrame extends JFrame {
    private final Client client;

    private JButton buttonSend;
    private JTextField textField;
    private JPanel contentManager;
    private JTextArea textArea;


    public MainFrame() throws IOException {
        super("Client");
        buttonSend = new JButton("Send");
        textField = new JTextField(30);
        contentManager = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        client = new Client("127.0.0.1", 9000);
        setSize(600, 300);

        setContentPane(contentManager);
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonSend) {
                    String s = textField.getText();
                    try {
                        client.sendToServer(s);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        add(buttonSend);
        add(textField);
        setVisible(true);
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
