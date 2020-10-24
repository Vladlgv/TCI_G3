package casino.game;


import casino.bet.Bet;
import casino.gamingmachine.IGamingMachine;

public class DefaultGame extends AbstractGame {

    @Override
    public void startBettingRound() {

    }

    @Override
    public boolean acceptBet(Bet bet, IGamingMachine gamingMachine) throws NoCurrentRoundException {
        return false;
    }

    @Override
    public void determineWinner() {

    }

    @Override
    public boolean isBettingRoundFinished() {
        return false;
    }
}
