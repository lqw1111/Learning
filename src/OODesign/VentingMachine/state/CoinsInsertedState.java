package OODesign.VentingMachine.state;

import OODesign.VentingMachine.*;

import java.util.List;

public class CoinsInsertedState implements State {

    VendingMachine vendingMachine;

    public CoinsInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    @Override
    public float selectItem(String selection) {
        throw new RuntimeException("Already have selected");
    }

    @Override
    public void insertCoins(List<Coin> coins) {
        vendingMachine.insert(coins);
    }

    @Override
    public Pair executeTransaction() {
        Pair p = vendingMachine.execute();
        vendingMachine.changeToNoSelectionState();
        return p;
    }

    @Override
    public List<Coin> cancelTransaction() {
        List<Coin> coins = vendingMachine.cancel();
        vendingMachine.changeToNoSelectionState();
        return coins;
    }
}
