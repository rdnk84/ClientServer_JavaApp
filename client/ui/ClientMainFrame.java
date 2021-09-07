package client.ui;

import client.transport.SocketConnection;
import client.transport.messages.Message;
import client.transport.messages.MessageSender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientMainFrame extends JFrame {

    private SocketConnection socketConnection;

    private JButton buttonSend;
    private JTextField textField;
    private JPanel contentManager;
    private JTextArea textArea;

    public ClientMainFrame(SocketConnection socketConnection) {
        super("SocketClient");
        this.buttonSend = new JButton("Send");
        this.textField = new JTextField(30);
        this.contentManager = new JPanel();
        this.socketConnection = socketConnection;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(600, 300);
        setContentPane(contentManager);

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonSend) {
                    String s = textField.getText();
                    try {
                        MessageSender.sendMessage(new Message(s), socketConnection);
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

}
