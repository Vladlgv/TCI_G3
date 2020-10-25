package casino.game;

import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.gamingmachine.GamingMachine;
import casino.idfactory.BettingRoundID;
import casino.idfactory.GamingMachineID;
import gamblingauthoritiy.BetLoggingAuthority;
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
    private IBetLoggingAuthority loggingAuthority;
    private GamingMachine gamingMachine;
    private IGame iGame;
    private static int MaxAmountOfBets = 10;


    //Define Set to store all Bets in this Betting round
    private Set<Bet> bets = new HashSet<Bet>();

    public BettingRound(BettingRoundID bettingRoundID,BetTokenAuthority betTokenAuthority, IGame iGame, IBetLoggingAuthority loggingAuthority){
        this.bettingRoundID = bettingRoundID;
        this.betTokenAuthority = betTokenAuthority;
        betToken = betTokenAuthority.getBetToken(bettingRoundID);
        this.iGame =  iGame;
        this.loggingAuthority = loggingAuthority;
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
            loggingAuthority.logAddAcceptedBet(bet,bettingRoundID,gamingMachine.getGamingMachineID());
            return true;
        }


       /* gamingMachine.placeBet(bet.getMoneyAmount().getAmountInCents());
        try {
            GamblerCard gamblerCard = gamingMachine.getGamblerCard();
            cashier.checkIfBetIsValid(gamblerCard,bet);
            bets.add(bet);
            loggingAuthority.logAddAcceptedBet(bet,bettingRoundID,gamingMachine.getGamingMachineID());
        } catch (BetNotExceptedException e) {
            return false;
        }*/
    }

    @Override
    public Set<Bet> getAllBetsMade() {
        return this.bets;
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
