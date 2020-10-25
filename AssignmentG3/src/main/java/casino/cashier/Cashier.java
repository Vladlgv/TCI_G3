package casino.cashier;


import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.CardID;
import gamblingauthoritiy.IBetLoggingAuthority;

public class Cashier implements ICashier {

    private IBetLoggingAuthority loggingAuthority;

    public Cashier(IBetLoggingAuthority loggingAuthority) {
        this.loggingAuthority = loggingAuthority;
    }

    @Override
    public IGamblerCard distributeGamblerCard() {
        CardID cardID = new CardID();
        GamblerCard newGamblerCard = new GamblerCard(cardID,new MoneyAmount(0l));
        //BetLoggingAuthority
        loggingAuthority.logHandOutGamblingCard(cardID);
        return newGamblerCard;
    }

    @Override
    public void returnGamblerCard(IGamblerCard card) {
        //BetLoggingAuthority
        loggingAuthority.logHandInGamblingCard(card.getCardID(),card.returnBetIDs());
        card.returnBetIDsAndClearCard();// betID1 betID2
        card.clearCardAmount();
    }

    @Override
    public boolean checkIfBetIsValid(IGamblerCard card, Bet betToCheck) throws BetNotExceptedException {
        long cardAmount = card.getAmount();
        long betAmount = betToCheck.getMoneyAmount().getAmountInCents();
        if(cardAmount>=betAmount){
            return true;
        }
        else{
            throw new BetNotExceptedException();
        }
    }

    @Override
    public void addAmount(IGamblerCard card, MoneyAmount amount) throws InvalidAmountException {
        if(amount.getAmountInCents()<=0){
            throw new InvalidAmountException();
        }
        card.setCardAmount(amount.getAmountInCents());
    }
}
