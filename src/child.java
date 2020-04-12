import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class child {

    private String name;
    private String age;
    private String sport;

    public child() {
    }

    public child(String name, String age, String sport) {
        this.name = name;
        this.age = age;
        this.sport = sport;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

//    public child generateChild(father fa, child c) throws IllegalAccessException, NoSuchFieldException {
//        Field[] fields = fa.getClass().getDeclaredFields();
//
//        for(Field f:fields){
//            String field = f.toString().substring(f.toString().lastIndexOf(".")+1);
//            f.setAccessible(true);
//            System.out.println("fa."+field+" --> "+f.get(fa));
//
//            Field cf = c.getClass().getDeclaredField(field);
//
//            cf.setAccessible(true);
//
//            cf.set(c, f.get(fa));
//        }
//
//        return c;
//    }


    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        thread t = new thread();



    }
}
