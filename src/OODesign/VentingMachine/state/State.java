package OODesign.VentingMachine.state;

import OODesign.VentingMachine.Coin;
import OODesign.VentingMachine.Pair;

import java.util.List;

public interface State {
    float selectItem(String selection);
    void insertCoins(List<Coin> coins);
    Pair executeTransaction();
    List<Coin> cancelTransaction();
}
