package OODesign.BlackJack;

import java.util.List;

public class Hand {
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void insertCard(Card card){
        cards.add(card);
    }

    public int sum(){
        int count = 0;
        for (Card card : cards){
            count += card.getValue();
        }
        return count;
    }
}
