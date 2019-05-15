package part01.lesson10.task01.domain;

import part01.lesson10.task01.server.ServerListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
    private ServerListener listener;
    private String name;
    private int port;

    public User(ServerListener listener) {
        this.listener = listener;
    }

    public User(String name, int port) {
        this.name = name;
        this.port = port;
    }

    public ServerListener getListener() {
        return listener;
    }

    public void setListener(ServerListener listener) {
        this.listener = listener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public byte[] toByteArray() {
        byte[] byteUser = null;
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(buffer)) {
            oos.writeObject(this);
            oos.close();
            byteUser = buffer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteUser;
    }
}
