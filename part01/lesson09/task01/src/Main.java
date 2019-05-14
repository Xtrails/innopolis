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

        //��������� ��� ������ SomeClass
        Path path = Paths.get("src/SomeClass.java");
        String someClass = Files.readString(path, StandardCharsets.UTF_8);
        String methodCode = readConsole();
        someClass = someClass.substring(0, someClass.indexOf("doWork()") + 10) + methodCode + "}\n}\n";

        System.out.println("��������� doWork():");
        Worker worker = new SomeClass();
        worker.doWork();

        //������� ����������� java ���� � ����������� ���
        File root = new File("src");
        File sourceFile = new File(root, "/SomeClass.java");
        sourceFile.getParentFile().mkdirs();
        Files.write(sourceFile.toPath(), someClass.getBytes(StandardCharsets.UTF_8));
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        //��������� � ���������� ����������� ����� doWork()
        useCustomClassLoader();

    }

    /**
     * ����� ���������� ClassLoader
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
     * ���������� � ������� �� ����� ������ ������
     * @return - ����� ��������� � �������
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
