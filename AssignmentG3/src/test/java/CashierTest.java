import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class CashierTest {
    IBetLoggingAuthority loggingAuthority= new BetLoggingAuthority();
    Cashier cashier = new Cashier(loggingAuthority);

    @Test
    public void test_RemoveBetsInCard_Works(){
        //Arrange
        GamblerCard gamblerCard = mock(GamblerCard.class);

        cashier.returnGamblerCard(gamblerCard);
        //throw new RuntimeException();
    }
}
