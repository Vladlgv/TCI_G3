import casino.bet.MoneyAmount;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.idfactory.CardID;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static  org.junit.Assert.*;

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

        assertEquals(0,gamblerCard.getNumberOfBetIDs());

        //remove money //remove betids

        //throw new RuntimeException();
    }
}
