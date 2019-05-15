package part01.lesson10.task01.server;

import part01.lesson10.task01.Listener;
import part01.lesson10.task01.client.Client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Server {
    public static final Integer SERVER_PORT = 4999; //Прослушиваемый порт

    public static void main(String[] args) throws IOException {

        Listener listenerThread = new Listener(SERVER_PORT);
        listenerThread.start();

        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10)); //Чтобы успеть запустить клиент

        try(Socket socket = new Socket("127.0.0.1", Client.CLIENT_PORT);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
            Scanner scanner = new Scanner(System.in);
            String message;
            while(!(message = scanner.nextLine()).isEmpty()){
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
