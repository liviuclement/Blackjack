package pack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pack {
    List<Card> pack;
    List<Card> usedPack;

    public Pack()
    {
        pack = new ArrayList<Card>();
        usedPack = new ArrayList<Card>();
        for (int i = 0; i < 6; i++)
            for(CardType type:CardType.values())
                for (CardSuit suit:CardSuit.values()) {
                    Card card = new Card(type,suit);
                    pack.add(card);
                }
    }

    public List<Card> getPack() {
        return pack;
    }

    public List<Card> getUsedPack() {
        return usedPack;
    }

    public void setPack(List<Card> pack) {
        this.pack = pack;
    }

    public void setUsedPack(List<Card> usedPack) {
        this.usedPack = usedPack;
    }

    public List<Card> shuffleCards ()
    {
        Random random = new Random();
        List<Card> newPack = new ArrayList<>();
        pack.addAll(usedPack);
        for(int i = 0; i < pack.size(); i++)
        {
            Card temp = pack.remove(random.nextInt(pack.size()));
            newPack.add(temp);
        }
        usedPack = new ArrayList<Card>();
        return newPack;
    }

    public Card drawCard()
    {
        Card temp = pack.remove(0);
        usedPack.add(temp);
        return temp;
    }

}
