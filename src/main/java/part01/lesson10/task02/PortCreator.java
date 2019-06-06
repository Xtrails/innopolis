package part01.lesson10.task02;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * Генератор портов
 *
 * @version   1.0 16.05.2019
 * @author    Pavel Anisimov
 */
public class PortCreator {

    private static Set<Integer> ports = new HashSet<>();
    private static final int MIN_PORTS_VALUE = 21000;
    private static final int MAX_PORTS_VALUE = 65000;

    private static PortCreator ourInstance = new PortCreator();


    /**
     * Создает случайный порт в диапазоне {MIN_PORTS_VALUE; MAX_PORTS_VALUE}
     * @return - порт в диапазоне {MIN_PORTS_VALUE; MAX_PORTS_VALUE}
     */
    public static Integer createPort() {
        Integer port = createRndInt(MIN_PORTS_VALUE, MAX_PORTS_VALUE);
        if (ports.size() == 0 || (ports.size() > 0 && !ports.contains(port))) {
            ports.add(port);
            return port;
        }
        return createPort();
    }

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
}
