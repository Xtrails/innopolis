package part01.lesson15.task01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Загрузчик найтсроек бд
 *
 * @version   1.0 06.06.2019
 * @author    Pavel Anisimov
 */
public class PropertyLoader {

    private Logger log = LoggerFactory.getLogger(PropertyLoader.class);

    private static PropertyLoader ourInstance = new PropertyLoader();

    private String PATH = "/home/user/rep/projects/innopolis/src/main/java/part01/lesson15/task01/resources/database.properties";

    public static PropertyLoader getInstance() {
        return ourInstance;
    }

    public Properties properties = new Properties();

    private PropertyLoader() {
        try (FileInputStream ip= new FileInputStream(PATH)) {

            properties.load(ip);

            log.info("Init properties complete");
//            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
//                log.info(entry.getKey()+" : "+entry.getValue());
//            }
        } catch (IOException e) {
            log.error("Ошибка при получение Properties: " + e.getMessage());
        }
    }
}
