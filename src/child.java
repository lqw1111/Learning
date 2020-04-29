import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        child child = (child) o;
        return Objects.equals(name, child.name) &&
                Objects.equals(age, child.age) &&
                Objects.equals(sport, child.sport);
    }

}
