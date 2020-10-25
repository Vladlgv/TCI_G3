package casino.game;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.IGamingMachine;
import casino.idfactory.BetID;
import casino.idfactory.BettingRoundID;
import casino.idfactory.GamingMachineID;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.BettingAuthority;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.fest.assertions.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class DefaultGameTest {
    private static final Object[] getDummyBets() {
        BetID b1ID = mock(BetID.class);
        when(b1ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b2ID = mock(BetID.class);
        when(b2ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b3ID = mock(BetID.class);
        when(b3ID.getUniqueID()).thenReturn(UUID.randomUUID());

        MoneyAmount mockAmount = mock(MoneyAmount.class);
        long amountToReturn = 1000;
        when(mockAmount.getAmountInCents()).thenReturn(amountToReturn);

        Bet b1 = mock(Bet.class);
        when(b1.getMoneyAmount()).thenReturn(mockAmount);
        when(b1.getBetID()).thenReturn(b1ID);

        Bet b2 = mock(Bet.class);
        when(b2.getMoneyAmount()).thenReturn(mockAmount);
        when(b2.getBetID()).thenReturn(b2ID);

        Bet b3 = mock(Bet.class);
        when(b3.getMoneyAmount()).thenReturn(mockAmount);
        when(b3.getBetID()).thenReturn(b3ID);



        return new Object[]{
                new Object[]{b1,b2,b3},

        };
    }

    BettingRound bettingRound = mock(BettingRound.class);

    DefaultGame myGame = new DefaultGame(bettingRound);

    ///
    //Test that tries to start a new betting round after a betting round was not previously started.
    ///
    @Test
    public void test_createNewBettingRoundWithoutPreviousBettingRound_BettingRoundIsCreated() throws NoBetsMadeException {
        //arrange
        DefaultGame myGameNull = new DefaultGame(null);
        //act
        myGameNull.startBettingRound();
        //assert
        Assertions.assertThat(myGameNull.getCurrentBettingRound()).isNotNull();
    }

    ///
    //Test that tries to start a new betting round after a betting round was previously started.
    //BettingRound Needs to be implemented in order to test this class
    ///
    @Parameters(method = "getDummyBets")
    @Test
    public void test_createNewBettingRoundWithPreviousBettingRound_BettingRoundIsCreated(Bet b1, Bet b2, Bet b3) throws NoBetsMadeException {
        //arrange
        String testUUID = "c81d4e2e-bcf2-11e6-869b-7df92533d2db";
        UUID expectedUUID = UUID.fromString(testUUID);
        BettingRoundID auxBettingRoundID = mock(BettingRoundID.class);
        when(auxBettingRoundID.getUniqueID()).thenReturn(UUID.fromString(testUUID));
        when(auxBettingRoundID.getTimeStamp()).thenReturn(null);
        when(bettingRound.getBettingRoundID()).thenReturn(auxBettingRoundID);
        when(bettingRound.getAllBetsMade()).thenReturn(new HashSet<>(){{add(b1);add(b2);add(b3);}});
        //act
        var currentBettingRound= myGame.getCurrentBettingRound().getBettingRoundID();
        myGame.startBettingRound();
        myGame.startBettingRound();
        //assert
        verify(bettingRound,times(2)).getBettingRoundID();
        verify(bettingRound).getAllBetsMade();
        Assertions.assertThat(myGame.getCurrentBettingRound().getBettingRoundID()).isNotSameAs(currentBettingRound);
    }

    ///
    //Test to see if a bet that is supposed to be valid is indeed valid;
    ///
    @Test
    public void test_AcceptValidBet_BetIsSuccessful() throws NoCurrentRoundException {
        //arrange
        IGamingMachine mockGamingMachine= mock(GamingMachine.class);
        Bet mockBet = mock(Bet.class);
        MoneyAmount m = mock(MoneyAmount.class);
        when(bettingRound.placeBet(mockBet)).thenReturn(true);
        long amountToReturn = 1000;
        //DefaultGame spyGame = spy(DefaultGame.class);

        when(mockBet.getBetID()).thenReturn((BetID) IDFactory.generateID("BetID"));
        when(m.getAmountInCents()).thenReturn(amountToReturn);
        when(mockBet.getMoneyAmount()).thenReturn(m);
        when(mockGamingMachine.getGamingMachineID()).thenReturn((GamingMachineID)IDFactory.generateID("GamingMachineID"));


        //act
        boolean gameAccepted = myGame.acceptBet(mockBet,mockGamingMachine);
        //assert
        verify(bettingRound).placeBet(mockBet);
        Assertions.assertThat(gameAccepted).isTrue();

    }

    ///
    //Test to see if a bet that is supposed to not be valid is indeed not valid;
    ///
    @Test
    public void test_InvalidBet_BetIsUnSuccessful() throws NoCurrentRoundException {
        //arrange
        IGamingMachine mockGamingMachine= mock(GamingMachine.class);
        Bet mockBet = mock(Bet.class);
        MoneyAmount m = mock(MoneyAmount.class);
        when(bettingRound.placeBet(mockBet)).thenReturn(false);
        long amountToReturn = 0;
        //DefaultGame spyGame = spy(DefaultGame.class);

        when(mockBet.getBetID()).thenReturn(null);
        when(m.getAmountInCents()).thenReturn(amountToReturn);
        when(mockBet.getMoneyAmount()).thenReturn(m);
        when(mockGamingMachine.getGamingMachineID()).thenReturn((GamingMachineID)IDFactory.generateID("GamingMachineID"));


        //act
        boolean gameAccepted = myGame.acceptBet(mockBet,mockGamingMachine);
        //assert
        verify(bettingRound).placeBet(mockBet);
        Assertions.assertThat(gameAccepted).isFalse();

    }


    ///
    //throws an NoCurrentRoundException
    ///
    @Test//(expected = NoCurrentRoundException.class)
    public void test_AcceptBetWithNoCurrentRound_ThrowNoCurrentRoundException() throws NoCurrentRoundException {
        //arrange
        DefaultGame differentDefaultGame = new DefaultGame(null);
        IGamingMachine mockGamingMachine= mock(GamingMachine.class);
        Bet mockBet = mock(Bet.class);
        MoneyAmount m = mock(MoneyAmount.class);
        long amountToReturn = 0;
        //DefaultGame spyGame = spy(DefaultGame.class);

        when(mockBet.getBetID()).thenReturn(null);
        when(m.getAmountInCents()).thenReturn(amountToReturn);
        when(mockBet.getMoneyAmount()).thenReturn(m);
        when(mockGamingMachine.getGamingMachineID()).thenReturn((GamingMachineID)IDFactory.generateID("GamingMachineID"));


        //act
        try {
            boolean gameAccepted = differentDefaultGame.acceptBet(mockBet, mockGamingMachine);
        }catch (Exception e) {
            //assert
            Assertions.assertThat(e).isInstanceOf(NoCurrentRoundException.class);
        }

    }
    @Parameters(method = "getDummyBets")
    @Test
    public void test_DetermineWinnerWithBets_CurrentBettingRoundIsEnded(Bet b1, Bet b2, Bet b3) throws NoBetsMadeException, NoCurrentRoundException {
        //arrange
        //act
        bettingRound.placeBet(b1);
        bettingRound.placeBet(b2);
        bettingRound.placeBet(b3);

        myGame.determineWinner();
        //assert
        //verify(b1).getBetID();
        Assertions.assertThat(myGame.getCurrentBettingRound()).isNull();
    }

    private static final Object[] reachMaxBet() {
        BetID b1ID = mock(BetID.class);
        when(b1ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b2ID = mock(BetID.class);
        when(b2ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b3ID = mock(BetID.class);
        when(b3ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b4ID = mock(BetID.class);
        when(b4ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b5ID = mock(BetID.class);
        when(b5ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b6ID = mock(BetID.class);
        when(b6ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b7ID = mock(BetID.class);
        when(b7ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b8ID = mock(BetID.class);
        when(b8ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b9ID = mock(BetID.class);
        when(b9ID.getUniqueID()).thenReturn(UUID.randomUUID());
        BetID b10ID = mock(BetID.class);
        when(b10ID.getUniqueID()).thenReturn(UUID.randomUUID());

        MoneyAmount mockAmount = mock(MoneyAmount.class);
        long amountToReturn = 1000;
        when(mockAmount.getAmountInCents()).thenReturn(amountToReturn);

        Bet b1 = mock(Bet.class);
        when(b1.getMoneyAmount()).thenReturn(mockAmount);
        when(b1.getBetID()).thenReturn(b1ID);

        Bet b2 = mock(Bet.class);
        when(b2.getMoneyAmount()).thenReturn(mockAmount);
        when(b2.getBetID()).thenReturn(b2ID);

        Bet b3 = mock(Bet.class);
        when(b3.getMoneyAmount()).thenReturn(mockAmount);
        when(b3.getBetID()).thenReturn(b3ID);

        Bet b4 = mock(Bet.class);
        when(b4.getMoneyAmount()).thenReturn(mockAmount);
        when(b4.getBetID()).thenReturn(b1ID);

        Bet b5 = mock(Bet.class);
        when(b5.getMoneyAmount()).thenReturn(mockAmount);
        when(b5.getBetID()).thenReturn(b2ID);

        Bet b6 = mock(Bet.class);
        when(b6.getMoneyAmount()).thenReturn(mockAmount);
        when(b6.getBetID()).thenReturn(b3ID);

        Bet b7 = mock(Bet.class);
        when(b7.getMoneyAmount()).thenReturn(mockAmount);
        when(b7.getBetID()).thenReturn(b1ID);

        Bet b8 = mock(Bet.class);
        when(b8.getMoneyAmount()).thenReturn(mockAmount);
        when(b8.getBetID()).thenReturn(b2ID);

        Bet b9 = mock(Bet.class);
        when(b9.getMoneyAmount()).thenReturn(mockAmount);
        when(b9.getBetID()).thenReturn(b3ID);

        Bet b10 = mock(Bet.class);
        when(b10.getMoneyAmount()).thenReturn(mockAmount);
        when(b10.getBetID()).thenReturn(b3ID);


        Set<Bet> myBets = new LinkedHashSet<>();
        myBets.add(b1);
        myBets.add(b2);
        myBets.add(b3);
        myBets.add(b4);
        myBets.add(b5);
        myBets.add(b6);
        myBets.add(b7);
        myBets.add(b8);
        myBets.add(b9);
        myBets.add(b10);


        return new Object[]{
                new Object[]{myBets},

        };
    }
    @Ignore
    @Parameters(method = "reachMaxBet")
    @Test
    public void test_DetectCorrectNumberOfTestsAreReached_SuccessfullyDetected(Set<Bet> myBets) throws NoBetsMadeException {
        //arrange
        when(bettingRound.getAllBetsMade()).thenReturn(myBets);
        //act
        boolean result = myGame.isBettingRoundFinished();
        //assert
        verify(bettingRound).getAllBetsMade();
        // Assertions.assertThat(result).isTrue();


    }
}