import java.security.SecureRandom;

/**
 * Вспомогательный класс
 *
 * @version   1.0 7.05.2019
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
     * Создать массив случайных числе в диапозоне {min;max} размерностью size
     * @param min - наибольшее число в массиве
     * @param max - наименьшее число в массиве
     * @param size - размерность массива
     * @return - массив
     */
    public static int[] createRndIntArr(int min, int max, int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = createRndInt(min,max);
        }
        return arr;
    }
}
