package OODesign.VentingMachine.state;

import OODesign.VentingMachine.*;

import java.util.List;

public class HasSelectionState implements State {
    VendingMachine vendingMachine;

    public HasSelectionState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public float selectItem(String selection) {
        throw new RuntimeException("Already have selection");
    }

    @Override
    public void insertCoins(List<Coin> coins) {
        vendingMachine.insert(coins);
        vendingMachine.changeToCoinsInsertedState();
    }

    @Override
    public Pair executeTransaction() {
        throw new RuntimeException("Please insert coin");
    }

    @Override
    public List<Coin> cancelTransaction() {
        List<Coin> coins = vendingMachine.cancel();
        vendingMachine.changeToNoSelectionState();
        return coins;
    }
}
