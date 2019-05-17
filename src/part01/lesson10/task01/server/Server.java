package part01.lesson10.task01.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервер чата
 *
 * @version   1.0 16.05.2019
 * @author    Pavel Anisimov
 */
public class Server {
    public static final Integer SERVER_PORT = 5000; //Прослушиваемый порт
    private static Map<String,ServerListener> users = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Start server. Port " + SERVER_PORT);
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerListener(socket);
            }
        } finally {
            serverSocket.close();
        }
    }

    public static Map<String, ServerListener> getUsers() {
        return users;
    }
}
