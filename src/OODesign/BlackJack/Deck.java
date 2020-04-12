package OODesign.BlackJack;

import java.util.*;

public class Deck {
    private Dealer dealer;
    private Stack<Card> cards;
    private List<Player> players;
    private Map<Player, Integer> bets;

    public Deck(){
        this.dealer = new Dealer(this);
        cards = new Stack<>();
        players = new ArrayList<>();
        bets = new HashMap<>();
    }

    public void addPlayer(Player player){
        players.add(player);
        bets.put(player, 0);
    }

    public void placeBets(int amount, Player player){
        bets.put(player, bets.getOrDefault(player, 0) + amount);
    }

    public void deadInitialCard(){
        for (Player p:
             players) {
            p.insertCard(cards.pop());
        }
    }

    public Card deadNextCard(){
        return cards.pop();
    }

    public void shuffleCard(){

    }

    public void compareResult(){
        for (Player player:
             players) {
            if (dealer.win(player)){
                bets.put(player, 0);
            } else {
                int amount = bets.get(player) * 2;
                player.updateBets(amount);
                bets.put(player, 0);
            }
        }
    }
}
