package JVM;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class load7 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader();
        classLoader.loadClass("HelloWorld");
    }
}

class MyClassLoader extends ClassLoader {

    @Override //name 就是类名称
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "/Users/luoqinwei/Documents/test/src/" + name + ".class";

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Files.copy(Paths.get(path),os);

            byte[] bytes = os.toByteArray();

            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException("a", e);
        }
    }
}
