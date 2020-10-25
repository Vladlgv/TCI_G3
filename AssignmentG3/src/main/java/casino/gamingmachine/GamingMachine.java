package casino.gamingmachine;


import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.cashier.BetNotExceptedException;
import casino.cashier.ICashier;
import casino.cashier.IGamblerCard;
import casino.cashier.InvalidAmountException;
import casino.game.IGame;
import casino.idfactory.BetID;
import casino.idfactory.GamingMachineID;

import java.rmi.server.ExportException;
import java.util.HashSet;
import java.util.Set;

public class GamingMachine implements IGamingMachine {

    private ICashier cashier;
    private GamingMachineID gamingMachineID;
    private IGamblerCard connectedCard;
    private Set<Bet> openBets=new HashSet<Bet>();
    private IGame iGame;

    public GamingMachine(GamingMachineID gamingMachineID,ICashier cashier,IGame igame){
        this.gamingMachineID=gamingMachineID;
        this.connectedCard=null;
        this.cashier=cashier;
        this.iGame=igame;
    }
    public GamingMachine(){
    }

    @Override
    public boolean placeBet(long amountInCents) throws NoPlayerCardException, BetNotExceptedException {
        if(this.connectedCard!=null){
            BetID betID=this.connectedCard.generateNewBetID();
            Bet newBet=new Bet(betID,new MoneyAmount(amountInCents));
            if(this.cashier.checkIfBetIsValid(this.connectedCard,newBet)){
                this.openBets.add(newBet);
                return true;
            }else{
                this.connectedCard.removeBetID(betID);
                return false;
            }
        }else{
            throw new NoPlayerCardException();
        }
    }

    @Override
    public void acceptWinner(BetResult winResult) {
        //clear bets
        this.openBets.clear();
        clearAllBets();
    }

    @Override
    public GamingMachineID getGamingMachineID() {
        return this.gamingMachineID;
    }

    @Override
    public void connectCard(IGamblerCard card) {
        this.connectedCard=card;
    }

    @Override
    public void disconnectCard() throws CurrentBetMadeException {
        if(this.iGame.isBettingRoundFinished()){
            this.connectedCard=null;
        }else{
            throw new CurrentBetMadeException();
        }

    }

    @Override
    public IGamblerCard getConnectedCard() {
        return this.connectedCard;
    }


    @Override
    public Bet getBetWithMoneyAmount(MoneyAmount moneyAmount) {
        for (Bet bet:this.openBets
             ) {
            if(bet.getMoneyAmount().equals(moneyAmount)){
                return bet;
            }
        }
        return null;
    }

    @Override
    public int getNumberOfBets() {
        return this.openBets.size();
    }

    @Override
    public void clearAllBets() {

    }
}
