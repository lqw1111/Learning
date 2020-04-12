package OODesign.VentingMachine;

import java.util.List;

public class Pair{
    public Item item;
    public List<Coin> coins;

    public Pair(Item item, List<Coin> coins) {
        this.item = item;
        this.coins = coins;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }
}
