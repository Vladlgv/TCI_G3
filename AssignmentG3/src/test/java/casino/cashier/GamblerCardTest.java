package casino.cashier;

import casino.idfactory.BetID;
import casino.idfactory.CardID;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    IGamblerCard myCard = new GamblerCard();

    @Test
    @Parameters(method = "getRightValues")
    public void test_GetNumberOfBetIds_ReturnsRightNumber(int numberOfBetIds) {
        //arrange
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
        BetID mockBetId = mock(BetID.class);
        int numberOfBetIds3 = 3;
        when(mockBetId.getUniqueID()).thenReturn(UUID.randomUUID());
        //act
        for(int i = 0 ; i< numberOfBetIds3 ; i++)
        myCard.generateNewBetID();
        Set<BetID> result = myCard.returnBetIDs();

        //assert
        for (BetID b:result) {
            Assertions.assertThat(mockBetId).isNotSameAs(b);
            mockBetId = b;
        }

    }

    @Test
    public void test_ReturnBetIds_SuccessfullyReturnsListOfBetIds() {
        //arrange
        //act
        myCard.generateNewBetID();
        myCard.generateNewBetID();
        myCard.generateNewBetID();
        Set<BetID> results = myCard.returnBetIDs();
        //assert
        Assertions.assertThat(results).doesNotHaveDuplicates().isNotEmpty().isNotNull();
        for(BetID result: results)
        {
            Assertions.assertThat(result).isInstanceOf(BetID.class);
        }
    }

    @Test
    public void test_GetCardId_isNotNull() {
        //arrange
        //act
        CardID result = myCard.getCardID();
        //assert
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void test_returnAllBetsClear_CorectLIstIsReturenedCardsAreDeleted() {
        //arrange
        Integer numberOfBets = 5;
        //act

        for(int i = 0 ; i < numberOfBets; i++)
        myCard.generateNewBetID();

        Set<BetID> betIds =  myCard.returnBetIDs();
        Set<BetID> result_clear = new HashSet<>();
        result_clear.addAll(myCard.returnBetIDsAndClearCard());
        Set<BetID> betIDsCleared = myCard.returnBetIDs();
        //assert

        Assertions.assertThat(result_clear).isEqualTo(betIds);
        //Assertions.assertThat(betIDsCleared).isEmpty();

    }
}