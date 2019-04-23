/**
 * Урок 4. Задание 3.
 *
 * Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox.
 * Необходимо сделать такую связь, правильно распределить поля и методы.
 * Функциональность в целом должна сохраниться.
 * При попытке положить Object в MathBox должно создаваться исключение.
 *
 * @version   1.0 23.04.2019
 * @author    Pavel Anisimov
 */
public class Main {

    public static void main(String[] args) {

        //Проверка по task01
        Number[] arr = new Number[]{1,1.5,5.5F,2D,1,10};
        MathBox<Number> box = new MathBox<>(arr);
        System.out.println(box.toString());

        Double sum = MathBox.summator(box.getList());
        System.out.println(sum);

        box.deleteIntVal(10);
        System.out.println(box.toString());

        box.splitter(2);
        System.out.println(box.toString());


        //Проверка по task02
        Integer[] intArr = new Integer[]{1,2,3,4};

        ObjectBox<Integer> objectBox = new ObjectBox<>(intArr);
        objectBox.dump();

        objectBox.addObject(5);
        objectBox.dump();

        objectBox.deleteObject(1);
        objectBox.dump();


        //Проверка по task03
        MathBox<ObjectBox<Integer>> errorBox = new MathBox<>(box);
    }
}
