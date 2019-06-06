package part01.lesson10.task02.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listener чата
 *
 * @version   1.0 16.05.2019
 * @author    Pavel Anisimov
 */
public class ClientListener extends Thread {

    /** Порт клиента */
    private int port;

    /** Конструктор */
    public ClientListener(int port) {
        this.port = port;
        start();
    }

    /**
     * Слушаем сервер в отдельном потоке, выпечатываем в консоль всё что от него приходит
     */
    @Override
    public void run() {
        while (true) {
            try (ServerSocket listenSocket = new ServerSocket(port);
                 Socket socket = listenSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
