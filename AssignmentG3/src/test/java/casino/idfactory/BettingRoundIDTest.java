package casino.idfactory;

import static org.junit.Assert.*;
import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.UUID;

public class BettingRoundIDTest {
    GeneralID bettingRoundID;

    /**
    Test to see if betting Round ID is properly generated
    */
    @Test
    public void test_BettingRoundID_GeneratesNumber() {
        //arrange
        //act
        bettingRoundID = (BettingRoundID) IDFactory.generateID("BettingRoundID");
        //assert
        Assertions.assertThat(bettingRoundID.getUniqueID()).isInstanceOf(UUID.class);
        Assertions.assertThat(bettingRoundID.getTimeStamp()).isNotNull();
        Assertions.assertThat(bettingRoundID).isNotNull();
    }

    /**
    Test to see if betting round  ID is properly generated
    */
    @Test
    public void test_CreateGamingMachineID_GamingMachineIDIsCreatedWithout_IDFactory() {
        //arrange
        //act
        bettingRoundID = new BettingRoundID();
        //assert
        Assertions.assertThat(bettingRoundID.getUniqueID()).isInstanceOf(UUID.class);
        Assertions.assertThat(bettingRoundID.getTimeStamp()).isNotNull();
        Assertions.assertThat(bettingRoundID).isNotNull();
    }

    /**
    Test to see if betting round ID is properly generated and has different UUID
    */
    @Test
    public void test_CreateGamingMachineID_GamingMachineHasDifferentUUID() {
        //arrange
        BettingRoundID b1 = new BettingRoundID();
        BettingRoundID b2 = new BettingRoundID();
        //act
        bettingRoundID = new BettingRoundID();
        //assert
        Assertions.assertThat(bettingRoundID.getUniqueID()).isNotSameAs(b1.getUniqueID());
        Assertions.assertThat(bettingRoundID.getUniqueID()).isNotSameAs(b2.getUniqueID());
        Assertions.assertThat(b1.getUniqueID()).isNotSameAs(b2.getUniqueID());

    }
}