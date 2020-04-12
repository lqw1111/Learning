package OODesign.CoffeeMaker;

public class test {
    public static void main(String[] args) {
        Coffee coffee = new Decaf();
        System.out.println(coffee.getDescription());
        coffee = new Mocha(coffee);
        System.out.println(coffee.getDescription());
    }
}
