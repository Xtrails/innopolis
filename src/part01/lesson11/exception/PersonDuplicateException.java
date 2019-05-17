package part01.lesson11.exception;

/**
 * Пользовательское исключение для объектов Person
 *
 * @version   1.0 17.05.2019
 * @author    Pavel Anisimov
 */
public class PersonDuplicateException extends Exception {

    public PersonDuplicateException(String message) {
        super(message);
    }
}
