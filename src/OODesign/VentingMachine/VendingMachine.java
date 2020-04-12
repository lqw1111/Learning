package OODesign.VentingMachine;

import OODesign.VentingMachine.exception.NotEnoughMoneyException;
import OODesign.VentingMachine.state.*;

import java.util.*;


public class VendingMachine {
    private List<Coin> coins;
    private Map<ItemInfo, Stack<Item>> items;
    private Map<String, ItemInfo> itemIdentifiers;
    private ItemInfo currentSelection;
    private List<Coin> currentCoins;
    private Stock stock;

    private State state;

    private HasSelectionState hasSelectionState;
    private CoinsInsertedState coinsInsertedState;
    private NoCoinState noCoinState;
    private NoSelectionState noSelectionState;
    private SoldOutState soldOutState;
    private SoldState soldState;

    public VendingMachine(Stock stock){
        hasSelectionState = new HasSelectionState(this);
        coinsInsertedState = new CoinsInsertedState(this);
        noCoinState = new NoCoinState(this);
        noSelectionState = new NoSelectionState(this);
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);
        state = noSelectionState;

        coins = new ArrayList<>();
        items = new HashMap<>();
        itemIdentifiers = new HashMap<>();
        currentSelection = null;
        currentCoins = new ArrayList<>();
        stock = this.stock;

        initial();
    }

    public void initial(){

    }

    public float selectItem(String selection){
        return state.selectItem(selection);
    }

    public void insertCoins(List<Coin> coins){
        state.insertCoins(coins);
    }

    public Pair executeTransaction(){
        return state.executeTransaction();
    }

    public List<Coin> cancelTransaction(){
        return state.cancelTransaction();
    }

    public void refillItems(List<Item> items){
        stock.fill(items);
    }

    // change status method
    public void changeToHasSelectionState() {
        this.state = hasSelectionState;
    }

    public void changeToNoCoinState(){
        this.state = noCoinState;
    }

    public void changeToCoinsInsertedState(){
        this.state = coinsInsertedState;
    }

    public void changeToNoSelectionState(){
        this.state = noSelectionState;
    }

    public void changeToSoldOutState(){
        this.state = soldOutState;
    }

    public void changeToSoldState(){
        this.state = soldState;
    }

    // actual implement
    public float findPrice(String selection) {
        ItemInfo itemInfo = itemIdentifiers.get(selection);
        currentSelection = itemInfo;
        return itemInfo.getPrice();
    }

    public void insert(List<Coin> coins) {
        this.currentCoins = coins;
    }

    public void insert(Coin coin){
        this.currentCoins.add(coin);
    }

    public Pair execute() {
        float totalCoinsValue = 0.0f;
        for (Coin c :
                coins) {
            totalCoinsValue += c.getValue();
        }

        float price = currentSelection.getPrice();
        if (totalCoinsValue < price)
            throw new NotEnoughMoneyException();

        List<Coin> refund = getRefund(totalCoinsValue - price);
        Item item = items.get(currentSelection).pop();

        return new Pair(item, refund);
    }

    private List<Coin> getRefund(float exchange){
        return new ArrayList<>();
    }


    public List<Coin> cancel() {
        List<Coin> res = currentCoins;
        currentCoins = new ArrayList<>();
        return res;
    }
}
