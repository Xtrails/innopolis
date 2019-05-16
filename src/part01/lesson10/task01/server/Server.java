package part01.lesson10.task01.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Сервер чата
 *
 * @version   1.0 16.05.2019
 * @author    Pavel Anisimov
 */
public class Server {
    public static final Integer SERVER_PORT = 5000; //Прослушиваемый порт
    private static List<ServerListener> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Start server. Port " + SERVER_PORT);
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                serverList.add(new ServerListener(socket));
            }
        } finally {
            serverSocket.close();
        }
    }

    public static List<ServerListener> getServerList() {
        return serverList;
    }
}
