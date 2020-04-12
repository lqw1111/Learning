package OODesign.BlackJack;

public class Player {
    private Deck deck;
    private int bets;
    private Hand hand;

    public void joinGame(Deck deck){
        deck.addPlayer(this);
    }

    public void placeBets(int amount){
        if (amount > bets)
            throw new RuntimeException("No enough money");
        deck.placeBets(amount, this);
    }

    public void dealNextCard(){
        Card c = deck.deadNextCard();
        insertCard(c);
    }

    public void insertCard(Card card){
        hand.insertCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void updateBets(int amount) {
        this.bets += amount;
    }
}
