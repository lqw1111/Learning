package java8.Default;

public class DefaultInAction {

    public static void main(String[] args) {

    }

    private interface A {
        default void hello() {
            System.out.println("==A.hello==");
        }
    }

    private interface B extends A {
        @Override
        default void hello() {
            System.out.println("==B.hello==");
        }
    }

    private static class C implements B {

    }
}
