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
     * @param size - ������ ������� ����� � ������
     * @param words - ������ ����
     * @param probability - ����������� ��������� ������ �� ���� ������� � ��������� ����������� (1/probability)
     */
    public static void getFiles(String path, int n, int size, String[] words, double probability) {
        if (n > 0 && size > 2 && words != null && words.length > 0 && probability > 0 && probability <= 1) {
            for (int i = 0; i < n; i++) {
                List<String> sentences = new ArrayList<>();
                String text = "";
                String fileName = createFileName(i, path);
                int ost = size % 240;
                int count = size / 240;     //���������� �����������
                if (size <= 240)            //����������� ��������� ������ ������ ����������� � 15 �������
                    text = Utill.createSentence(size, Utill.getRandomWordFromArr(words), probability);
                if (count != 1) {
                    if (ost == 1 || ost == 2)
                        count--;
                    for (int j = 0; j < count; j++)
                        sentences.add(Utill.createSentence(239, Utill.getRandomWordFromArr(words), probability));
                }
                if (ost == 1) {
                    sentences.add(Utill.createSentence(119, Utill.getRandomWordFromArr(words), probability));
                    sentences.add(Utill.createSentence(121, Utill.getRandomWordFromArr(words), probability));
                }
                if (ost == 2) {
                    sentences.add(Utill.createSentence(120, Utill.getRandomWordFromArr(words), probability));
                    sentences.add(Utill.createSentence(121, Utill.getRandomWordFromArr(words), probability));
                }
                if (ost > 2) {
                    sentences.add(Utill.createSentence(ost, Utill.getRandomWordFromArr(words), probability));
                }

                if (text.isEmpty()) {
                    int sentenceSize = sentences.size();
                    for (int j = 0; j < sentenceSize; j++) {
                        if (j == sentenceSize - 1) {
                            text += sentences.get(j);
                            continue;
                        }
                        if (j % 15 == 0 && j >= 15) {
                            text += sentences.get(j) + "\n";
                            continue;
                        } else
                            text += sentences.get(j) + " ";
                    }
                }
                System.out.print(text.length() == size ? "true" : "false");
                System.out.println("  size=" + size + " text.length=" + text.length());
                writeFile(text.getBytes(), fileName);
            }
        } else
            System.out.println("�� ������ ������� ���������");
    }

    /**
     * �������� ������ ���� �������� size
     * @param size - ���������� ���� � �������
     * @return - ������ ���� �������� size
     */
    public static String[] getWords(int size) {
        if (size <= words.size()) {
            String[] arr = new String[size];
            for (int i = 0; i < size; i++) {
                arr[i] = words.get(i);
            }
            return arr;
        }
        return null;
    }

    /**
     * �������� ��������������� �� �������� ���� �� ������������� ���� �� ������ ������
     */
    private static void getWordsFromFile() {
        String str = "";
        try (FileInputStream fin = new FileInputStream(INPUT_TEXT_PATH)) {
            System.out.println("������ ������������ �����: " + fin.available() + " ����(�)");
            int i = -1;
            while ((i = fin.read()) != -1) {
                if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122) || i == 32)
                    str += (char) i;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        if (!str.isEmpty()) {
            str = str.replace("  ", " ").toLowerCase();
            String[] arr = str.split(" ");
            Set<String> setWords = new HashSet<>(Arrays.asList(arr));
            if(setWords.contains("")){
                setWords.remove("");
            }
            words = new ArrayList<>(setWords);
            Collections.sort(words);
            System.out.println("������ ������ ����: " + words.size());
        }
    }

    /**
     * ���������� ���������� ����� � ����
     */
    public static void createDictonariesFile() {
        getWordsFromFile();
        String str = "";
        for (String word : words) {
            str += word + "\n";
        }
        writeFile(str.getBytes(),OUTPUT_TEXT_PATH);
    }

    /**
     * ������ � ���� ������� ����
     * @param buffer - ������ ����
     * @param path - ���� �� �����
     */
    private static void writeFile(byte[] buffer, String path){
        try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * ��������� ��� ����� ��������: /file01.txt
     * @param n - ����� �����
     * @param path - ����
     * @return - �������� ��� �����
     */
    private static String createFileName(int n, String path) {
        if (path != null ) {
            if (n < 10) {
                path += "file0";
            } else {
                path += "file";
            }
            return path + n + ".txt";
        }
        return null;
    }
}
