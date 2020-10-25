package casino.idfactory;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CardIDTest {

    /**
     * Test to see if the cardID is always unique;
     */
    @Test
    public void uniqueCardID(){
        Set<CardID> cardIDS=new HashSet<CardID>();

        //test with 10 times generating BetID
        for (int i = 0; i < 10 ; i++) {
            cardIDS.add((CardID)IDFactory.generateID("CardID"));
        }

        //because betIDs is a set, so there is no duplicating elements (BetID)
        //therefore just need to test if the size of betIDs = 10
        assertEquals(10,cardIDS.size());
    }
}