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
        card.returnBetIDsAndClearCard();
    }

    @Override
    public boolean checkIfBetIsValid(IGamblerCard card, Bet betToCheck) throws BetNotExceptedException {
        return false;
    }

    @Override
    public void addAmount(IGamblerCard card, MoneyAmount amount) throws InvalidAmountException {

    }
}
