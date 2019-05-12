import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static java.lang.String.format;

/**
 * Урок 7.
 *
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива. Использовать пул потоков для решения задачи.
 *
 * Особенности выполнения:
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти,
 * очень вероятен StackOverFlow. Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 *
 * По сути, есть несколько способа решения задания:
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллеливать вычисления для разных чисел
 * 3) комбинированный
 *
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого, что будет гораздо быстрее
 *
 * @version   1.0 7.05.2019
 * @author    Pavel Anisimov
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException{

        int n = 100;
        int[] arr = Utill.createRndIntArr(1,15,n);

        FactorialBox box = FactorialBox.getInstance();

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        List<Future<BigInteger>> futures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int j = arr[i];
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> Utill.getFactorial(j,box),
                            threadPool
                    ));
        }

//        BigInteger value = BigInteger.valueOf(0);
//        for (Future<BigInteger> future : futures) {
//            System.out.println(future.get());
//        }

        threadPool.shutdown();
    }
}
