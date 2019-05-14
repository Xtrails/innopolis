import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Урок 9
 *
 * Дан интерфейс
 *
 * public interface Worker {
 *     void doWork();
 * }
 *
 * Необходимо написать программу, выполняющую следующее:
 *
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 *
 * @version   1.0 13.05.2019
 * @author    Pavel Anisimov
 */

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {

        //Считываем код класса SomeClass
        Path path = Paths.get("src/SomeClass.java");
        String someClass = Files.readString(path, StandardCharsets.UTF_8);
        String methodCode = readConsole();
        someClass = someClass.substring(0, someClass.indexOf("doWork()") + 10) + methodCode + "}\n}\n";

        System.out.println("Выполняем doWork():");
        Worker worker = new SomeClass();
        worker.doWork();

        //Создаем обновленный java файл и компилируем его
        File root = new File("src");
        File sourceFile = new File(root, "/SomeClass.java");
        sourceFile.getParentFile().mkdirs();
        Files.write(sourceFile.toPath(), someClass.getBytes(StandardCharsets.UTF_8));
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        //Загружаем и используем обновленный метод doWork()
        useCustomClassLoader();

    }

    /**
     * Вызов кастомного ClassLoader
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static  void useCustomClassLoader() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader cl = new MyClassLoader();
        Class<?> kindClass = cl.loadClass("SomeClass");
        Worker worker = (Worker) kindClass.newInstance();
        worker.doWork();
    }

    /**
     * Считывание с консоли до ввода пустой строки
     * @return - текст введенный с консоли
     */
    private static String readConsole(){
        String code = "";
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bf.readLine();
            while (!line.isEmpty()) {
                code += line;
                line = bf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
}
