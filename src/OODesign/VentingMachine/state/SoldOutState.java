package OODesign.VentingMachine.state;

import OODesign.VentingMachine.*;

import java.util.List;

public class SoldOutState implements State {
    VendingMachine vendingMachine;

    public SoldOutState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }


    @Override
    public float selectItem(String selection) {
        throw new RuntimeException("Need Refill");
    }

    @Override
    public void insertCoins(List<Coin> coins) {
        throw new RuntimeException("Need Refill");
    }

    @Override
    public Pair executeTransaction() {
        throw new RuntimeException("Need Refill");
    }

    @Override
    public List<Coin> cancelTransaction() {
        throw new RuntimeException("Need Refill");
    }
}
