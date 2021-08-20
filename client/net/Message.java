package client.net;

import java.io.Serializable;

public class Message implements Serializable {
    private Integer clientId;
    private String payload;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Message(Integer clientId, String payload) {
        this.clientId = clientId;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "clientId=" + clientId +
                ", payload='" + payload + '\'' +
                '}';
    }
}
