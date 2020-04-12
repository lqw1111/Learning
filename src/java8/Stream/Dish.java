package java8.Stream;

public class Dish {
    private final String name;
    private final boolean vegatarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegatarian, int calories, Type type) {
        this.name = name;
        this.vegatarian = vegatarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegatarian() {
        return vegatarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegatarian=" + vegatarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    public enum Type{
        MEAT,OTHER, FISH
    }
}
