import java.security.SecureRandom;
/***
 * Вспомогательный класс
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class Utill {
    /**
     * Метод создания числа в диапозоне {min;max}
     *
     * @param max - наибольшее число
     * @param min - наименьшее число
     * @return - число в диапозоне {min;max}
     */
    private static int createRndInt(int min, int max) {
        if (max > min) {
            SecureRandom rnd = new SecureRandom();
            int result = rnd.nextInt(max - min) + min;
            return result;
        } else if (min == max) {
            return min;
        }
        return -1;
    }

    /**
     * Создать слово длинной от min до max
     *
     * @param min - минимальная длинна слова
     * @param max - максимальная длинна слова
     * @return - слово
     */
    public static String createWord(int min, int max) {
        String word = "";
        if (min > 0 && max > min) {
            int rndInt = createRndInt(min, max);
            word = createWord(rndInt);
        } else if (min == max) {
            word = createWord(min);
        }
        return word;
    }

    /**
     * Генерирует слово определенной длинны length
     *
     * @param length - длинна слова
     * @return - случайное слово длинной length
     */
    public static String createWord(int length) {
        String word = "";
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                int rndInt = createRndInt(97, 122);
                word += (char) rndInt;
            }
        }
        return word;
    }

    /**
     * Сделать заглавной первую букву в слове
     *
     * @param word - слово в котором необходимо сделать первую букву заглавной
     * @return - слово с первой заглавной буквой
     */
    public static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * Генерирует предложение определенной длинны length
     *
     * @param length - длинна предложения
     * @return - случайное предложение длинной length
     */
    public static String createSentence(int length) {
        if(length>2){
            String sentence = "";

        }
        return null;
    }

    /**
     * Получить случайное слово из массива
     *
     * @param strArr - массив слов
     * @return - случайное слово
     */
    public static String getRandomWordFromArr(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            SecureRandom rnd = new SecureRandom();
            int rndInt = rnd.nextInt(strArr.length);
            return strArr[rndInt];
        }
        return null;
    }

    /**
     * Возвращает true с вероятностью probability
     * @param probability - вероятность
     * @return - true/false
     */
    public static boolean isProbability(int probability){
        if(probability>=1 && probability<=1000){
            int rnd = createRndInt(1,1000);
            if(rnd<=probability)
                return true;
        }
        return false;
    }
}
