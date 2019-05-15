package part01.lesson10.task01.client;

import part01.lesson10.task01.Listener;
import part01.lesson10.task01.domain.User;
import part01.lesson10.task01.server.Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Урок 10. Задание 1.
 *
 * Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).
 *
 * @version   1.0 14.05.2019
 * @author    Pavel Anisimov
 */
public class Client2 {

    public static Integer CLIENT_PORT = 4991;

    public static void main(String[] args) throws IOException {

        try(Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);
            OutputStream os = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os))){

            Scanner scanner = new Scanner(System.in);
            System.out.print("Create username: ");
            User user = new User(scanner.nextLine(),CLIENT_PORT);
            os.write(user.toByteArray());
            os.flush();

            String message;
            while(!(message = scanner.nextLine()).isEmpty()){
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            Listener listenerThread = new Listener(socket);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //TODO: 1:15:44
}
