package part01.lesson10.task01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {

    private int port;

    public Listener(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            String message;
            while((message = reader.readLine())!=null){
                System.out.println(message);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
