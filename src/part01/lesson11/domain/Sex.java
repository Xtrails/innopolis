package part01.lesson11.domain;

/**
 * Класс Sex со строковыми константами внутри MAN, WOMAN
 *
 * @version   1.0 17.05.2019
 * @author    Pavel Anisimov
 */
public enum Sex {
    MAN ("MAN"),
    WOMAN ("WOMAN");

    /** Заголовок */
    private String title;

    /**
     * Конструктор
     * @param title - заголовок
     */
    Sex(String title) {
        this.title = title;
    }

    /**
     * Получить заголовок
     * @return - заголовок
     */
    public String getTitle() {
        return title;
    }

    /**
     * Вывести заголовок в виде строки
     * @return - заголовок
     */
    @Override
    public String toString() {
        return "domain.Sex{" +
                "title='" + title + '\'' +
                '}';
    }
}