import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * ����� ��� ������ � �������
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class FileUtill {
    private static final String INPUT_TEXT_PATH = "input_text.txt";
    private static final String OUTPUT_TEXT_PATH = "words.txt";

    private static List<String> words = new ArrayList<>();

    /**
     * �������� ��������������� �� �������� ���� �� ������������� ����
     */
    private static void getWords() {
        String str = "";
        try (FileInputStream fin = new FileInputStream(INPUT_TEXT_PATH)) {
            System.out.println("������ ������������ �����: " + fin.available() + " ����(�)");
            int i = -1;
            while ((i = fin.read()) != -1) {
                if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122) || i == 32 || i == 39)
                    str += (char) i;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        if (!str.isEmpty()) {
            str = str.replace("  ", " ").toLowerCase();
            String[] arr = str.split(" ");
            Set<String> setWords = new HashSet<>(Arrays.asList(arr));
            words = new ArrayList<>(setWords);
            Collections.sort(words);
            System.out.println("������ ������ ����: " + words.size());
        }
    }

    /**
     * ���������� ���������� ����� � ����
     */
    public static void createDictonariesFile() {
        getWords();
        String result = "";
        for (String word : words) {
            result += word + "\n";
        }
        byte[] buffer = result.getBytes();
        writeFile(buffer, OUTPUT_TEXT_PATH);
    }

    /**
     * �������� ������ ���� � ����
     * @param buffer - ������ ����
     * @param path - ���� �� �����
     */
    public static void writeFile(byte[] buffer, String path) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * ��������� ��������� ������, ���������� �� ��������� ��������:
     *  - ����������� ������� �� 1<=n1<=15 ����. � ����������� ����� ������������ ���� ����� ���������� �������.
     *  - ����� ������� �� 1<=n2<=15 ��������� ����
     *  - ����� ��������� ����� ��������
     *  - ����������� ���������� � ��������� �����
     *  - ����������� ������������� (.|!|?)+" "
     *  - ����� ������� �� �������. � ����� ������ 1<=n3<=20 �����������. � ����� ������ ����� ������ ������ � ������� �������.
     *  - ���� ������ ���� 1<=n4<=1000. ���� ����������� probability ��������� ������ �� ���� ����� ������� � ��������� ����������� (1/probability).
     *
     * C������ n ������ �������� size � �������� path. words - ������ ����, probability - �����������.
     *
     * @param path - ���� ��� ����� ������� �����
     * @param n - ���������� ����������� ������
     * @param size - ������ ������� �����
     * @param words - ������ ����
     * @param probability - ����������� ��������� ������ �� ���� ������� � ��������� ����������� (1/probability)
     */
    public static void getFiles(String path, int n, int size, String[] words, int probability){

    }
}
