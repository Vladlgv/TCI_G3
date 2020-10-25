package casino.gamingmachine;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.cashier.InvalidAmountException;
import casino.idfactory.BetID;
import casino.idfactory.GamingMachineID;
import casino.idfactory.IDFactory;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GamingMachineTest {
    GamingMachineID gamingMachineID=mock(GamingMachineID.class);
    GamingMachine gameMachine=new GamingMachine(gamingMachineID);
    Cashier cashier=mock(Cashier.class);
    GamblerCard gamblerCard=(GamblerCard)cashier.distributeGamblerCard();


    @Test
    public void placeBet() throws InvalidAmountException {
        MoneyAmount moneyAmount=mock(MoneyAmount.class);
        cashier.addAmount(gamblerCard,moneyAmount);
        long amountBet=moneyAmount.getAmountInCents();
        Bet bet=new Bet((BetID)IDFactory.generateID("BetID"),new MoneyAmount(amountBet));
        assertEquals(true,cashier.checkIfBetIsValid(gamblerCard,bet));
    }

    @Test
    public void acceptWinner() {
    }

    @Test
    public void getGamingMachineID() {
    }

    @Test
    public void connectCard() {
    }

    @Test
    public void disconnectCard() {
    }
}