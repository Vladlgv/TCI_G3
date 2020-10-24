package casino.cashier;


import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.CardID;
import gamblingauthoritiy.IBetLoggingAuthority;

public class Cashier implements ICashier {

    public Cashier(IBetLoggingAuthority loggingAuthority) {

    }

    @Override
    public IGamblerCard distributeGamblerCard() {
        GamblerCard newGamblerCard = new GamblerCard(new CardID(),new MoneyAmount(0l));
        return newGamblerCard;
    }

    @Override
    public void returnGamblerCard(IGamblerCard card) {
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
        else
        return false;
    }

    @Override
    public void addAmount(IGamblerCard card, MoneyAmount amount) throws InvalidAmountException {
        card.setCardAmount(amount.getAmountInCents());
    }
}
