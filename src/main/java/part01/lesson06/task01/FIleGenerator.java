package part01.lesson06.task01;

import java.io.*;
import java.util.*;

/**
 * Класс для работы с файлами
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class FIleGenerator {
    private static final String INPUT_TEXT_PATH = "input_text.txt";
    private static final String OUTPUT_TEXT_PATH = "words.txt";

    private static List<String> words = new ArrayList<>();

    /**
     * Получаем отсортированный по алфавиту лист не дублирующихся слов из любого текста
     */
    private static void getWordsFromFile() {
        String str = "";
        try (FileInputStream fin = new FileInputStream(INPUT_TEXT_PATH)) {
            System.out.println("Размер считываемого файла: " + fin.available() + " байт(а)");
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
            System.out.println("Размер списка слов: " + words.size());
        }
    }

    /**
     * Записываем полученные слова в файл
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
     * Запись в файл массива байт
     * @param buffer - массив байт
     * @param path - путь до файла
     */
    private static void writeFile(byte[] buffer, String path){
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
