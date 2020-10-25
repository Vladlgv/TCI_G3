package casino.idfactory;

import static org.junit.Assert.*;
import casino.gamingmachine.GamingMachine;
import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.UUID;


public class GamingMachineIDTest {
    GeneralID gamingMacineID;
    /**
    Test to see if game machine ID is properly generated
    */
    @Test
    public void test_CreateGamingMachineID_GamingMachineIDIsCreated() {
        //arrange
        //act
        gamingMacineID = (GamingMachineID) IDFactory.generateID("GamingMachineID");
        //assert
        Assertions.assertThat(gamingMacineID.getUniqueID()).isInstanceOf(UUID.class);
        Assertions.assertThat(gamingMacineID.getTimeStamp()).isNotNull();
        Assertions.assertThat(gamingMacineID).isNotNull();
    }

    /**
    Test to see if game machine ID is properly generated
    */
    @Test
    public void test_CreateGamingMachineID_GamingMachineIDIsCreatedWithout_IDFactory() {
        //arrange
        GeneralID gamingMacineID;
        //act
        gamingMacineID = new GamingMachineID();
        //assert
        Assertions.assertThat(gamingMacineID.getUniqueID()).isInstanceOf(UUID.class);
        Assertions.assertThat(gamingMacineID.getTimeStamp()).isNotNull();
        Assertions.assertThat(gamingMacineID).isNotNull();
    }

    /**
    Test to see if game machine ID is properly generated and has different UUID
    */
    @Test
    public void test_CreateGamingMachineID_GamingMachineHasDifferentUUID() {
        //arrange
        GamingMachineID g2 = new GamingMachineID();
        GamingMachineID g3 = new GamingMachineID();
        //act
        gamingMacineID = new GamingMachineID();
        //assert
        Assertions.assertThat(gamingMacineID.getUniqueID()).isNotSameAs(g2.getUniqueID());
        Assertions.assertThat(gamingMacineID.getUniqueID()).isNotSameAs(g3.getUniqueID());
        Assertions.assertThat(g2.getUniqueID()).isNotSameAs(g3.getUniqueID());

    }
}