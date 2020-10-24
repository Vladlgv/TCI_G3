package casino.game;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.IGamingMachine;
import casino.idfactory.BetID;
import casino.idfactory.BettingRoundID;
import casino.idfactory.GamingMachineID;
import casino.idfactory.IDFactory;
import org.fest.assertions.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DefaultGameTest {


    DefaultGame myGame = new DefaultGame();

    ///
    //Test that tries to start a new betting round after a betting round was not previously started.
    ///
    @Test
    public void test_createNewBettingRoundWithoutPreviousBettingRound_BettingRoundIsCreated() {
        //arrange
        DefaultGame myGame = new DefaultGame();
        //act
        myGame.startBettingRound();
        //assert
        Assertions.assertThat(myGame.getCurrentBettingRound()).isNotNull();
    }

    ///
    //Test that tries to start a new betting round after a betting round was previously started.
    //BettingRound Needs to be implemented in order to test this class
    ///
    @Ignore
    @Test
    public void test_createNewBettingRoundWithPreviousBettingRound_BettingRoundIsCreated() {
        //arrange
        String testUUID = "c81d4e2e-bcf2-11e6-869b-7df92533d2db";
        UUID expectedUUID = UUID.fromString(testUUID);
        BettingRoundID auxBettingRoundID = mock(BettingRoundID.class);
        when(auxBettingRoundID.getUniqueID()).thenReturn(UUID.fromString(testUUID));
        when(auxBettingRoundID.getTimeStamp()).thenReturn(null);
        //act
        myGame.startBettingRound();
        var currentBettingRound= myGame.getCurrentBettingRound().getBettingRoundID();
        myGame.startBettingRound();
        //assert
        Assertions.assertThat(myGame.getCurrentBettingRound().getBettingRoundID()).isNotSameAs(currentBettingRound);
    }
    /**
     * Accept a bet on the current betting round.
     * determine if the betting round is finished, if so: determine the winner,
     * notify the connected gaming machines and start a new betting round.
     *
     * Note: also use the appropiate required methods from the gambling authority API
     *
     * @param bet the bet to be made on the betting round
     * @param gamingMachine gamingmachine which places bet on this game.
     * @return true when bet is accepted by the game, otherwise false.
     * @throws NoCurrentRoundException when no BettingRound is currently active.
     */
    ///
    //Test to see if a bet that is supposed to be valid is indeed valid;
    ///
    @Test
    public void test_AcceptValidBet_BetIsSuccessful() throws NoCurrentRoundException {
        //arrange
        IGamingMachine mockGamingMachine= mock(GamingMachine.class);
        Bet mockBet = mock(Bet.class);
        MoneyAmount m = mock(MoneyAmount.class);
        long amountToReturn = 1000;
        DefaultGame spyGame = spy(DefaultGame.class);

        when(mockBet.getBetID()).thenReturn((BetID) IDFactory.generateID("BetID"));
        when(m.getAmountInCents()).thenReturn(amountToReturn);
        when(mockBet.getMoneyAmount()).thenReturn(m);

        when(mockGamingMachine.getGamingMachineID()).thenReturn((GamingMachineID)IDFactory.generateID("GamingMachineID"));


        //act
        boolean gameAccepted = myGame.acceptBet(mockBet,mockGamingMachine);
        //assert
        verify(myGame).acceptBet(mockBet,mockGamingMachine);
        Assertions.assertThat(gameAccepted).isTrue();

    }

    @Test
    public void determineWinner() {
    }

    @Test
    public void isBettingRoundFinished() {
    }
}