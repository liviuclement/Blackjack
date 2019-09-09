package pack;

public class Card{
    public CardType cardType;

    public CardSuit cardSuit;

    Card(CardType type,CardSuit suit)
    {
        cardType = type;
        cardSuit = suit;
    }
    @Override
    public String toString() {
        String card = "";
        card += cardType + " of " + cardSuit + " (" + cardType.getValue() + ")";
        return card;
    }



}
