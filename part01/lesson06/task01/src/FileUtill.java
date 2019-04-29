import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    private static void getWords() {
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

    public static void createDictonariesFile() {
        getWords();
        try (FileOutputStream fos = new FileOutputStream(OUTPUT_TEXT_PATH)) {
            for (String word : words) {
                word += "\n";
                byte[] buffer = word.getBytes();
                fos.write(buffer, 0, buffer.length);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
