package part01.lesson08.task02; /**
 * Сериализуемый класс
 *
 * @version   1.0 13.05.2019
 * @author    Pavel Anisimov
 */
import java.io.Serializable;

public class Home implements Serializable {
    private String home;

    public Home(String home) {
        this.home = home;
    }

    public String getHome() {
        return home;
    }
}
