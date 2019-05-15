package part01.lesson10.task01.server;

import part01.lesson10.task01.Listener;
import part01.lesson10.task01.client.Client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Server {
    public static final Integer SERVER_PORT = 4999; //Прослушиваемый порт
    public static List<ServerListener> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                serverList.add(new ServerListener(socket));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        for (ServerListener serverListener : serverList) {
            try(Socket socket = new Socket("127.0.0.1", serverListener.getUser().getPort());
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
            Scanner scanner = new Scanner(System.in);
            String message;
            while(!(message = scanner.nextLine()).isEmpty()){
                bufferedWriter.write(serverListener.getUser().getName() + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        }
    }



    /**
     * Метод создания числа в диапозоне {min;max}
     *
     * @param max - наибольшее число
     * @param min - наименьшее число
     * @return - число в диапозоне {min;max}
     */
    private static int createRndInt(int min, int max) {
        if (max > min) {
            SecureRandom rnd = new SecureRandom();
            int result = rnd.nextInt(max - min) + min;
            return result;
        } else if (min == max) {
            return min;
        }
        return -1;
    }
}
