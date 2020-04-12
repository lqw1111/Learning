package OODesign.CoffeeMaker;

public class Mocha extends CoffeeDecorator {

    public Mocha(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + "Mocha";
    }

}
