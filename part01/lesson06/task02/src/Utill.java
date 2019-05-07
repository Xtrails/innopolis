import java.security.SecureRandom;

/***
 * Вспомогательный класс
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class Utill {

    /**
     * Генерирует предложение определенной длинны length
     *
     * @param length - длинна предложения
     * @return - случайное предложение длинной length
     */
    private static String createSentence(int length) {
        if (length > 1) {
            length--;
            String sentence = "";
            if (length <= 15) {
                sentence = createWord(length);
            }
            if (length > 15 && length % 15 == 1) {
                int count = length / 16;
                int ost = length % 16;
                if (ost == 0) {
                    count--;
                }
                for (int i = 0; i < count; i++) {
                    sentence += " " + createWord(14);
                }
                sentence += " " + createWord(7) + " " + createWord(8);
            } else if (length > 15 && (length % 15 > 1 || length % 15 == 0)) {
                int count = length / 16;
                int ost = length % 16;
                if (ost == 0) {
                    count--;
                    sentence += " " + createWord(7) + " " + createWord(8);
                } else
                    sentence += " " + createWord(ost);
                for (int i = 0; i < count; i++) {
                    sentence += " " + createWord(15);
                }
            }
            return sentence.trim() + createRndMark();
        }
        return null;
    }

    /**
     * Гененрирует предложение длинной length со словом word
     *
     * @param length - длинна предложения
     * @param word - слово входящее в предложение
     * @return - случайное предложение длинной length со словом word
     */
    private static String createSentence(int length, String word) {
        if (length > 1) {
            String sentence;
            if (word.length() + 1 == length) {
                sentence = firstUpperCase(word + Utill.createRndMark());
            }
            if (word.length() + 2 < length && length != word.length()) {
                sentence = firstUpperCase(word + " " + createSentence(length - word.length() - 1));
            } else {
                sentence = firstUpperCase(createSentence(length));
            }
            if(!sentence.isEmpty() && sentence.split(" ").length>2)
                sentence = addComma(sentence);
            return sentence;
        }
        return null;
    }

    /**
     * Гененрирует предложение длинной length с вероятностью probability вхождения слова word
     *
     * @param length - длинна предложения
     * @param word - слово входящее в предложение
     * @param probability - вероятность вхождения слова word  в предложение
     * @return - случайное предложение длинной length со словом word
     */
    public static String createSentence(int length, String word, double probability) {
        if(isProbability(probability)){
            return createSentence(length,word);
        }
        return firstUpperCase(createSentence(length));
    }

    /**
     * Вставляет запятую в предложение в котором более 2 слов
     *
     * @param sentence - предложение
     * @return - предложение с запятой
     */
    private static String addComma(String sentence) {
        if (!sentence.isEmpty() && sentence.contains(" ")) {
            String[] words = sentence.split(" ");
            String result = "";
            if (words.length > 2) {
                if (words[1].length() > 1) {
                    words[1] = words[1].substring(0, words[1].length() - 1) + ",";
                    for (int i = 0; i < words.length; i++) {
                        if (i == words.length - 1)
                            result += words[i];
                        else
                            result += words[i] + " ";
                    }
                    return result;
                }
            }
        }
        return sentence;
    }

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
     * Генерирует слово определенной длинны length
     *
     * @param length - длинна слова
     * @return - случайное слово длинной length
     */
    private static String createWord(int length) {
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
    private static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
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
     *
     * @param probability - вероятность
     * @return - true/false
     */
    private static boolean isProbability(Double probability) {
        if (probability > 0 && probability < 1) {
            int value = (int) (probability * 1000);
            if (value >= 1 && value <= 1000) {
                int rnd = createRndInt(1, 1000);
                if (rnd <= value)
                    return true;
            }
        }
        return false;
    }

    /**
     * Создает случайный знак препинания "!"/"?"/"."
     *
     * @return - случайный знак препинания "!"/"?"/"."
     */
    private static String createRndMark() {
        SecureRandom rnd = new SecureRandom();
        int i = rnd.nextInt(3);
        switch (i) {
            case 0:
                return "!";
            case 1:
                return "?";
            case 2:
                return ".";
        }
        return "";
    }
}