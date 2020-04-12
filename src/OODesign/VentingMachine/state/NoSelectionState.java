package OODesign.VentingMachine.state;

import OODesign.VentingMachine.*;

import java.util.ArrayList;
import java.util.List;

public class NoSelectionState implements State {

    VendingMachine vendingMachine;

    public NoSelectionState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public float selectItem(String selection) {
        float price = vendingMachine.findPrice(selection);
        vendingMachine.changeToHasSelectionState();
        return price;
    }

    @Override
    public void insertCoins(List<Coin> coins) {
        throw new RuntimeException("please select item first");
    }

    @Override
    public Pair executeTransaction() {
        throw new RuntimeException("please select item first");
    }

    @Override
    public List<Coin> cancelTransaction() {
        List<Coin> coins = vendingMachine.cancel();
        vendingMachine.changeToNoSelectionState();
        return coins;
    }
}
