package casino.game;


import casino.bet.Bet;
import casino.gamingmachine.IGamingMachine;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.BettingAuthority;

public class DefaultGame extends AbstractGame {


    public DefaultGame(BetLoggingAuthority betLoggingAuthority) {
        super(betLoggingAuthority);
    }

    @Override
    public void startBettingRound() {

    }

    @Override
    public boolean acceptBet(Bet bet, IGamingMachine gamingMachine) throws NoCurrentRoundException {
        return false;
    }

    @Override
    public void determineWinner() {

    }

    @Override
    public boolean isBettingRoundFinished() {
        return true;
    }
}
