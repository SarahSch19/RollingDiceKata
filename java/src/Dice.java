package src;

import Random.RNG;

public class Dice {
    private int value;

    public Dice(int value) {
        this.value = value;
    }

    public int rollDice() {
        //return RNG.random(value);
        return 4 ;
    }

    public int getValue() {
        return value;
    }
}
