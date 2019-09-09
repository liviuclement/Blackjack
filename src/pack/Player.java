package pack;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private int money;
    private List<Card> hand;
    private int total;
    private int bet;
    public boolean keepPlaying = true;
    public Player()
    {
        money = 100;
        hand = new ArrayList<>();
        bet = 0;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal()
    {
        total = 0;
        for(int i = 0; i < hand.size(); i++)
            total += hand.get(i).cardType.getValue();
        return total;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }

    public void addCardToHand(Card card)
    {
        hand.add(card);
    }

    public int getMoney() {
        return money;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void intro()
    {
        System.out.println("Welcome to the game!");
    }

    public void bet()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have $" + money);
        System.out.println("How much would you like to bet? ");
        bet = scanner.nextInt();
            while (bet > money)
            {
                System.out.println("You don't have enough money! Place a bet within your limits!");
                bet = scanner.nextInt();
            }
        money -= bet;
    }

    public void refreshHand()
    {
        hand = new ArrayList<>();
    }

    public int aceValue()
    {
        int count = 0;
        for (Card card:hand
             ) {
            if(card.cardType == CardType.ACE) {
                count++;
            }
        }
        return count;
    }
}
