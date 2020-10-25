package casino.cashier;


import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.BetID;
import casino.idfactory.CardID;
import casino.idfactory.IDFactory;

import java.util.HashSet;
import java.util.Set;

public class GamblerCard implements IGamblerCard {

    private CardID cardID;
    private MoneyAmount cardAmount;
    //BetIDS
    private Set<BetID> betIDS=new HashSet<BetID>();

    public GamblerCard(CardID cardID,MoneyAmount cardAmount){
        this.cardID=cardID;
        this.cardAmount=cardAmount;
    }

    @Override
    public Set<BetID> returnBetIDs() {
        return this.betIDS;
    }

    @Override
    public Set<BetID> returnBetIDsAndClearCard() {
        Set<BetID> tempSetBetIDs=new HashSet<BetID>();
        tempSetBetIDs.addAll(this.betIDS);
        this.betIDS.clear();
        return tempSetBetIDs;
    }

    @Override
    public BetID generateNewBetID() {
        BetID newBetID=(BetID) IDFactory.generateID("BetID");
        this.betIDS.add(newBetID);
        return newBetID;
    }

    @Override
    public int getNumberOfBetIDs() {
        return this.betIDS.size();
    }

    @Override
    public CardID getCardID() {
        return this.cardID;
    }

    @Override
    public MoneyAmount getCardAmount() {
        return this.cardAmount;
    }

    @Override
    public void setCardAmount(long amount) {
        long currentAmount=this.cardAmount.getAmountInCents();
        this.cardAmount=new MoneyAmount(currentAmount+amount);
    }

    @Override
    public void clearCardAmount() {
        this.cardAmount=new MoneyAmount(0);
    }

    @Override
    public void removeBetID(BetID betID) {
        this.betIDS.remove(betID);
    }
}
