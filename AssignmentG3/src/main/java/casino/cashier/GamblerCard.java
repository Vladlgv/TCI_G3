package casino.cashier;


import casino.idfactory.BetID;
import casino.idfactory.CardID;
import casino.idfactory.IDFactory;

import java.util.HashSet;
import java.util.Set;

public class GamblerCard implements IGamblerCard {

    private Set<BetID> betIDS=new HashSet<BetID>();
    @Override
    public Set<BetID> returnBetIDs() {
        return this.betIDS;
    }

    @Override
    public Set<BetID> returnBetIDsAndClearCard() {
        return null;
    }

    @Override
    public BetID generateNewBetID() {
        BetID newBetID= (BetID) IDFactory.generateID("BetID");
        this.betIDS.add(newBetID);
        return newBetID;
    }

    @Override
    public int getNumberOfBetIDs() {
        return ((int) betIDS.size());
    }

    @Override
    public CardID getCardID() {
        return null;
    }
}
