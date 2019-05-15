package part01.lesson10.task01.server;

import part01.lesson10.task01.domain.User;

import java.io.*;
import java.net.Socket;

public class ServerListener extends Thread {

    private Socket socket;
    private User user;

    public ServerListener(Socket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is));
             ObjectInputStream ois = new ObjectInputStream(is)) {
            if(ois != null){
                try {
                    user = (User) ois.readObject();
                    System.out.println("User \"" + user.getName() + "\" has joined chat.");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Server.serverList.add(this);
            }
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(user.getName() + ": " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
