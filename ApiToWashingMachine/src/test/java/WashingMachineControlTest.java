import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Util;

import java.io.File;

public class WashingMachineControlTest {
    String mode = "quick";
    String temperature = "60";
    String speed = "700";
    String time = "0";

    @BeforeClass
    public static void initializeDirectory(){
        Util util = new Util();
        util.creationDirectoryInCommonDir("/newTasks");
        util.creationDirectoryInCommonDir("/newStates");
    }

    @AfterClass
    public static void destructor(){
        Util util = new Util();
        String sUserDir = System.getProperty("user.home");
        String sCommonDir = sUserDir + "/WashingMachine";
        util.recursiveDelete(new File(sCommonDir));
    }

    // washing machine send state to service
    @Test
    public void getStateOfWashingMachine() {
        String state = "not busy";
        String error = "no";

        RequestWashingMachine requestWashingMachine = new RequestWashingMachine();
        String result = requestWashingMachine.sendState(state, error);
        Assertions.assertEquals("HTTP/1.1 204 No Content", result);
    }

    @Test
    public void sendTaskToWashingMachine() {
        RequestOwnerWashingMachine requestOwnerWashingMachine = new RequestOwnerWashingMachine();
        RequestWashingMachine requestWashingMachine = new RequestWashingMachine();
        // Set task to washing machine
        String resultSet = requestOwnerWashingMachine.setTask(mode,temperature,speed,time);
        Assertions.assertEquals(resultSet,"{\"mode\":\"" + mode + "\",\"temperature\":\"" + temperature + "\",\"time\":\"" + time + "\",\"speed\":\"" + speed + "\"}");
        // washing machine get task
        String resultGet = requestWashingMachine.getTaskFrService();
        Assertions.assertEquals(resultGet,"{mode=quick, temperature=60, time=0, speed=700}");
    }

    @Test
    public void getTaskToWashingMachineIfNoTask(){
        RequestWashingMachine requestWashingMachine = new RequestWashingMachine();
        // get task to washing machine if no task
        String result = requestWashingMachine.getTaskFrService();
        Assertions.assertEquals(result, "no tasks");
    }
}
