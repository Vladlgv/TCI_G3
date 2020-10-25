package casino.cashier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import casino.bet.MoneyAmount;
import casino.idfactory.BetID;
import casino.idfactory.CardID;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.IBetLoggingAuthority;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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


    //
    //Tests to see that the getNumberOfBetIDs does return the expected number of BetIDs
    //
    @Test
    @Parameters(method = "getRightValues")
    public void test_GetNumberOfBetIds_ReturnsRightNumber(int numberOfBetIds) {
        //arrange
        IBetLoggingAuthority betLoggingAuthority=mock(IBetLoggingAuthority.class);
        Cashier cashier= mock(Cashier.class);//new Cashier(betLoggingAuthority);
        when(cashier.distributeGamblerCard()).thenReturn(new GamblerCard((CardID)IDFactory.generateID("CardID"),new MoneyAmount(0)));
        IGamblerCard myCard = (GamblerCard)cashier.distributeGamblerCard();
        //act
        for(int i = 0 ; i< numberOfBetIds ; i++)
            myCard.generateNewBetID();
        int expectedResult = myCard.getNumberOfBetIDs();
        //assert
        Assertions.assertThat(expectedResult).isEqualTo(numberOfBetIds);
    }
    ///
    //Test the generateNewBetID method by generating multiple betIDs and making sure that the returned IDs are not duplicates
    ///
    @Test
    public void test_CreateUniqueBetID_SuccessfulyCreatesBetID() {
        //arrange
        int numberOfBetIds3 = 3;
        IBetLoggingAuthority betLoggingAuthority=mock(IBetLoggingAuthority.class);
        Cashier cashier= mock(Cashier.class);//new Cashier(betLoggingAuthority);
        when(cashier.distributeGamblerCard()).thenReturn(new GamblerCard((CardID)IDFactory.generateID("CardID"),new MoneyAmount(0)));
        IGamblerCard myCard = (GamblerCard)cashier.distributeGamblerCard();
        //act
        for(int i = 0 ; i< numberOfBetIds3 ; i++)
            myCard.generateNewBetID();
        Set<BetID> result = myCard.returnBetIDs();

        //assert
        Assertions.assertThat(result).doesNotHaveDuplicates();

    }

    ///
    // Test to see if The return betId does indeed return a non empty non null with no duplicates and containing only BetIDs collection
    ///
    @Test
    public void test_ReturnBetIdsNotEmpty_SuccessfullyReturnsListOfBetIds() {
        //arrange
        IBetLoggingAuthority betLoggingAuthority=mock(IBetLoggingAuthority.class);
        Cashier cashier= mock(Cashier.class);//new Cashier(betLoggingAuthority);
        when(cashier.distributeGamblerCard()).thenReturn(new GamblerCard((CardID)IDFactory.generateID("CardID"),new MoneyAmount(0)));
        IGamblerCard myCard = (GamblerCard)cashier.distributeGamblerCard();
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

    ///
    // Test to see if The return betId does indeed return an empty collection that is not null
    ///
    @Test
    public void test_ReturnBetIdsEmpty_SuccessfullyReturnsListOfBetIds() {
        //arrange
        IBetLoggingAuthority betLoggingAuthority=mock(IBetLoggingAuthority.class);
        Cashier cashier= mock(Cashier.class);//new Cashier(betLoggingAuthority);
        when(cashier.distributeGamblerCard()).thenReturn(new GamblerCard((CardID)IDFactory.generateID("CardID"),new MoneyAmount(0)));
        IGamblerCard myCard = (GamblerCard)cashier.distributeGamblerCard();
        //act
        Set<BetID> results = myCard.returnBetIDs();
        //assert
        Assertions.assertThat(results).isEmpty();
        Assertions.assertThat(results).isNotNull();
    }

    ///
    // make sure that the CardID is not null
    ///
    @Test
    public void test_GetCardId_isNotNull() {
        //arrange
        IBetLoggingAuthority betLoggingAuthority=mock(IBetLoggingAuthority.class);
        Cashier cashier= mock(Cashier.class);//new Cashier(betLoggingAuthority);
        when(cashier.distributeGamblerCard()).thenReturn(new GamblerCard((CardID)IDFactory.generateID("CardID"),new MoneyAmount(0)));
        IGamblerCard myCard = (GamblerCard)cashier.distributeGamblerCard();
        //act
        CardID result = myCard.getCardID();
        //assert
        Assertions.assertThat(result).isNotNull();
    }

    ///
    // Test to see that the returnBetIDsAndClear does indeed return the correct collection and it clears it afterwards.
    ///
    @Test
    public void test_returnAllBetsClear_CorectLIstIsReturenedCardsAreDeleted() {
        //arrange
        Integer numberOfBets = 5;
        IBetLoggingAuthority betLoggingAuthority=mock(IBetLoggingAuthority.class);
        Cashier cashier= mock(Cashier.class);//new Cashier(betLoggingAuthority);
        when(cashier.distributeGamblerCard()).thenReturn(new GamblerCard((CardID)IDFactory.generateID("CardID"),new MoneyAmount(0)));
        IGamblerCard myCard = (GamblerCard)cashier.distributeGamblerCard();
        //act

        for(int i = 0 ; i < numberOfBets; i++)
            myCard.generateNewBetID();

        Set<BetID> betIds =  myCard.returnBetIDs();
        Set<BetID> result_clear = new HashSet<>();
        result_clear.addAll(myCard.returnBetIDsAndClearCard());
        Set<BetID> betIDsCleared = myCard.returnBetIDs();
        //assert

        Assertions.assertThat(result_clear.size()).isEqualTo(numberOfBets);
        Assertions.assertThat(betIDsCleared).isEmpty();


    }

}