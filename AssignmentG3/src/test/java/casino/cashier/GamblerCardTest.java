package casino.cashier;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GamblerCardTest {


    private static final Object[] getRightValues() {
        Integer i1 = 5;
        Integer i2 = 10;
        Integer i3 = 0;
        Integer i4 = 1;

        return new Object[]{
                new Object[]{i1},
                new Object[]{i2},
                new Object[]{i3},
                new Object[]{i4}
        };
    }

    @Test
    @Parameters(method = "getRightValues")
    public void test_GetNumberOfBetIds_ReturnsRightNumber(int numberOfBetIds) {
        //arrange
        IGamblerCard myCard = new GamblerCard();
        //act
        for(int i = 0 ; i< numberOfBetIds ; i++)
        myCard.generateNewBetID();
        int expectedResult = myCard.getNumberOfBetIDs();
        //assert
        Assertions.assertThat(expectedResult).isEqualTo(numberOfBetIds);
    }

    @Test
    public void test_CreateUniqueBetID_SuccessfulyCreatesBetID() {
        //arrange
        IGamblerCard myCard = new GamblerCard();
        //act
        myCard.generateNewBetID();
        //assert
    }
}