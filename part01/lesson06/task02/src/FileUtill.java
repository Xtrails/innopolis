import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Класс для работы с файлами
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class FileUtill {
    private static final String INPUT_TEXT_PATH = "input_text.txt";
    private static final String OUTPUT_TEXT_PATH = "words.txt";

    private static List<String> words = new ArrayList<>();

    public static List<String> getWords() {
        return words;
    }

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

    /**
     * Генератор текстовых файлов, работающий по следующим правилам:
     *  - Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
     *  - Слово состоит из 1<=n2<=15 латинских букв
     *  - Слова разделены одним пробелом
     *  - Предложение начинается с заглавной буквы
     *  - Предложение заканчивается (.|!|?)+" "
     *  - Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
     *  - Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
     *
     * Cоздает n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
     *
     * @param path - путь где будут созданы файлы
     * @param n - количество создаваемых файлов
     * @param size - размер каждого файла
     * @param words - массив слов
     * @param probability - вероятность вхождения одного из слов массива в следующее предложение (1/probability)
     */
    public static void getFiles(String path, int n, int size, String[] words, int probability) {
        for (int i = 0; i < n; i++) {
            String fileName = createFileName(n, path);

        }
    }

    /**
     * Формирует имя файла например: /file01.txt
     * @param n - номер файла
     * @param path - путь
     * @return - итоговое имя файла
     */
    private static String createFileName(int n, String path) {
        if (path != null) {
            if (n < 10) {
                path += "/file0";
            } else {
                path += "/file";
            }
            return path + n + ".txt";
        }
        return null;
    }
}
