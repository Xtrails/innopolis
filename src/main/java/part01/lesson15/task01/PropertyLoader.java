package part01.lesson15.task01;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Загрузчик найтсроек
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class PropertyLoader {

    private Logger log = LoggerFactory.getLogger(PropertyLoader.class);

    private static PropertyLoader ourInstance = new PropertyLoader();

    public static PropertyLoader getInstance() {
        return ourInstance;
    }

    public Properties properties = new Properties();

    /**
     * Загружает настройки из файлов
     */
    private PropertyLoader() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("log4j.properties");
             InputStream ip = this.getClass().getClassLoader().getResourceAsStream("database.properties")) {
            PropertyConfigurator.configure(is);
            properties.load(ip);

            log.info("\n========================\nInit properties complete\n========================");
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                log.info(entry.getKey() + " : " + entry.getValue());
            }
        } catch (IOException e) {
            log.error("Ошибка при получение Properties: " + e.getMessage());
        }
    }
}
