
class A {
    private String a;
    private String b;

    public A(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}

class B extends A {

    public B(String a, String b) {
        super(a, b);
    }
}

public class ss {
    public static void main(String[] args) {
        B b = new B("a","b");
        b.getA();
    }
}
