package casino.game;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;

import java.lang.reflect.Array;
import java.util.Set;

import static org.apache.commons.lang3.ArrayUtils.toArray;

public class GameRule implements IGameRule {
    @Override
    public BetResult determineWinner(Integer randomWinValue, Set<Bet> bets) throws NoBetsMadeException {
//        int myNumber = randomWinValue;
//
//         Bet arrayBets[] =  (Bet[])bets.toArray();
//         int firstHash = (int) ((Bet) arrayBets[0]).getBetID().getUniqueID().hashCode();
//        int distance = Math.abs(firstHash - myNumber );
//        int idx = 0;
//        for(int c = 1; c < arrayBets.length; c++){
//            int currentHash = (int) ((Bet) arrayBets[c]).getBetID().getUniqueID().hashCode();
//            int cdistance = Math.abs(currentHash - myNumber);
//            if(cdistance < distance){
//                idx = c;
//                distance = cdistance;
//            }
//        }
//        Bet theWinner = arrayBets[idx];
//        long amountToWin = 0;
//        for(Bet b: bets)
//        {
//           amountToWin += b.getMoneyAmount().getAmountInCents();
//        }
        BetResult theBetReuslt = new BetResult((Bet)bets.toArray()[0],new MoneyAmount(1000));
        return theBetReuslt;
    }

    @Override
    public int getMaxBetsPerRound() {
        return 10;
    }
}
