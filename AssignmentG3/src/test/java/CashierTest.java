import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.cashier.*;
import casino.idfactory.BetID;
import casino.idfactory.CardID;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static  org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class CashierTest {
    IBetLoggingAuthority loggingAuthority= new BetLoggingAuthority();
    Cashier cashier = new Cashier(loggingAuthority);

    @Test
    public void test_RemoveBetsInCard_Works(){
        //Arrange
        CardID cardID=mock(CardID.class);
        MoneyAmount cardAmount=mock(MoneyAmount.class);

        GamblerCard gamblerCard = new GamblerCard(cardID,cardAmount);
        gamblerCard.generateNewBetID();
        gamblerCard.generateNewBetID();

        //Act
        cashier.returnGamblerCard(gamblerCard);
        //Assert
        assertEquals(0,gamblerCard.getNumberOfBetIDs());

    }

    @Test
    @Parameters(method = "getMoneys")
    public void test_RemoveAmountInCard_Works(ArrayList<MoneyAmount> moneyAmounts){
        //Arrange
        CardID cardID=mock(CardID.class);
        //MoneyAmount cardAmount=mock(MoneyAmount.class);
        //when(cardAmount.getAmountInCents()).thenReturn(100l);

        //MoneyAmount cardAmount= new MoneyAmount(100l);

        GamblerCard gamblerCard = new GamblerCard(cardID,moneyAmounts.get(0));

        //Act
        cashier.returnGamblerCard(gamblerCard);
        //Assert
        //TODO: Diqin decide on alternative
       // assertEquals(0l,gamblerCard.getAmount());
    }

    @Test
    @Parameters(method = "getMoneys")
    public void test_AddMoneyToCard_Works(ArrayList<MoneyAmount> moneyAmounts) throws InvalidAmountException {
        //Arrange
        CardID cardID=mock(CardID.class);

        /*MoneyAmount money=mock(MoneyAmount.class);
        when(money.getAmountInCents()).thenReturn(100l);*/

        GamblerCard gamblerCard = new GamblerCard(cardID,moneyAmounts.get(0));
        //Act
        cashier.addAmount(gamblerCard,moneyAmounts.get(1));

        //Assert
        //TODO: Diqin Find an alternative to those classes that were added to the interface
        //assertEquals(moneyAmounts.get(0).getAmountInCents()+moneyAmounts.get(1).getAmountInCents(),gamblerCard.getAmount());
    }

    @Test(expected = InvalidAmountException.class)
    @Parameters(method = "getMoneys")
    public void test_AddInvalidMoneyToCard_ReturnsException(ArrayList<MoneyAmount> moneyAmounts) throws InvalidAmountException {
        //Arrange
        CardID cardID=mock(CardID.class);
        //
        /*MoneyAmount money=mock(MoneyAmount.class);
        MoneyAmount money2=mock(MoneyAmount.class);
        when(money.getAmountInCents()).thenReturn(100l);
        when(money2.getAmountInCents()).thenReturn(-100l);*/
        //
        GamblerCard gamblerCard = new GamblerCard(cardID,moneyAmounts.get(0));
        //Act
        cashier.addAmount(gamblerCard,moneyAmounts.get(2));

        //Assert
        //TODO: Diqin decide how to solve this
        //assertEquals(0l,gamblerCard.getAmount());
    }


    //@Test
    /*public void  test_CardDistributedToGambler_CreateCardSuccessfully(){
        //Arrange
        CardID cardID = mock(CardID.class);
        BetLoggingAuthority betLoggingAuthority = mock(BetLoggingAuthority.class);

        cashier.distributeGamblerCard();

        verify(betLoggingAuthority).logHandOutGamblingCard(cardID);

        //Assert
    }*/

    @Test
    public void test_CheckBetValid_ReturnsTrue() throws BetNotExceptedException {
        //Arrange
        GamblerCard gamblerCard = mock(GamblerCard.class);
        Bet bet = mock(Bet.class);
        //TODO: add something here
      //  when(gamblerCard.getAmount()).thenReturn(100l);
        when(bet.getMoneyAmount()).thenReturn(new MoneyAmount(50l));
        //Act
        Boolean result = cashier.checkIfBetIsValid(gamblerCard,bet);

        //Assert
        assertEquals(true,result);
    }

    @Test(expected = BetNotExceptedException.class)
    public void test_CheckBetValid_ReturnsException() throws BetNotExceptedException {
        //Arrange
        GamblerCard gamblerCard = mock(GamblerCard.class);
        Bet bet = mock(Bet.class);
        //TODO: changed
        //when(gamblerCard.getAmount()).thenReturn(50l);
        when(bet.getMoneyAmount()).thenReturn(new MoneyAmount(100l));
        //Act
        cashier.checkIfBetIsValid(gamblerCard,bet);

    }

    //
    public static final Object[] getMoneys(){
        ArrayList<MoneyAmount> moneyAmounts = new ArrayList<>();

        MoneyAmount moneyAmount1 = new MoneyAmount(100);
        MoneyAmount moneyAmount2 = new MoneyAmount(50);
        MoneyAmount moneyAmount3 = new MoneyAmount(-20);

        moneyAmounts.add(moneyAmount1);
        moneyAmounts.add(moneyAmount2);
        moneyAmounts.add(moneyAmount3);

        return new Object[]{
                new Object[]{
                        moneyAmounts
                }
        };
    }

}
