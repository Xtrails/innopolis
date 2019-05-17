package part01.lesson10.task02.domain;

import java.io.*;
import java.net.Socket;

/**
 * Класс пользователя
 *
 * @version   1.0 16.05.2019
 * @author    Pavel Anisimov
 */

public class User implements Serializable {

    /** Имя пользователя */
    private String name;

    /** Port клиента*/
    private int port;

    public User(String name, int port) {
        this.name = name;
        this.port = port;
    }

    /**
     * Преобразует User в byte[]
     * @return - User в виде массива байт
     */
    public byte[] toByteArray() {
        byte[] byteUser = null;
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(buffer)) {
            oos.writeObject(this);
            oos.close();
            byteUser = buffer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteUser;
    }

    /**
     * Отправляет пользователя в OutputStream
     * @param os - исходящий поток
     * @throws IOException
     */
    public void sendUser(OutputStream os) throws IOException {
        os.write(this.toByteArray());
        os.flush();
    }

    /**
     * Отправить сообщение
     * @param message - сообщение
     * @param userName - имя пользователя от которого отправляется сообщение
     */
    public void sendMessage(String message, String userName){
            try (Socket socketUser = new Socket("127.0.0.1", port);
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socketUser.getOutputStream()))) {
                bufferedWriter.write(userName + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
            }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }
}