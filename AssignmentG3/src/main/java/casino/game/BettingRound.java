package casino.game;

import casino.idfactory.BettingRoundID;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.BetTokenAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import casino.bet.Bet;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class BettingRound implements IBettingRound {

    private BettingRoundID bettingRoundID;
    private BetToken betToken;
    private BetTokenAuthority betTokenAuthority;

    public BettingRound(BettingRoundID bettingRoundID,BetTokenAuthority betTokenAuthority){
        this.bettingRoundID = bettingRoundID;
        this.betTokenAuthority = betTokenAuthority;
        betToken = betTokenAuthority.getBetToken(bettingRoundID);
    }

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
