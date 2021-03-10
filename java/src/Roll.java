package src;
import java.util.regex.*;


import java.lang.reflect.Array;

public class Roll {

    public enum RollType {
        NORMAL,
        ADVANTAGE,
        DISADVANTAGE
    }

    // Attributes
    private int diceValue ;
    private int nbRoll ;
    private int modifier ;
    private int value ;
    private int[] results ;
    private boolean valid = true ;


    public void print (int var) {
        System.out.print(var) ;
    }
    public Roll(String formula) {
        Pattern pattern = Pattern.compile("(\\d)*[d]((\\p{Punct})?(\\d)+)((\\p{Punct})(\\d)+)*") ;
        Matcher matcher = pattern.matcher(formula) ;
        this.valid = matcher.matches() ;

        if (this.valid == true) {
            if (matcher.group(1) == null) {
                if (formula.charAt(0) == 'd')
                    this.nbRoll = 1;
                else
                    this.valid = false;
            } else
                this.nbRoll = Integer.parseInt(matcher.group(1));

            if (this.nbRoll <= 0)
                this.valid = false;

            this.diceValue = matcher.group(2) == null ? 0 : Integer.parseInt(matcher.group(2));
            if (this.diceValue <= 0)
                this.valid = false;

            if (matcher.group(5) != null) {
                if (matcher.group(6) != null && Pattern.matches("[+-]", matcher.group(6))) {
                    this.modifier = matcher.group(5) == null ? 0 : Integer.parseInt(matcher.group(5));
                } else {
                    this.valid = false;
                }
            }
        }
    }

    public Roll(int diceValue, int nbRoll, int modifier) {
        if (diceValue <= 0)
            this.valid = false ;
        this.diceValue = diceValue ;

        if (nbRoll <= 0)
            this.valid = false ;
        this.nbRoll = nbRoll ;

        this.modifier = modifier ;
        this.results = new int[2] ;
    }

    public int makeRoll(RollType rollType) {
        Dice d = new Dice(this.diceValue) ;
        int tmp = 0 ;

        this.value = 0 ;

        if (this.valid == false)
            return -1 ;

        for (int i = 0 ; i < nbRoll ; ++i) {
            switch (rollType) {
                case ADVANTAGE:
                    this.getResults(d);
                    tmp = this.getMax();
                    break;
                case DISADVANTAGE:
                    this.getResults(d);
                    tmp = this.getMin();
                    break;
                case NORMAL:
                    tmp = d.rollDice();
                    break;
            }
            this.value += tmp ;
        }
        this.value += modifier ;

        if (this.value < 0)
            this.value = 0 ;

        return this.value;
    }

    public void getResults (Dice d) {
        for (int i = 0 ; i < 2 ; ++i) {
            this.results[i] = d.rollDice() ;
        }
    }

    private int getMax () {
        int max = -1 ;
        for (int i = 0 ; i < 2 ; ++i) {
            if (this.results[i] > max)
                max = this.results[i] ;
        }
        return max ;
    }

    private int getMin () {
        int min = 100 ;
        for (int i = 0 ; i < 2 ; ++i) {
            if (this.results[i] < min)
                min = this.results[i] ;
        }
        return min ;
    }

}
