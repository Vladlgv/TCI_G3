import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.cashier.*;
import casino.idfactory.BetID;
import casino.idfactory.CardID;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import org.junit.Assert;
import org.junit.Test;

import static  org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    //todo invalid Amount to add and extract

    @Test
    public void  test_CardDistributedToGambler_CreateCardSuccessfully(){
        //Arrange
        CardID cardID=mock(CardID.class);
        MoneyAmount cardAmount=mock(MoneyAmount.class);

        GamblerCard gamblerCard1 = new GamblerCard(cardID,cardAmount);
        //act
        IGamblerCard gamblerCard2 = cashier.distributeGamblerCard();

        //Assert
        //verify()
    }

    @Test
    public void test_CheckBetValid_ReturnsTrue() throws BetNotExceptedException {
        //Arrange
        GamblerCard gamblerCard = mock(GamblerCard.class);
        Bet bet = mock(Bet.class);

        when(gamblerCard.getAmount()).thenReturn(100l);
        when(bet.getMoneyAmount()).thenReturn(new MoneyAmount(50l));
        //Act
        Boolean result = cashier.checkIfBetIsValid(gamblerCard,bet);

        //Assert
        assertEquals(true,result);
    }
}
