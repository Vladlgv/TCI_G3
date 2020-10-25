package casino.game;


import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.IGamingMachine;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.BettingAuthority;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DefaultGame extends AbstractGame {

    private IBettingRound currentBettingRound;
    private BettingAuthority bettingAuthority;
    private IGameRule gameRule;

    public Set<GamingMachine> getConnectedGamingMachines() {
        return connectedGamingMachines;
    }

    private Set<GamingMachine> connectedGamingMachines;

    public IBettingRound getCurrentBettingRound() {
        return currentBettingRound;
    }

    // need to mock the functionality that comes from the betting round and also in the beging the first betting round should be given by the
    //creating class.
    public DefaultGame(BettingRound currentBettingRound) {
        this.bettingAuthority = new BettingAuthority();
        this.connectedGamingMachines = new HashSet<>();
        this.currentBettingRound = currentBettingRound;
        this.gameRule = new GameRule();
    }

    public DefaultGame(IBettingRound currentBettingRound, IGameRule gameRule, BettingAuthority bettingAuthority){
        this.bettingAuthority = bettingAuthority;
        this.connectedGamingMachines = new HashSet<>();
        this.gameRule = gameRule;
        this.currentBettingRound = currentBettingRound;
    }

    //continue it make more tests for not happy paths
    @Override
    public void startBettingRound() throws NoBetsMadeException {
    if(currentBettingRound == null) {
        currentBettingRound = new BettingRound();
        bettingAuthority.getLoggingAuthority().logStartBettingRound(this.getCurrentBettingRound());;
    }
    else
    {
      // BetToken myBetToken = bettingAuthority.getTokenAuthority().getBetToken(currentBettingRound.getBettingRoundID());
        BetToken myBetToken = new BetToken(currentBettingRound.getBettingRoundID());
        BetResult myBetResult = gameRule.determineWinner((int)Math.random(),currentBettingRound.getAllBetsMade());
        bettingAuthority.getLoggingAuthority().logEndBettingRound(currentBettingRound,myBetResult);
        currentBettingRound = null;
    }


    }
    /**
     * Accept a bet on the current betting round.
     * determine if the betting round is finished, if so: determine the winner,
     * notify the connected gaming machines and start a new betting round.
     *
     * Note: also use the appropiate required methods from the gambling authority API
     *
     * @param bet the bet to be made on the betting round
     * @param gamingMachine gamingmachine which places bet on this game.
     * @return true when bet is accepted by the game, otherwise false.
     * @throws NoCurrentRoundException when no BettingRound is currently active.
     */
    @Override
    public boolean acceptBet(Bet bet, IGamingMachine gamingMachine) throws NoCurrentRoundException {
       if(currentBettingRound == null)
           throw new NoCurrentRoundException();
       else
       {
           boolean betAccepted = currentBettingRound.placeBet(bet);
           connectedGamingMachines.add((GamingMachine)gamingMachine);
           if(betAccepted) {
               bettingAuthority.getLoggingAuthority().logAddAcceptedBet(bet, currentBettingRound.getBettingRoundID(), gamingMachine.getGamingMachineID());
               return true;
           }
           else
           {
               return  false;
           }
       }
    }

    @Override
    public void determineWinner() throws NoBetsMadeException {

        if(currentBettingRound.getAllBetsMade().size() == 0)
        {
            BetToken betToken = bettingAuthority.getTokenAuthority().getBetToken(currentBettingRound.getBettingRoundID());
            currentBettingRound = null;
            bettingAuthority.getLoggingAuthority().logEndBettingRound(currentBettingRound,null);
        }
        else
        {
            BetToken betToken = bettingAuthority.getTokenAuthority().getBetToken(currentBettingRound.getBettingRoundID());
            Integer winningNumber =  bettingAuthority.getTokenAuthority().getRandomInteger(betToken);

            var winner =  gameRule.determineWinner(winningNumber,currentBettingRound.getAllBetsMade());

            for(GamingMachine gameMachine : getConnectedGamingMachines())
            {
                gameMachine.acceptWinner(winner);
            }

            currentBettingRound = null;
            bettingAuthority.getLoggingAuthority().logEndBettingRound(currentBettingRound,winner);

        }

    }

    @Override
    public boolean isBettingRoundFinished() throws NoBetsMadeException {
      //  if(gameRule.getMaxBetsPerRound() == currentBettingRound.numberOFBetsMade())
        if(10 == currentBettingRound.numberOFBetsMade())
        {
            return false;
        }
        return true;
    }
}
