/**
 * Сериализуемый класс
 *
 * @version   1.0 13.05.2019
 * @author    Pavel Anisimov
 */
import java.io.Serializable;

public class Home implements Serializable {

    private String home;
    private static final long serialVersionUID = -6281898335580667474L;

    public Home(String home) {
        this.home = home;
    }

    public String getHome() {
        return home;
    }
}
