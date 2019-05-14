import java.io.*;
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

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);  //�������

        InputStream fromClient = socket.getInputStream();
        OutputStream toClient = socket.getOutputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fromClient));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(toClient));

        Scanner scanner = new Scanner(System.in);
        String message;
        while (!(message = scanner.nextLine()).isEmpty()) {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            System.out.println("server echo " + bufferedReader.readLine());
        }
        socket.close();
    }
}
