package ElevatorTest;

import Elevator.Scheduler;
import Elevator.Floor;
import Elevator.Elevator;
import Elevator.Request;
import Elevator.Direction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class ElevatorTests {

    Thread elevatorThread;
    Elevator elevator;
    Scheduler scheduler;

    @Before
    public  void setUp() throws InterruptedException {
        scheduler = new Scheduler();
        elevator = new Elevator(scheduler, 1);
        elevator.setTestEnabled(true);
        elevatorThread = new Thread(elevator, "Elevator 1");

    }

    @Test
    public void completeRequest() throws InterruptedException, ClassNotFoundException {
        //Create and start threads
        elevatorThread.start();
        scheduler.putRequest(new Request(LocalDateTime.now(), 1, Direction.UP, 3));
        elevatorThread.join();

        Assert.assertEquals(scheduler.getRequestsCompleted(), 1);
        Assert.assertTrue(elevator.getCurrentFloor() == 3);
    }
}