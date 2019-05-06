import java.security.SecureRandom;
/***
 * ��������������� �����
 *
 * @version   1.0 29.04.2019
 * @author    Pavel Anisimov
 */
public class Utill {
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
     * ���������� ����������� ������������ ������ length
     *
     * @param length - ������ �����������
     * @return - ��������� ����������� ������� length
     */
    public static String createSentence(int length) {
        if(length>2){
            String sentence = "";

        }
        return null;
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
     * @param probability - �����������
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
