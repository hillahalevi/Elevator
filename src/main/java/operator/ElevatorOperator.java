package operator;

import com.sun.javafx.scene.traversal.Direction;
import handlers.UserRequest;
import lombok.Data;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * singleton class to  represent elevator operating system
 */
@Data
public class ElevatorOperator implements IElevatorOperator {

    private static ElevatorOperator elevator = null;

    private TreeSet<UserRequest> requestSet = new TreeSet<>(Comparator.comparing(UserRequest::getDestination));

    private Integer currentFloor = 1;

    private Direction direction = Direction.UP;

    private Thread requestProcessorThread;

    /**
     * @return singleton instance
     */
    public static ElevatorOperator getInstance() {
        if (elevator == null) {
            elevator = new ElevatorOperator();
        }
        return elevator;
    }


    @Override
    public void moveDown() throws InterruptedException {
        goToFloor(--currentFloor);
    }

    @Override
    public void moveUp() throws InterruptedException {
        goToFloor(++currentFloor);

    }

    @Override
    public synchronized void addRequest(UserRequest request) {
        requestSet.add(request);
        if (requestProcessorThread.getState() == Thread.State.WAITING) {
            // Notify processor thread that a new jib was added
            notify();
        } else {
            // check if our new request should be precessed before current request
            requestProcessorThread.interrupt();
        }
    }

    @Override
    public synchronized UserRequest nextRequest() {
        UserRequest floor;
        UserRequest currentDesinationRequest = new UserRequest(currentFloor);
        if (direction == Direction.UP) {
            if (requestSet.ceiling(currentDesinationRequest) != null) {
                floor = requestSet.ceiling(currentDesinationRequest);
            } else {
                floor = requestSet.floor(currentDesinationRequest);
            }
        } else {
            if (requestSet.floor(currentDesinationRequest) != null) {
                floor = requestSet.floor(currentDesinationRequest);
            } else {
                floor = requestSet.ceiling(currentDesinationRequest);
            }
        }


        if (floor == null) {
            try {
                System.out.println("Waiting at Floor :" + getCurrentFloor());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Remove the request from Set as it is the request in Progress.
            requestSet.remove(floor);
        }
        if (floor == null) {
            floor = new UserRequest(-1);
        }
        return floor;
    }


    private void goToFloor(int requestFloor) throws InterruptedException {
        if (this.currentFloor > requestFloor) {
            setDirection(Direction.DOWN);
        } else {
            setDirection(Direction.UP);
        }
        this.currentFloor = requestFloor;

        System.out.println("Floor : " + requestFloor);

        //each move between floors take time so sleep
        Thread.sleep(2000);
    }

}
