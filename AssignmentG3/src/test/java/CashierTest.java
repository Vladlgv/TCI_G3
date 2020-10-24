import casino.bet.MoneyAmount;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.cashier.InvalidAmountException;
import casino.idfactory.BetID;
import casino.idfactory.CardID;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static  org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
    public void test_RemoveAmountInCard_Works(){
        //Arrange
        CardID cardID=mock(CardID.class);
        //MoneyAmount cardAmount=mock(MoneyAmount.class);
        //when(cardAmount.getAmountInCents()).thenReturn(100l);
        MoneyAmount cardAmount= new MoneyAmount(100l);
        GamblerCard gamblerCard = new GamblerCard(cardID,cardAmount);

        //Act
        cashier.returnGamblerCard(gamblerCard);
        //Assert
        assertEquals(0l,gamblerCard.getAmount());
    }

    @Test
    public void test_AddMoneyToCard_Works() throws InvalidAmountException {
        //Arrange
        CardID cardID=mock(CardID.class);
        MoneyAmount money=mock(MoneyAmount.class);

        when(money.getAmountInCents()).thenReturn(100l);
        GamblerCard gamblerCard = new GamblerCard(cardID,money);
        //Act
        cashier.addAmount(gamblerCard,money);

        //Assert
        assertEquals(money.getAmountInCents()*2,gamblerCard.getAmount());
    }

    @Test
    public void test_ExtractMoney_Works() throws InvalidAmountException {
        //Arrange
        CardID cardID=mock(CardID.class);
        MoneyAmount money=mock(MoneyAmount.class);
        MoneyAmount money2=mock(MoneyAmount.class);
        when(money.getAmountInCents()).thenReturn(100l);
        when(money2.getAmountInCents()).thenReturn(-100l);
        GamblerCard gamblerCard = new GamblerCard(cardID,money);
        //Act
        cashier.addAmount(gamblerCard,money2);

        //Assert
        assertEquals(0l,gamblerCard.getAmount());
    }
}
