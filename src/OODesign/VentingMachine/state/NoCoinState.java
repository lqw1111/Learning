package OODesign.VentingMachine.state;

import OODesign.VentingMachine.*;

import java.util.List;

public class NoCoinState implements State {
    VendingMachine vendingMachine;

    public NoCoinState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }


    @Override
    public float selectItem(String selection) {
        return 0;
    }

    @Override
    public void insertCoins(List<Coin> coins) {

    }

    @Override
    public Pair executeTransaction() {
        return null;
    }

    @Override
    public List<Coin> cancelTransaction() {
        return null;
    }
}
