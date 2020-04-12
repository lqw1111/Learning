package OODesign.CoffeeMaker;

public class Milk extends CoffeeDecorator {

    public Milk(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + " Milk";
    }
}
