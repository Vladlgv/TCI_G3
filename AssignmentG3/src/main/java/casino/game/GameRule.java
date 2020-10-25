package casino.game;

import casino.bet.Bet;
import casino.bet.BetResult;

import java.util.Random;
import java.util.Set;

public class GameRule implements IGameRule {

    private int maxBetsPerRound;

    public GameRule(int maxBetsPerRound){
        this.maxBetsPerRound=maxBetsPerRound;
    }

    public GameRule(){
        this.maxBetsPerRound=0;
    }

    @Override
    public BetResult determineWinner(Integer randomWinValue, Set<Bet> bets) throws NoBetsMadeException {
        if(bets==null||bets.size()==0){
            throw new NoBetsMadeException();
        }
        if(randomWinValue>0&&randomWinValue<=bets.size()){
            Bet bet=(Bet)bets.toArray()[randomWinValue-1];
            return new BetResult(bet,bet.getMoneyAmount());
        }
        return null;
    }

    @Override
    public int getMaxBetsPerRound() {
        return this.maxBetsPerRound;
    }
}
