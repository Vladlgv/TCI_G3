package casino.game;


import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.IGamingMachine;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.BettingAuthority;

import java.util.HashSet;
import java.util.Set;

public class DefaultGame extends AbstractGame {

    private IBettingRound currentBettingRound;
    private static BettingAuthority bettingAuthority;

    public Set<GamingMachine> getConnectedGamingMachines() {
        return connectedGamingMachines;
    }

    private Set<GamingMachine> connectedGamingMachines;

    public IBettingRound getCurrentBettingRound() {
        return currentBettingRound;
    }

    public DefaultGame() {
        this.bettingAuthority = new BettingAuthority();
        this.connectedGamingMachines = new HashSet<>();
    }

    @Override
    public void startBettingRound() {
    if(currentBettingRound == null) {
        currentBettingRound = new BettingRound();
        bettingAuthority.getLoggingAuthority().logStartBettingRound(this.getCurrentBettingRound());;
    }
    else if(this.isBettingRoundFinished())
    {
       BetToken myBetToken = bettingAuthority.getTokenAuthority().getBetToken(currentBettingRound.getBettingRoundID());
        BetResult myBetResult = new BetResult(, new MoneyAmount(100));
        bettingAuthority.getLoggingAuthority().logEndBettingRound(currentBettingRound,myBetToken);
        bettingAuthority.getLoggingAuthority().logStartBettingRound(this.getCurrentBettingRound());

    }

    }

    @Override
    public boolean acceptBet(Bet bet, IGamingMachine gamingMachine) throws NoCurrentRoundException {
       if(currentBettingRound == null)
           throw new NoCurrentRoundException();
       else
       {
           bettingAuthority.getTokenAuthority().
       }

        return false;
    }

    @Override
    public void determineWinner() throws NoBetsMadeException {
        BetToken betToken = bettingAuthority.getTokenAuthority().getBetToken(currentBettingRound.getBettingRoundID());
        Integer winningNumber =  bettingAuthority.getTokenAuthority().getRandomInteger(betToken);
        IGameRule gameRule = new GameRule();
         gameRule.determineWinner(winningNumber,currentBettingRound.getAllBetsMade());
    }

    @Override
    public boolean isBettingRoundFinished() {
        return false;
    }
}
