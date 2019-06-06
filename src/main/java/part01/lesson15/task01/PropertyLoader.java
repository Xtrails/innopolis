package part01.lesson15.task01;

import org.slf4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {

    private Logger log = org.slf4j.LoggerFactory.getLogger(PropertyLoader.class);

    private static PropertyLoader ourInstance = new PropertyLoader();

    public static PropertyLoader getInstance() {
        return ourInstance;
    }

    public Properties properties = new Properties();

    private PropertyLoader() {
        try (OutputStream output = new FileOutputStream("/home/user/rep/projects/innopolis/src/part01/lesson15/task01/resources/database.properties")) {

            //Get the properties value
            properties.getProperty("db.url");
            properties.getProperty("db.user");
            properties.getProperty("db.password");
            properties.getProperty("db.driver");

            log.info("Init properties complete:");
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                log.info(entry.getKey()+" : "+entry.getValue());
            }
        } catch (IOException e) {
            log.error("Ошибка при получение Properties: " + e.getMessage());
        }
    }
}
