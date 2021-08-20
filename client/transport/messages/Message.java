package client.transport.messages;

import java.io.Serializable;

public class Message implements Serializable {

    private Integer clientIdFrom;
    private String payload;

    public Integer getClientIdFrom() {
        return clientIdFrom;
    }

    public void setClientIdFrom(Integer clientIdFrom) {
        this.clientIdFrom = clientIdFrom;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Message(Integer clientIdFrom, String payload) {
        this.clientIdFrom = clientIdFrom;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "clientId=" + clientIdFrom +
                ", payload='" + payload + '\'' +
                '}';
    }
}
