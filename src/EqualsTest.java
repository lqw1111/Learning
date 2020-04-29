import java.util.*;

class User {
    private String name;//姓名
    private String IdCard;//身份证
    private int age;//年龄

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            if (user.getIdCard().equals(this.IdCard) && user.getName().equals(this.name) &&      user.getAge() == this.age) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, IdCard, age);
    }
}
public class EqualsTest {



    public static void main(String[] args) {
        User user1=new User();
        user1.setName("路西");
        user1.setAge(18);
        user1.setIdCard("430");
        User user2=new User();
        user2.setName("路西");
        user2.setAge(18);
        user2.setIdCard("430");
        System.out.println("user1.equals(user2)="+user1.equals(user2));

        Set set =new HashSet();
        set.add(user1);
        set.add(user2);
        Map map=new HashMap();
        map.put(user1,"user1");
        map.put(user2,"user2");
        System.out.println("set 长度"+set.size());
        System.out.println("map 长度"+map.keySet().size());;
    }
}
