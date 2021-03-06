package casino.game;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.game.BettingRound;
import casino.game.IGame;
import casino.gamingmachine.GamingMachine;
import casino.idfactory.*;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.BetTokenAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class BettingroundTest {

    BettingRoundID bettingRoundID = mock(BettingRoundID.class);
    BetTokenAuthority betTokenAuthority = mock(BetTokenAuthority.class);
    IBetLoggingAuthority loggingAuthority= new BetLoggingAuthority();

    GamingMachine gamingMachine = new GamingMachine();
    IGame iGame = mock(IGame.class);

    BettingRound bettinground = new BettingRound(bettingRoundID, iGame, gamingMachine);

    /**
     * Test to add a new bet to current betting round
     */
    @Test
    public void test_AddBetToCurrentBettinground_ReturnsTrue(){
        //Arrange
        Bet bet = new Bet(new BetID(),new MoneyAmount(1l));
        //Act
        boolean result =bettinground.placeBet(bet);
        //Assert
        assertEquals(true,result);
    }

    /**
     * Test to see when added a new bet if the size of the list BetsMade increased
     */
    @Test
    public void test_addNewBet(){
        //Arrange
        Bet bet = mock(Bet.class);
        //Act
        bettinground.placeBet(bet);
        assertEquals(1,bettinground.getAllBetsMade().size());
    }

    /**
     * Test to see a null bet has been placed in betting round
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_addNullBet(){
        //Arrange
        Bet bet = null;
        //Act
        bettinground.placeBet(bet);
    }

    /**
     * Test to see bet cannot be added when a betting round is finished
     * @param bets
     */
    @Test
    @Parameters(method = "getBetWithValue")
    public void test_addBetWhenBettingroundWhenFinished(ArrayList<Bet> bets){
        //Arrange
        Bet bet = mock(Bet.class);
        when(iGame.isBettingRoundFinished()).thenReturn(true);
        //Act
        Boolean result = bettinground.placeBet(bets.get(0));

        //Assert
        assertEquals(false,result);

    }

    /*
    @Test
    public void test_LoggingAuthorityInvoked(){
        //Arrange
        Bet bet = mock(Bet.class);
        when(iGame.isBettingRoundFinished()).thenReturn(true);
        BetLoggingAuthority betLoggingAuthority = mock(BetLoggingAuthority.class);

        //Act
        bettinground.placeBet(bet);

        //Assert
        verify(betLoggingAuthority).logAddAcceptedBet(bet,bettingRoundID,gamingMachine.getGamingMachineID());

    }*/

    /**
     * Test to return all bets in current betting round
     * @param bets
     */
    @Test
    @Parameters(method = "getBetWithValue")
    public void test_ReturnAllBetsInCurrentBettinground_BetsReturned(ArrayList<Bet> bets){

        //Arrange
        //Bet bet = mock(Bet.class);
        when(iGame.isBettingRoundFinished()).thenReturn(false);

        //Act
        bettinground.placeBet(bets.get(0));
        bettinground.placeBet(bets.get(1));

        //Assert
        assertEquals(2,bettinground.getAllBetsMade().size());
    }

    /*@Test
    public void test_ReturnBetTokenForCurrentBettinground_BetTokenReturned(){
        bettinground.getBetToken();
    }*/

    /**
     * Test to get the amount of bets in current betting round
     * @param bets
     */
    @Test
    @Parameters(method = "getBetWithValue")
    public void test_ReturnAmountOfBetsInCurrentBettinground_AmountReturned(ArrayList<Bet> bets){
        //Arrange
        //Bet bet = mock(Bet.class);
        when(iGame.isBettingRoundFinished()).thenReturn(false);

        //Act
        bettinground.placeBet(bets.get(0));
        bettinground.placeBet(bets.get(1));
        //Assert
        assertEquals(2,bettinground.numberOFBetsMade());
    }

    /**
     * parameter list contains some bets
     * @return
     */
    private static final Object[] getBetWithValue(){
        ArrayList<Bet> bets = new ArrayList<>();

        BetID betID = new BetID();
        MoneyAmount moneyAmount = new MoneyAmount(1);
        Bet bet1 = new Bet(betID,moneyAmount);
        Bet bet2 = new Bet(betID,moneyAmount);
        bets.add(bet1);
        bets.add(bet2);

        return new Object[]{
                new Object[]{
                        bets
                }
        };
    }

    /**
     * Generate BetID test
     */
    @Test
    public void test_generateBetID() {
        //Arrange
        GeneralID betID;
        //Act
        betID = (BetID)IDFactory.generateID("BetID");
        Assertions.assertThat(betID.getUniqueID()).isInstanceOf(UUID.class);
        Assertions.assertThat(betID.getTimeStamp()).isNotNull();
        Assertions.assertThat(betID).isNotNull();
    }
}
