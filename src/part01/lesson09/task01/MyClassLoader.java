package part01.lesson09.task01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if("SomeClass".equals(name)){
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if("SomeClass".equals(name)){
            try{
                byte[] bytes = Files.readAllBytes(Paths.get("src/part01/lesson09/task01/SomeClass.class"));
                return defineClass("part01.lesson09.task01." + name,bytes,0,bytes.length);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
