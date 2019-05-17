package part01.lesson10.task02.client;

import part01.lesson10.task02.PortCreator;
import part01.lesson10.task02.domain.User;
import part01.lesson10.task02.server.Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Клиент чата
 *
 * @version   1.0 16.05.2019
 * @author    Pavel Anisimov
 */
public class Client {

    public static Integer CLIENT_PORT = PortCreator.createPort();

    public static void main(String[] args) throws IOException {

        try(Socket socketServer = new Socket("127.0.0.1", Server.SERVER_PORT);
            OutputStream os = socketServer.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os))){

            new ClientListener(CLIENT_PORT);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Команды:");
            System.out.println("pm userName message - отправить личное сообщение message пользователю с ником userName");
            System.out.println("quit - выйти из чата");
            System.out.print("Введите имя пользователя: ");
            User user = new User(scanner.nextLine(),CLIENT_PORT);
            user.sendUser(os);

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
