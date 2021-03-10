package test;

import Random.RNG;
import Random.RNGMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import src.Dice;
import src.Roll;


public class RollTest {

    @BeforeClass
    public static void mockRNG() {
        RNG.setImpl(new RNGMock());
    }

    @Test
    public void diceRoll() {
        Dice d = new Dice(6);
        Assert.assertEquals(4, d.rollDice());
    }

    @Test
    public void singleD6Roll() {
        Roll rollTest = new Roll(6, 1, 0);
        Assert.assertEquals(4, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void singleD6RollWithAd() {
        Roll rollTest = new Roll(6, 1, 0);
        Assert.assertEquals(4, rollTest.makeRoll(Roll.RollType.ADVANTAGE));
    }

    @Test
    public void singleD6RollWithDisad() {
        Roll rollTest = new Roll(6, 1, 0);
        Assert.assertEquals(4, rollTest.makeRoll(Roll.RollType.DISADVANTAGE));
    }

    @Test
    public void singleD6RollWithModifierPositive() {
        Roll rollTest = new Roll(6, 1, 2);
        Assert.assertEquals(6, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void multipleD6RollWithModifierPositive() {
        Roll rollTest = new Roll(6, 2, 2);
        Assert.assertEquals(10, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void multipleD6RollWithModifierNegative() {
        Roll rollTest = new Roll(6, 2, -2);
        Assert.assertEquals(6, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void multipleD6RollWithModifierNegativeResultBelowZero() {
        Roll rollTest = new Roll(6, 2, -20);
        Assert.assertEquals(0, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void zeroAsNumberRoll() {
        Roll rollTest = new Roll(6, 0, 2);
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void zeroAsDiceValue() {
        Roll rollTest = new Roll(0, 4, 2);
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void negativeAsDiceValue() {
        Roll rollTest = new Roll(-50, 4, 2);
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void negativeNbRoll() {
        Roll rollTest = new Roll(2, -40, 2);
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void simpled6Formula() {
        Roll rollTest = new Roll("2d6");
        Assert.assertEquals(8, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void simpled6FormulaWithoutFirstDigit() {
        Roll rollTest = new Roll("d6");
        Assert.assertEquals(4, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void simpled6FormulaWithoutFirstDigitWithModifier() {
        Roll rollTest = new Roll("d6+1");
        Assert.assertEquals(5, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void simpled6FormulaWithPositiveModifier() {
        Roll rollTest = new Roll("2d6+1");
        Assert.assertEquals(9, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void simpled6FormulaWithNegativeModifier() {
        Roll rollTest = new Roll("2d6-1");
        Assert.assertEquals(7, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void invalidWrongSign() {
        Roll rollTest = new Roll("2d6/1");
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }
    @Test
    public void invalidNegativeDiceValue() {
        Roll rollTest = new Roll("2d-6");
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void invalidBadNumberRoll() {
        Roll rollTest = new Roll("hd6");
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void invalidGibberish() {
        Roll rollTest = new Roll("fhfxhfch64gxf4dg6d4g");
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }

    @Test
    public void toto() {
        Roll rollTest = new Roll("toto");
        Assert.assertEquals(-1, rollTest.makeRoll(Roll.RollType.NORMAL));
    }
}
