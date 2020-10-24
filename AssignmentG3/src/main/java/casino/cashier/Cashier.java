package casino.cashier;


import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.CardID;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.IBetLoggingAuthority;

import java.util.HashSet;
import java.util.Set;

public class Cashier implements ICashier {

    private Set<GamblerCard> gamblerCards=new HashSet<GamblerCard>();

    public Cashier(IBetLoggingAuthority loggingAuthority) {

    }

    @Override
    public IGamblerCard distributeGamblerCard() {
        CardID newCardID= (CardID)IDFactory.generateID("CardID");
        MoneyAmount newCardAmount=new MoneyAmount(0);
        GamblerCard newCard=new GamblerCard(newCardID,newCardAmount);
        this.gamblerCards.add(newCard);
        return newCard;
    }

    @Override
    public void returnGamblerCard(IGamblerCard card) {
        card.returnBetIDsAndClearCard();
        card.clearCardAmount();
    }

    @Override
    public boolean checkIfBetIsValid(IGamblerCard card, Bet betToCheck){
        if(card.getCardAmount().getAmountInCents()<betToCheck.getMoneyAmount().getAmountInCents()){
            return false;
        }
        return true;
    }

    @Override
    public void addAmount(IGamblerCard card, MoneyAmount amount) throws InvalidAmountException {
        if(amount.getAmountInCents()<0){
            throw new InvalidAmountException();
        }else{
            card.setCardAmount(amount.getAmountInCents());
        }
    }
}
