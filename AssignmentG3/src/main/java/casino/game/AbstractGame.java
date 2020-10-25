package casino.game;


import casino.gamingmachine.GamingMachine;
import gamblingauthoritiy.BetLoggingAuthority;
import gamblingauthoritiy.BettingAuthority;

import java.util.HashSet;
import java.util.Set;

abstract class AbstractGame implements IGame{

    private IBettingRound currentBettingRound;
    private BettingAuthority bettingAuthority;
    private IGameRule gameRule;
    private Set<GamingMachine> connectedGamingMachines;
 // define only the constructor here
    public AbstractGame(BettingRound bettingRound){
        this.bettingAuthority = new BettingAuthority();
        this.connectedGamingMachines = new HashSet<>();
        this.currentBettingRound = currentBettingRound;
        this.gameRule = new GameRule();
    }


}
