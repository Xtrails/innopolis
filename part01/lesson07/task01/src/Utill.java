import java.security.SecureRandom;

/**
 * ��������������� �����
 *
 * @version   1.0 7.05.2019
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
     * ������� ������ ��������� ����� � ��������� {min;max} ������������ size
     * @param min - ���������� ����� � �������
     * @param max - ���������� ����� � �������
     * @param size - ����������� �������
     * @return - ������
     */
    public static int[] createRndIntArr(int min, int max, int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = createRndInt(min,max);
        }
        return arr;
    }
}
