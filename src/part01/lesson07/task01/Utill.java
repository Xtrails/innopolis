package part01.lesson07.task01;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * ¬спомогательный класс
 *
 * @version   1.0 7.05.2019
 * @author    Pavel Anisimov
 */
public class Utill {

    /**
     * ћетод создани€ числа в диапозоне {min;max}
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
     * —оздать массив случайных числе в диапозоне {min;max} размерностью size
     *
     * @param min  - наибольшее число в массиве
     * @param max  - наименьшее число в массиве
     * @param size - размерность массива
     * @return - массив
     */
    public static int[] createRndIntArr(int min, int max, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = createRndInt(min, max);
        }
        return arr;
    }

    /**
     * ѕолучить факториал числа
     *
     * @param value -число
     * @param box   - сохраненные значени€
     * @return - факториал числа value
     */
    public static BigInteger getFactorial(Integer value, FactorialBox box) {
        BigInteger result = BigInteger.valueOf(1);
        int start = 1;
        if (value > 0) {
            if (value <= box.getMaxKey()) {
                result = box.findValue(value);
                System.out.println("ѕолучаем факториал от " + value + ": " + result);
            } else {
                if (box.getMaxKey() > 0) {
                    start = box.getMaxKey();
                    result = box.findValue(start);
                    System.out.println("ѕолучаем факториал от " + start + ": " + result);
                }
                for (int i = start + 1; i <= value; i++) {
                    BigInteger tmp = result;
                    result = result.multiply(BigInteger.valueOf(i));
                    System.out.println("¬ычисл€ем:" + tmp + "*" + i + "=" + result);
                    if (!box.getMemory().containsKey(i)) {
                        box.add(i, result);
                    }
                }
            }
            System.out.println("‘акториал числа " + value + ": " + result);
            return result;
        }
        return null;
    }
}
