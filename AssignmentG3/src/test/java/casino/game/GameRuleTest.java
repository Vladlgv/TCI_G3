package casino.game;

import casino.bet.Bet;
import casino.bet.BetResult;
import org.junit.Test;


import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GameRuleTest {
    int maxNumberOfBets=6;
    GameRule gameRule = new GameRule(maxNumberOfBets);

    /**
     * Test to see if the NoBetsMadeException is thrown when the set of made bets is null or empty;
     */
    @Test(expected = NoBetsMadeException.class)
    public void betsSetIsNullOrEmpty() throws NoBetsMadeException {
        Set<Bet> bets=null;
        //test when set is null
        gameRule.determineWinner(1,bets);
        //test when set is empty
        bets=new HashSet<Bet>();
        gameRule.determineWinner(1,bets);
    }

    /**
     * Test to see if a null betResult is returned when the randomWinValue is not in the range of the set;
     */
    @Test
    public void randomWinValueNotInRangeOfSet() throws NoBetsMadeException {
        Set<Bet> bets=new HashSet<Bet>();
        bets.add(mock(Bet.class,"Bet1"));
        bets.add(mock(Bet.class,"Bet2"));
        bets.add(mock(Bet.class,"Bet3"));
        bets.add(mock(Bet.class,"Bet4"));
        //test bets size
        assertEquals(4,bets.size());
        //test the return BetResult: null is expected bcs range is 1-4
        assertEquals(null,gameRule.determineWinner(5,bets));
        assertEquals(null,gameRule.determineWinner(0,bets));
    }

    /**
     * Test to see if a betResult is returned when the randomWinValue is in the range of the set;
     */
    @Test
    public void randomWinValueInRangeOfSet() throws NoBetsMadeException {
        Set<Bet> bets=new HashSet<Bet>();
        Bet bet1=mock(Bet.class,"Bet1");
        Bet bet2=mock(Bet.class,"Bet2");
        Bet bet3=mock(Bet.class,"Bet3");
        Bet bet4=mock(Bet.class,"Bet4");
        //
        bets.add(bet1);
        bets.add(bet2);
        bets.add(bet3);
        bets.add(bet4);
        //test bets size
        assertEquals(4,bets.size());
        //test the return BetResult: A BetResult object is expected bcs range is 1-4
        assertNotEquals(null,gameRule.determineWinner(4,bets));
    }

    /**
     * Test to see if a correct number of the maxNumberOfBetsCanMade is returned;
     */
    @Test
    public void returnMaxNumberOfBetsCanMade(){
        assertEquals(maxNumberOfBets,gameRule.getMaxBetsPerRound());
    }
}