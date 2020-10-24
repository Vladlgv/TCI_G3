package casino.cashier;


import casino.bet.Bet;
import casino.bet.MoneyAmount;
import gamblingauthoritiy.IBetLoggingAuthority;

public class Cashier implements ICashier {

    public Cashier(IBetLoggingAuthority loggingAuthority) {

    }

    @Override
    public IGamblerCard distributeGamblerCard() {
        return null;
    }

    @Override
    public void returnGamblerCard(IGamblerCard card) {
        card.returnBetIDsAndClearCard();// betID1 betID2
        card.clearCardAmount();
    }

    @Override
    public boolean checkIfBetIsValid(IGamblerCard card, Bet betToCheck) throws BetNotExceptedException {
        return false;
    }

    @Override
    public void addAmount(IGamblerCard card, MoneyAmount amount) throws InvalidAmountException {
        card.setCardAmount(amount.getAmountInCents());
    }
}
