package part01.lesson10.task01.server;

import part01.lesson10.task01.domain.User;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * Listener сервера
 *
 * @version   1.0 16.05.2019
 * @author    Pavel Anisimov
 */
public class ServerListener extends Thread {

    private static final String JOIN_USER_MSG = "User \"%s\" has joined chat.\n";
    private static final String EXIT_USER_MSG = "User \"%s\" exit chat.\n";
    private static final String ERROR_MSG = "Error command.\n";

    private Socket socket;
    private User user;

    public ServerListener(Socket socket) {
        this.socket = socket;
        start();
    }

    /**
     * Слушаем подключившегося клиента и рассылаем его сообщение всем остальным пользователям.
     * Если приходит слово "quit" отключаем пользователя.
     * Через "pm" отправляем личные сообщения
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
                Server.getUsers().put(user.getName(), this);
            }
            String message;
            while ((message = reader.readLine()) != null) {
                if (!message.isEmpty()) {
                    if (message.equals("quit")) {
                        System.out.printf(EXIT_USER_MSG, user.getName());
                        break;
                    } else if (message.split(" ")[0].equals("pm")) {
                        String[] data = message.split(" ");
                        if(data.length>2){
                            String userName = data[1];
                            message = message.replace("pm "+ data[1],"");
                            sendPersonalMsg(message, userName);
                        } else {
                            sendPersonalMsg(ERROR_MSG, this.getUserName());
                        }

                    } else {
                        sendAll(message);
                    }
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправляет сообщение пришедшее от какого лиюо User-а всем остальным
     * @param message - сообщение
     */
    private void sendAll(String message) {
        Map<String, ServerListener> serverList = Server.getUsers();
        if (serverList != null && serverList.size() > 0)
            serverList.forEach((name, listener) -> {
                if (listener.getUser() != null && listener != this) {
                    listener.getUser().sendMessage(message, this.getUserName());
                }
            });
    }

    /**
     * Отправить личное сообщение пользователю с именем userName
     * @param message - сообщение
     * @param userName - имя пользователя
     */
    private void sendPersonalMsg(String message, String userName) {
        Map<String, ServerListener> serverList = Server.getUsers();
        if (serverList != null && serverList.size() > 0 && serverList.containsKey(userName) && serverList.get(userName).getUser() != null) {
            serverList.get(userName).getUser().sendMessage(message, this.getUserName());
        }
    }

    public User getUser() {
        return user;
    }

    private String getUserName() {
        return this.getUser() != null ? this.getUser().getName() : null;
    }
}
