package OODesign.Restaurant;

public class Meal {
    private String name;
    private float price;

    public Meal(String name, float price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
