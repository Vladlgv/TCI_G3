package casino.idfactory;

import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class IDFactoryTest {

    ///
    //Test to see if betting Round ID is properly generated
    ///
    @Test
    public void test_BettingRoundID_GeneratesNumber() {
        //arrange
        GeneralID bettingRoundID;
        //act
        bettingRoundID = (BettingRoundID) IDFactory.generateID("BettingRoundID");
        //assert
        Assertions.assertThat(bettingRoundID.getUniqueID()).isInstanceOf(UUID.class);
        Assertions.assertThat(bettingRoundID.getTimeStamp()).isNotNull();
        Assertions.assertThat(bettingRoundID).isNotNull();
    }
}