package part01.lesson10.task01.server;

import part01.lesson10.task01.domain.User;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Listener сервера
 *
 * @version   1.0 16.05.2019
 * @author    Pavel Anisimov
 */
public class ServerListener extends Thread {

    private static final String JOIN_USER_MSG = "User \"%s\" has joined chat.\n";
    private static final String EXIT_USER_MSG = "User \"%s\" exit chat.\n";

    private Socket socket;
    private User user;

    public ServerListener(Socket socket) {
        this.socket = socket;
        start();
    }

    /**
     * Слушаем подключившегося клиента и рассылаем его сообщение всем остальным пользователям.
     * Если приходит слово "quit" отключаем пользователя.
     */
    @Override
    public void run() {
        try (InputStream is = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is));
             ObjectInputStream ois = new ObjectInputStream(is)) {
            if (ois != null) {
                try {
                    user = (User) ois.readObject();
                    System.out.printf(JOIN_USER_MSG, user.getName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Server.getServerList().add(this);
            }
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.equals("quit")) {
                    System.out.printf(EXIT_USER_MSG, user.getName());
                    break;
                } else {
                    send(message);
                    System.out.println(user.getName() + ": " + message);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: выпиливать отключившегося пользователя из коллекции
        //TODO: Добавить ЛС
    }

    /**
     * Отправляет сообщение пришедшее от какого лиюо User-а всем остальным
     * @param message - рассылаемое сообщение
     */
    private void send(String message){
        List<ServerListener> serverList = Server.getServerList();
        if (serverList != null && serverList.size() > 0) {
            for (ServerListener serverListener : serverList) {
                if (serverListener.getUser() != null && serverListener != this) {
                    try (Socket socketUser = new Socket("127.0.0.1", serverListener.getUser().getPort());
                         BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socketUser.getOutputStream()))) {
                        bufferedWriter.write(this.getUser().getName() + ": " + message);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    public User getUser() {
        return user;
    }
}
