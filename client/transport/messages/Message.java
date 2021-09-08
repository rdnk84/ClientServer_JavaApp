package client.transport.messages;

import java.io.Serializable;

public class Message implements Serializable {

    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Message(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
