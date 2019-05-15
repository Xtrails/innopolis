package part01.lesson01.task02;

import java.security.SecureRandom;

/**
 * Урок 1. Задание 2. Составить программу, генерирующую N случайных чисел. Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран. Предусмотреть что первоначальные числа могут
 * быть отрицательные, в этом случае генерировать исключение.
 */

public class Main {
    public static void main(String[] args) {
        int n = 100;
        int[] arr;
        arr = MyClass.createRndArr(n,100,0);

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i : arr) {
            MyClass.printSqrt(i);
        }
    }

    /**
     * Вспомогательный класс
     *
     * @version   1.10 19.04.2019
     * @author    Pavel Anisimov
     */
    private static class MyClass {

        /**
         * Метод создания массива чисел от -100 до 100
         *
         * @param count - размер массива
         * @param max   - наибольшее число в массиве
         * @param min   - наименьшее число в массиве
         * @return - массив чисел в диапозоне {min;max}
         */
        private static int[] createRndArr(int count, int max, int min) {
            if (max > 0 && max > min) {
                SecureRandom rnd = new SecureRandom();
                int[] result = new int[count];
                max++;
                for (int i = 0; i < count; i++) {
                    result[i] = rnd.nextInt(max - min) + min;
                }
                return result;
            }
            return null;
        }

        /**
         * Для каждого числа k вычисляет квадратный корень q.
         * Если квадрат целой части q числа равен k, то выводит это число на экран.
         * @param k - число у которого ищем корень
         */
        private static void printSqrt(int k) {
            if (k >= 0) {
                double q = Math.sqrt(k);
                if((int) q == k){
                    System.out.println(" " + q);
                }
            } else {
                try {
                    throw new IllegalArgumentException("На вход поданно отрицательное число");
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            }
        }
    }
}