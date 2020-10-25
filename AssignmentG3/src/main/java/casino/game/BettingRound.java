package casino.game;

import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.gamingmachine.GamingMachine;
import casino.idfactory.BettingRoundID;
import casino.idfactory.GamingMachineID;
import gamblingauthoritiy.*;
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
    private BettingAuthority bettingAuthority;

    private GamingMachine gamingMachine;
    private IGame iGame;
    private static int MaxAmountOfBets = 10;


    //Define Set to store all Bets in this Betting round
    private Set<Bet> bets = new HashSet<Bet>();

    //TODO: Diqin Needs to fix
    public BettingRound(BettingRoundID bettingRoundID,IGame iGame, GamingMachine gamingMachine){
        this.bettingRoundID = bettingRoundID;
        this.betTokenAuthority = new BetTokenAuthority();
        betToken = betTokenAuthority.getBetToken(bettingRoundID);
        this.iGame =  iGame;
        this.bettingAuthority = new BettingAuthority();
        this.gamingMachine = gamingMachine;
    }

    public BettingRound(){
    }
    @Override
    public BettingRoundID getBettingRoundID() {
        return null;
    }

    @Override
    public boolean placeBet(Bet bet) throws IllegalArgumentException {
        //Check Bet Validattion
        if(bet==null){
            throw new IllegalArgumentException();
        }
        if(bets.size()>=MaxAmountOfBets){
            return false;
        }

        if(iGame.isBettingRoundFinished()==true){
            return false;
        }
        else {
            this.bets.add(bet);
            this.bettingAuthority.getLoggingAuthority().logAddAcceptedBet(bet,bettingRoundID,gamingMachine.getGamingMachineID());
            return true;
        }

    }

    @Override
    public Set<Bet> getAllBetsMade() {
        return this.bets;
    }

    @Override
    public BetToken getBetToken() {
        return this.betToken;
    }

    @Override
    public int numberOFBetsMade() {
        return this.bets.size();
    }
}
