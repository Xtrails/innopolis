package part01.lesson10.task01.client;

import part01.lesson10.task01.Listener;
import part01.lesson10.task01.server.Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ���� 10. ������� 1.
 *
 * ����������� ���������� - ��������������������� ���, � ������� ��������� ������������ ���������� ��������.
 * ������ ������ ����� ������� ���������� ���� ��� �������. ����� ���� �������� ���������� ��� ���������.
 * ������ ��������� ������ ����������� ������ ������� � ��������� ���� �������� (broadcast).
 *
 * @version   1.0 14.05.2019
 * @author    Pavel Anisimov
 */
public class Client {

    public static Integer CLIENT_PORT = 4998;

    public static void main(String[] args) throws IOException {
        Listener listenerThread = new Listener(CLIENT_PORT);
        listenerThread.start();

        try(Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);
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

    //TODO: 1:15:44
}
