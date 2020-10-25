package casino.game;


import casino.gamingmachine.GamingMachine;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.BettingAuthority;

import java.util.HashSet;
import java.util.Set;

abstract class AbstractGame implements IGame{

    private IBettingRound currentBettingRound;
 // define only the constructor here
    public AbstractGame(BettingRound bettingRound){
        this.currentBettingRound = currentBettingRound;

    }


}
