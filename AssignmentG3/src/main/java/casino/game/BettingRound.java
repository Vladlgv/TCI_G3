package casino.game;

import casino.idfactory.BettingRoundID;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.IBetLoggingAuthority;
import casino.bet.Bet;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class BettingRound implements IBettingRound {
    @Override
    public BettingRoundID getBettingRoundID() {
        return null;
    }

    @Override
    public boolean placeBet(Bet bet) throws IllegalArgumentException {
        return false;
    }

    @Override
    public Set<Bet> getAllBetsMade() {
        return null;
    }

    @Override
    public BetToken getBetToken() {
        return null;
    }

    @Override
    public int numberOFBetsMade() {
        return 0;
    }
}
