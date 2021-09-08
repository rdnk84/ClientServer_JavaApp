package server;

import client.transport.messages.Message;
import server.model.ClientInfo;
import server.transport.messages.MessageSender;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandler extends Thread {

    private Socket socket;

    private Map<Integer, ClientInfo> clientInfoMap;
    private ClientInfo thisClientInfo;

    public ClientHandler(Socket socket, ClientInfo thisClientInfo, Map<Integer, ClientInfo> clientInfoMap) throws IOException {
        this.socket = socket;
        this.thisClientInfo = thisClientInfo;
        this.clientInfoMap = clientInfoMap;
    }

    @Override
    public void run() {
        System.out.println("New client is connected (включился класс ClientHandler)");

        try {
            while (true) {
                ObjectInputStream in = thisClientInfo.getIn();

                Message msg = (Message) in.readObject();

                System.out.printf("client: %s\n", msg);

                //как-то надо пalue
                //считываю сообщение (ну пока из консоли)
//                Scanner scanner = new Scanner(System.in);
//                String msgFromSrv = scanner.nextLine();
//                Message messageFromConsole = new Message(msgFromSrv);
//

                for (ClientInfo clientInfo : clientInfoMap.values()) {
                    if(!clientInfo.equals(thisClientInfo)) {

                        String fromClient = msg.getPayload() + "111";
                        msg.setPayload(fromClient);
                        MessageSender.sendMessage(msg, clientInfo);
                    }
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                thisClientInfo.getIn().close();
                thisClientInfo.getOut().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}