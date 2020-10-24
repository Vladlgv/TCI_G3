package casino.bet;

import casino.cashier.GamblerCard;
import casino.idfactory.BetID;
import casino.idfactory.IDFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BetTest {

    @Test
    public void CreateABet(){
        BetID betId= mock(BetID.class);
        MoneyAmount moneyAmount=mock(MoneyAmount.class);
        Bet bet=new Bet(betId,moneyAmount);

        assertEquals(betId,bet.getBetID());
        assertEquals(moneyAmount,bet.getMoneyAmount());
    }

    @Test
    public void TheNewBetIsExisted(){
        Set<BetID> betIDS=new HashSet<BetID>();

        for (int i = 0; i < 10 ; i++) {
            betIDS.add((BetID)IDFactory.generateID("BetID"));
        }

        //because betIDs is a set, so there is no duplicating elements (BetID)
        //therefore just need to test if the size of betIDs = 10
        assertEquals(10,betIDS.size());
    }
}