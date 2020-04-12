package OODesign.BlackJack;

public class Dealer {
    private Deck deck;
    private Hand hand;
    int bets;

    public Dealer(Deck deck){
        this.deck = deck;
    }

    public boolean win(Player player){
        if (hand.sum() > player.getHand().sum()){
            return true;
        }else
            return false;
    }
}
