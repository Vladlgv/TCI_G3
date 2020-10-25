package casino.idfactory;

import casino.gamingmachine.GamingMachine;
import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class GamingMachineIDTest {
    ///
    //Test to see if betting Round ID is properly generated
    ///
    @Test
    public void test_CreateGamingMachineID_GamingMachineIDIsCreated() {
        //arrange
        GeneralID gamingMacineID;
        //act
        gamingMacineID = (GamingMachineID) IDFactory.generateID("GamingMachineID");
        //assert
        Assertions.assertThat(gamingMacineID.getUniqueID()).isInstanceOf(UUID.class);
        Assertions.assertThat(gamingMacineID.getTimeStamp()).isNotNull();
        Assertions.assertThat(gamingMacineID).isNotNull();
    }
}