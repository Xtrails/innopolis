package part01.lesson01.task03.exception;

/**
 * Пользовательское исключение для объектов Person
 *
 * @version   1.0 22.04.2019
 * @author    Pavel Anisimov
 */
public class PersonDuplicateException extends Exception {

    public PersonDuplicateException(String message) {
        super(message);
    }
}
