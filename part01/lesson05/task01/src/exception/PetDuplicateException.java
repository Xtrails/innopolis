package exception;

/**
 * Пользовательское исключение для объектов Pet
 *
 * @version   1.0 26.04.2019
 * @author    Pavel Anisimov
 */
public class PetDuplicateException extends Exception {

    public PetDuplicateException(String message) {
        super(message);
    }
}