package casino.game;

import org.fest.assertions.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

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
    ///
    @Test
    public void test_createNewBettingRoundWithPreviousBettingRound_BettingRoundIsCreated() {
        //arrange
        //act
        myGame.startBettingRound();
        var currentBettingRound= myGame.getCurrentBettingRound().getBettingRoundID();
        myGame.startBettingRound();
        //assert
        Assertions.assertThat(myGame.getCurrentBettingRound().getBettingRoundID()).isNotSameAs(currentBettingRound);
    }

    @Test
    public void acceptBet() {
    }

    @Test
    public void determineWinner() {
    }

    @Test
    public void isBettingRoundFinished() {
    }
}