package pack;

import java.util.*;

public enum CardType {
    TWO(2), THREE(3), FOUR(4),
    FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10),
    ACE(11);

    private Integer value;

    CardType(Integer value)
    {
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }
}
