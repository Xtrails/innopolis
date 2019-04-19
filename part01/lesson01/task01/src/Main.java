/**
 * Урок 1. Задание 1. Написать программу ”Hello, World!”. В ходе выполнения
 * программы она должна выбросить исключение и завершиться с ошибкой.
 * Смоделировав ошибку «NullPointerException»
 * Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 * Вызвав свой вариант ошибки через оператор throw
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        //Вызываем NullPointerException
        Test object = null;
        try{
            object.getArr();
        } catch (NullPointerException e){
            System.out.println(e);
        }

        //Вызываем ArrayIndexOutOfBoundsException
        object = new Test();
        object.setArr(new int[5]);
        try {
            int boom = object.getArr()[10];
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);

        }

        //Вызываем свой вариант ошибки
        try {
            throw new ClassCastException("ClassCastException");
        } catch (ClassCastException e){
            System.out.println(e);

        }
    }

    /**
     * Служебный класс для вызова необходимых исключений
     *
     * @version   1.0 19.04.2019
     * @author    Pavel Anisimov
     */
    static class Test {
        private int[] arr;

        public int[] getArr() {
            return arr;
        }

        public void setArr(int[] arr) {
            this.arr = arr;
        }
    }
}
