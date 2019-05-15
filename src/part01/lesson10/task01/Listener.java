package part01.lesson10.task01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {

    private Socket socket;

    public Listener(Socket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
