import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Вспомогательный класс
 *
 * @version   1.0 30.04.2019
 * @author    Pavel Anisimov
 */
public class Utill {

    /**
     * Создает случайный знак препинания
     * @return - случайный знак препинания "!"/"?"/"."
     */
    public static String createRndMark(){
        SecureRandom rnd = new SecureRandom();
        int i = rnd.nextInt(3);
        switch (i){
            case 0: return "!";
            case 1: return "?";
            case 2: return ".";
        }
        return "";
    }

    /**
     * Метод создания случайного числа в диапазоне {min;max}
     * @param max - наибольшее число в массиве
     * @param min - наименьшее число в массиве
     * @return - чисел в диапозоне {min;max}
     */
    public static int createRndInt(int min, int max) {
        if (max > 0 && max > min) {
            SecureRandom rnd = new SecureRandom();
            max++;
            return rnd.nextInt(max - min) + min;
        } else if (min == max) {
            return min;
        }
        return -1;
    }

    /**
     * Генерирует случайное слово определенной длинны
     * @param min - минимальная длинна слова
     * @param max - максимальная длинна слова
     * @return - случайное слово определенной длинны
     */
    public static String createRndWord(int min, int max) {
        if (max > 0 && max > min) {
            int rndLength = createRndInt(min, max);
            String word = "";
            for (int i = 0; i < rndLength; i++) {
                word += (char) createRndInt(97, 122);
            }
            return word;
        }
        return null;
    }

    /**
     * Генерирует случаное предложение c определенным количеством слов
     * @param min - минимальное количество слов в предложении
     * @param max - максимальное количество слов в предложении
     * @return - случаное предложение определенной длинны
     */
    public static String createRndSentence(int min, int max) {
        if (max > 0 && max > min) {
            int rndLength = createRndInt(min, max);
            List<String> words = new ArrayList<>();
            for (int i = 0; i < rndLength; i++) {
                if (i == 0)
                    words.add(firstUpperCase(createRndWord(1, 15)));
                else
                    words.add(createRndWord(1, 15));
            }
            if(rndLength>1){
                int l = createRndInt(min, rndLength-1);
                words.add(l,",");
            }

            words.add(createRndMark());
            String result = "";
            int size = words.size();
            for (int i = 0; i < size; i++) {
                if(i!=0 && i!=size-1 && !words.get(i).equals(",")){
                    result = result + " " + words.get(i);
                } else {
                    result += words.get(i);
                }
            }
            return result;
        }
        return null;
    }

    /**
     * Сгенерировать абзац с определенным количеством предложений
     * @param min - минимальное количество предложений в абзаце
     * @param max - максимальное количество предложений в абзаце
     * @return - абзац
     */
    public static String createRndParagraph(int min, int max){
        String result = "";
        if (max > 0 && max > min) {
            int rndLength = createRndInt(min, max);
            for (int i = 0; i < rndLength; i++) {
                result += createRndSentence(1,15) + " ";
            }
            return result + "\n";
        }
        return null;
    }

    /**
     * Сделать первую букву в слове заглавной
     * @param word - слово
     * @return - слово с заглавной буквой
     */
    private static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
