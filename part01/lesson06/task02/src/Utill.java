import java.security.SecureRandom;

/***
 * ��������������� �����
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class Utill {

    /**
     * ���������� ����������� ������������ ������ length
     *
     * @param length - ������ �����������
     * @return - ��������� ����������� ������� length
     */
    public static String createSentence(int length) {
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
     * ����������� ����������� ������� length �� ������ word
     *
     * @param length - ������ �����������
     * @param word
     * @return - ��������� ����������� ������� length �� ������ word
     */
    public static String createSentence(int length, String word) {
        if (length > 1) {
            if (word.length() + 1 == length) {
                return firstUpperCase(word + Utill.createRndMark());
            }
            if (word.length() + 2 < length && length != word.length()) {
                return firstUpperCase(word + " " + createSentence(length - word.length() - 1));
            } else {
                return firstUpperCase(createSentence(length));
            }
        }
        return null;
    }

    /**
     * ��������� ������� � ����������� � ������� ����� 2 ����
     *
     * @param sentence - �����������
     * @return - ����������� � �������
     */
    public static String addComma(String sentence) {
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
     * ����� �������� ����� � ��������� {min;max}
     *
     * @param max - ���������� �����
     * @param min - ���������� �����
     * @return - ����� � ��������� {min;max}
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
     * ������� ����� ������� �� min �� max
     *
     * @param min - ����������� ������ �����
     * @param max - ������������ ������ �����
     * @return - �����
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
     * ���������� ����� ������������ ������ length
     *
     * @param length - ������ �����
     * @return - ��������� ����� ������� length
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
     * ������� ��������� ������ ����� � �����
     *
     * @param word - ����� � ������� ���������� ������� ������ ����� ���������
     * @return - ����� � ������ ��������� ������
     */
    public static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * �������� ��������� ����� �� �������
     *
     * @param strArr - ������ ����
     * @return - ��������� �����
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
     * ���������� true � ������������ probability
     *
     * @param probability - �����������
     * @return - true/false
     */
    public static boolean isProbability(int probability) {
        if (probability >= 1 && probability <= 1000) {
            int rnd = createRndInt(1, 1000);
            if (rnd <= probability)
                return true;
        }
        return false;
    }

    /**
     * ������� ��������� ���� ���������� "!"/"?"/"."
     *
     * @return - ��������� ���� ���������� "!"/"?"/"."
     */
    public static String createRndMark() {
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
//        /**
//         * ���������� ��������� ����� ������������ ������
//         * @param min - ����������� ������ �����
//         * @param max - ������������ ������ �����
//         * @return - ��������� ����� ������������ ������
//         */
//        public static String createRndWord(int min, int max) {
//            if (max > 0 && max > min) {
//                int rndLength = createRndInt(min, max);
//                String word = "";
//                for (int i = 0; i < rndLength; i++) {
//                    word += (char) createRndInt(97, 122);
//                }
//                return word;
//            }
//            return null;
//        }


//    /**
//     * ������������� ����� � ������������ ����������� �����������
//     *
//     * @param min - ����������� ���������� ����������� � ������
//     * @param max - ������������ ���������� ����������� � ������
//     * @return - �����
//     */
//    public static String createRndParagraph(int min, int max) {
//        String result = "";
//        if (max > 0 && max > min) {
//            int rndLength = createRndInt(min, max);
//            for (int i = 0; i < rndLength; i++) {
//                result += createRndSentence(1, 15) + " ";
//            }
//            return result + "\n";
//        }
//        return null;
//    }


//    /**
//     * ���������� �������� ����������� c ������������ ����������� ����
//     *
//     * @param min - ����������� ���������� ���� � �����������
//     * @param max - ������������ ���������� ���� � �����������
//     * @return - �������� ����������� ������������ ������
//     */
//    public static String createRndSentence(int min, int max) {
//        if (max > 0 && max > min) {
//            int rndLength = createRndInt(min, max);
//            List<String> words = new ArrayList<>();
//            for (int i = 0; i < rndLength; i++) {
//                if (i == 0)
//                    words.add(firstUpperCase(createWord(1, 15)));
//                else
//                    words.add(createWord(1, 15));
//            }
//            if (rndLength > 1) {
//                int l = createRndInt(min, rndLength - 1);
//                words.add(l, ",");
//            }
//
//            words.add(createRndMark());
//            String result = "";
//            int size = words.size();
//            for (int i = 0; i < size; i++) {
//                if (i != 0 && i != size - 1 && !words.get(i).equals(",")) {
//                    result = result + " " + words.get(i);
//                } else {
//                    result += words.get(i);
//                }
//            }
//            return result;
//        }
//        return null;
//    }
}