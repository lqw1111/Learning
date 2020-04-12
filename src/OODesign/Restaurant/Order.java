package OODesign.Restaurant;

import java.util.List;

public class Order {
    private List<Meal> meals;
    private Table table;
    private Party party;

    public Order(List<Meal> meals, Table table, Party party) {
        this.meals = meals;
        this.table = table;
        this.party = party;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
