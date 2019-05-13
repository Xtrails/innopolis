import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * ���� 9
 *
 * ��� ���������
 *
 * public interface Worker {
 *     void doWork();
 * }
 *
 * ���������� �������� ���������, ����������� ���������:
 *
 * ��������� � ������� ��������� ��������� ��� ������ doWork. ��� �� ������ ��������� ������� �������������� �������.
 * ����� ����� ������ ������ ���������� ������������ � ��������� ������ ����������� � ���� ������ public void doWork() � ����� SomeClass.java.
 * ���� SomeClass.java ������������� ���������� (� ��������) � ���� SomeClass.class.
 * ���������� ���� ������������ � ��������� � ������� ���������� ����������
 * �����, ��������� � �������, ����������� � �������� (���������� � ���������� ������� ������������� ������)
 *
 * @version   1.0 13.05.2019
 * @author    Pavel Anisimov
 */

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {

        Worker worker = new SomeClass();
//        String methodCode = readConsole();
        String methodCode = "123";

        System.out.println("��������� doWork():");
        worker.doWork();

        // Prepare source somehow.
        String source = "import java.io.Serializable;\n" +
                "\n" +
                "public class SomeClass implements Worker, Serializable {\n" +
                "\n" +
                "    @Override\n" +
                "    public void doWork() {\n" +
                "        System.out.println(\"doWork 1.0\");\n" +
                "    }\n" +
                "}\n";

        //https://stackoverflow.com/questions/2946338/how-do-i-programmatically-compile-and-instantiate-a-java-class
        //������� ������ 1:35:55

        // Save source in .java file.
        File root = new File("src/java"); // On Windows running on C:\, this is C:\java.
        File sourceFile = new File(root, "SomeClass.java");
        sourceFile.getParentFile().mkdirs();
        Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));

        // Compile source file.
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        // Load and instantiate compiled class.
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
        Class<?> cls = Class.forName("java.SomeClass", true, classLoader); // Should print "hello".
        Object instance = cls.newInstance(); // Should print "world".
        System.out.println(instance); // Should print "test.Test@hashcode".

        useCustomClassLoadre();

        System.out.println("��������� ����������� doWork():");
        worker.doWork();

    }

    private static  void useCustomClassLoadre() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader cl = new MyClassLoader();
        Class<?> kindClass = cl.loadClass("");
        Worker worker = (Worker) kindClass.newInstance();
        worker.doWork();
    }

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
