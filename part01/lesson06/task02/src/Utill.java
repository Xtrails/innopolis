import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ��������������� �����
 *
 * @version   1.0 30.04.2019
 * @author    Pavel Anisimov
 */
public class Utill {

    /**
     * ������� ��������� ���� ����������
     * @return - ��������� ���� ���������� "!"/"?"/"."
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
     * ����� �������� ���������� ����� � ��������� {min;max}
     * @param max - ���������� ����� � �������
     * @param min - ���������� ����� � �������
     * @return - ����� � ��������� {min;max}
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
     * ���������� ��������� ����� ������������ ������
     * @param min - ����������� ������ �����
     * @param max - ������������ ������ �����
     * @return - ��������� ����� ������������ ������
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
     * ���������� �������� ����������� c ������������ ����������� ����
     * @param min - ����������� ���������� ���� � �����������
     * @param max - ������������ ���������� ���� � �����������
     * @return - �������� ����������� ������������ ������
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
     * ������������� ����� � ������������ ����������� �����������
     * @param min - ����������� ���������� ����������� � ������
     * @param max - ������������ ���������� ����������� � ������
     * @return - �����
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
     * ������� ������ ����� � ����� ���������
     * @param word - �����
     * @return - ����� � ��������� ������
     */
    private static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
