package casino.game;


import casino.bet.Bet;
import casino.gamingmachine.IGamingMachine;
import gamblingauthoritiy.BettingAuthority;

public class DefaultGame extends AbstractGame {

    private IBettingRound currentBettingRound;
    private static BettingAuthority bettingAuthority;

    public IBettingRound getCurrentBettingRound() {
        return currentBettingRound;
    }

    public DefaultGame() {
        this.bettingAuthority = new BettingAuthority();
    }

    @Override
    public void startBettingRound() {
    if(currentBettingRound == null) {
        currentBettingRound = new BettingRound();
        bettingAuthority.getLoggingAuthority().logStartBettingRound(this.getCurrentBettingRound());;
    }
    else
    {
        bettingAuthority.getLoggingAuthority().logStartBettingRound(this.getCurrentBettingRound());
    }

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
        return false;
    }
}
