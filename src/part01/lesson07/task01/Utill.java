package part01.lesson07.task01;

import java.math.BigInteger;
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
     *
     * @param min  - ���������� ����� � �������
     * @param max  - ���������� ����� � �������
     * @param size - ����������� �������
     * @return - ������
     */
    public static int[] createRndIntArr(int min, int max, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = createRndInt(min, max);
        }
        return arr;
    }

    /**
     * �������� ��������� �����
     *
     * @param value -�����
     * @param box   - ����������� ��������
     * @return - ��������� ����� value
     */
    public static BigInteger getFactorial(Integer value, FactorialBox box) {
        BigInteger result = BigInteger.valueOf(1);
        int start = 1;
        if (value > 0) {
            if (value <= box.getMaxKey()) {
                result = box.findValue(value);
                System.out.println("�������� ��������� �� " + value + ": " + result);
            } else {
                if (box.getMaxKey() > 0) {
                    start = box.getMaxKey();
                    result = box.findValue(start);
                    System.out.println("�������� ��������� �� " + start + ": " + result);
                }
                for (int i = start + 1; i <= value; i++) {
                    BigInteger tmp = result;
                    result = result.multiply(BigInteger.valueOf(i));
                    System.out.println("���������:" + tmp + "*" + i + "=" + result);
                    if (!box.getMemory().containsKey(i)) {
                        box.add(i, result);
                    }
                }
            }
            System.out.println("��������� ����� " + value + ": " + result);
            return result;
        }
        return null;
    }
}
