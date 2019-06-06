package part01.lesson08.task01;

import java.io.*;

/**
 * Класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 *
 * @version   1.0 13.05.2019
 * @author    Pavel Anisimov
 */

public class SerializableMgr {

    /**
     * Записывает объект в файл
     * @param object - объект
     * @param file - имя файла
     */
    public static void serialize (Object object, String file) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oos.writeObject(object);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Преобразует файл в объект
     * @param file - имя файла
     * @return - объект
     */
    public static Object deSerialize(String file){
        Object result = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
           result = ois.readObject();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
