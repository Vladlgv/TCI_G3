import casino.bet.MoneyAmount;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
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

        //Act
        cashier.returnGamblerCard(gamblerCard);

    }
}
