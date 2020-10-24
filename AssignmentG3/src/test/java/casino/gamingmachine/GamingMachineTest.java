package casino.gamingmachine;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.cashier.InvalidAmountException;
import casino.idfactory.BetID;
import casino.idfactory.GamingMachineID;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.IBetLoggingAuthority;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GamingMachineTest {
    IBetLoggingAuthority betLoggingAuthority=mock(IBetLoggingAuthority.class);
    Cashier cashier=new Cashier(betLoggingAuthority);//use real Cashier to distribute cards, not mock Cashier
    GamingMachineID gamingMachineID=mock(GamingMachineID.class);
    GamingMachine gameMachine=new GamingMachine(gamingMachineID,cashier);
    GamblerCard gamblerCard=(GamblerCard)cashier.distributeGamblerCard();

    @Test(expected = BetNotExceptedException.class)
    public void placeBetWithInvalidAmount() throws NoPlayerCardException, BetNotExceptedException {
        gameMachine.connectCard(gamblerCard);
        gameMachine.placeBet(-99);
    }

    @Test
    public void clearAllBetWhenReceiveWinner() throws NoPlayerCardException, BetNotExceptedException, InvalidAmountException {
        MoneyAmount moneyAmount=mock(MoneyAmount.class);
        cashier.addAmount(gamblerCard,new MoneyAmount(150));
        gameMachine.connectCard(gamblerCard);
        //place 4 bets
        gameMachine.placeBet(10);
        gameMachine.placeBet(20);
        gameMachine.placeBet(25);
        gameMachine.placeBet(30);
        //test the numbers of Bet have made
        assertEquals(4,gameMachine.getNumberOfBets());
        //
        BetResult winningBet=mock(BetResult.class);
        gameMachine.acceptWinner(winningBet);
        assertEquals(0,gameMachine.getNumberOfBets());
    }

    @Test
    public void getGamingMachineID() {
        assertEquals(gamingMachineID,gameMachine.getGamingMachineID());
    }

    @Test
    public void CheckIfCardIsConnected() {
        gameMachine.connectCard(gamblerCard);
        assertEquals(gamblerCard,gameMachine.getConnectedCard());
    }

    @Test
    public void CheckIfCardIsDisconnected() throws CurrentBetMadeException {
        gameMachine.connectCard(gamblerCard);
        gameMachine.disconnectCard();
        assertEquals(null,gameMachine.getConnectedCard());
    }

    @Test(expected = NoPlayerCardException.class)
    public void NoConnectedCardToPlaceBet() throws NoPlayerCardException, BetNotExceptedException {
        gameMachine.placeBet(10);
    }
}