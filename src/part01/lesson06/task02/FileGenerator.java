package part01.lesson06.task02;

import java.io.*;
import java.util.*;

/**
 * Класс для работы с файлами
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class FileGenerator {
    private static final String INPUT_TEXT_PATH = "src/part01/lesson06/task02/input_text.txt";
    private static final String OUTPUT_TEXT_PATH = "src/part01/lesson06/task02/words.txt";

    /** Минимальная длинна предложения */
    private static final Integer MIN_LENGTH_SENTENCE = 2;

    /** Максимально возможная длинна предложения */
    private static final Integer MAX_LENGTH_SENTENCE = 15*15+15;

    /** Максимально возможное количество предложений в абзаце */
    private static final Integer MAX_COUNT_SENTENCE_IN_PARAGRAPH = 15;

    private static List<String> words = new ArrayList<>();

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
     * @param size - размер каждого файла в байтах
     * @param words - массив слов
     * @param probability - вероятность вхождения одного из слов массива в следующее предложение (1/probability)
     */
    public static void getFiles(String path, int n, int size, String[] words, double probability) {
        if (n > 0 && size > MIN_LENGTH_SENTENCE && words != null && words.length > 0 && probability > 0 && probability <= 1) {
            for (int i = 0; i < n; i++) {
                List<String> sentences = new ArrayList<>();
                String text = "";
                String fileName = createFileName(i, path);
                int ost = size % MAX_LENGTH_SENTENCE;
                int count = size / MAX_LENGTH_SENTENCE;     //Количество предложений
                if (size <= MAX_LENGTH_SENTENCE)            //Максимально возможная длинна одного предложения с 15 словами
                    text = TextHandler.createSentence(size, TextHandler.getRandomWordFromArr(words), probability);
                if (count != 1) {
                    if (ost == 1 || ost == 2)
                        count--;
                    for (int j = 0; j < count; j++)
                        sentences.add(TextHandler.createSentence(MAX_LENGTH_SENTENCE - 1, TextHandler.getRandomWordFromArr(words), probability));
                }
                if (ost == 1) {
                    sentences.add(TextHandler.createSentence(MAX_LENGTH_SENTENCE / 2 - 1, TextHandler.getRandomWordFromArr(words), probability));
                    sentences.add(TextHandler.createSentence(MAX_LENGTH_SENTENCE / 2 + 1, TextHandler.getRandomWordFromArr(words), probability));
                }
                if (ost == 2) {
                    sentences.add(TextHandler.createSentence(MAX_LENGTH_SENTENCE / 2, TextHandler.getRandomWordFromArr(words), probability));
                    sentences.add(TextHandler.createSentence(MAX_LENGTH_SENTENCE / 2 + 1, TextHandler.getRandomWordFromArr(words), probability));
                }
                if (ost > 2) {
                    sentences.add(TextHandler.createSentence(ost, TextHandler.getRandomWordFromArr(words), probability));
                }

                if (text.isEmpty()) {
                    int sentenceSize = sentences.size();
                    for (int j = 0; j < sentenceSize; j++) {
                        if (j == sentenceSize - 1) {
                            text += sentences.get(j);
                            continue;
                        }
                        if (j % MAX_COUNT_SENTENCE_IN_PARAGRAPH == 0 && j >= MAX_COUNT_SENTENCE_IN_PARAGRAPH) {
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
            System.out.println("Не верные входные параметры");
    }

    /**
     * Получить массив слов размером size
     * @param size - количество слов в массиве
     * @return - массив слов размером size
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
     * Получаем отсортированный по алфавиту лист не дублирующихся слов из любого текста
     */
    private static void getWordsFromFile() {
        String str = "";
        try (FileInputStream fin = new FileInputStream(INPUT_TEXT_PATH);
             BufferedReader br = new BufferedReader(new InputStreamReader(fin))) {
            System.out.println("Размер считываемого файла: " + fin.available() + " байт(а)");
            String strLine;
            while ((strLine = br.readLine()) != null){
                str += strLine;
            }
            str = str.replaceAll("[^A-Za-z ]","");
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
     * Формирует имя файла например: /file01.txt
     * @param n - номер файла
     * @param path - путь
     * @return - итоговое имя файла
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
