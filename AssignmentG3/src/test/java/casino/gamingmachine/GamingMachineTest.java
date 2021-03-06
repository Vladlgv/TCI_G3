package casino.gamingmachine;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.cashier.InvalidAmountException;
import casino.game.IGame;
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
    IGame iGame=mock(IGame.class);
    GamingMachine gameMachine=new GamingMachine(gamingMachineID,cashier,iGame);
    GamblerCard gamblerCard=(GamblerCard)cashier.distributeGamblerCard();

    /**
     * Test to see if the BetNotExceptedException is thrown when placeBet with an invalid amount;
     */
    @Test(expected = BetNotExceptedException.class)
    public void placeBetWithInvalidAmount() throws NoPlayerCardException, BetNotExceptedException {
        gameMachine.connectCard(gamblerCard);
        gameMachine.placeBet(-99);
    }

    /**
     * Test to see if the set of the made bets is clear when the gameMachine receives the betResult;
     */
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

    /**
     * Test to see if the gamingMachineID is returned correctly;
     */
    @Test
    public void getGamingMachineID() {
        assertEquals(gamingMachineID,gameMachine.getGamingMachineID());
    }

    /**
     * Test to see if the gamblerCard is connected to the gamingMachine;
     */
    @Test
    public void CheckIfCardIsConnected() {
        gameMachine.connectCard(gamblerCard);
        assertEquals(gamblerCard,gameMachine.getConnectedCard());
    }

    /**
     * Test to see if the gamblerCard is no longer connected to the gamingMachine;
     */
    @Test
    public void checkIfCardIsDisconnected() throws CurrentBetMadeException {
        gameMachine.connectCard(gamblerCard);
        when(iGame.isBettingRoundFinished()).thenReturn(true);
        gameMachine.disconnectCard();
        assertEquals(null,gameMachine.getConnectedCard());
    }

    /**
     * Test to see if the NoPlayerCardException is thrown when trying to place a bet with the zero-connected-gamblerCard gamingMachine;
     */
    @Test(expected = NoPlayerCardException.class)
    public void noConnectedCardToPlaceBet() throws NoPlayerCardException, BetNotExceptedException {
        gameMachine.placeBet(10);
    }

    /**
     * Test to see if the CurrentBetMadeException is thrown when trying to disconnect gamblerCard from the gamingMachine while the bettingRound is not finished yet;
     */
    @Test(expected = CurrentBetMadeException.class)
    public void checkIfCardIsConnectedWhenOpenBet() throws CurrentBetMadeException {
        when(iGame.isBettingRoundFinished()).thenReturn(false);
        gameMachine.disconnectCard();
    }
}