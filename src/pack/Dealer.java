package pack;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {
    private int stash;
    List<Card> cards;
    public Dealer()
    {
        stash = Integer.MAX_VALUE;
        cards = new ArrayList<>();
    }

    public int getStash() {
        return stash;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setStash(int stash) {
        this.stash = stash;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
