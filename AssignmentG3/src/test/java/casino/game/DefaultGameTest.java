package casino.game;

import casino.idfactory.BettingRoundID;
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

    @Test
    public void test_AcceptValidBet_BetIsSuccessful() {
    }

    @Test
    public void determineWinner() {
    }

    @Test
    public void isBettingRoundFinished() {
    }
}