package casino.gamingmachine;


import casino.bet.BetResult;
import casino.cashier.GamblerCard;
import casino.cashier.IGamblerCard;
import casino.idfactory.GamingMachineID;

public class GamingMachine implements IGamingMachine {

    private GamblerCard gamblerCard;

    public GamblerCard getGamblerCard() {
        return gamblerCard;
    }

    @Override
    public boolean placeBet(long amountInCents) throws NoPlayerCardException {
        return false;
    }

    @Override
    public void acceptWinner(BetResult winResult) {

    }

    @Override
    public GamingMachineID getGamingMachineID() {
        return null;
    }

    @Override
    public void connectCard(IGamblerCard card) {

    }

    @Override
    public void disconnectCard() throws CurrentBetMadeException {

    }
}
