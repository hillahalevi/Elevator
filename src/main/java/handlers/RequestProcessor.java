package handlers;

import operator.ElevatorOperator;

public class RequestProcessor implements Runnable {

    @Override
    public void run() {
        while (true) {
            ElevatorOperator elevator = ElevatorOperator.getInstance();
            UserRequest requestFloor = elevator.nextRequest();
            Integer currentFloor = elevator.getCurrentFloor();
            try {
                if (requestFloor.getDestination() >= 0) {
                    if (currentFloor > requestFloor.getDestination()) {
                        goDown(requestFloor.getDestination());
                    } else {
                        goUp(requestFloor.getDestination());
                    }
                    //arrival !
                    System.out.println("Finally - Welcome to Floor : " + elevator.getCurrentFloor() + " enjoy your staying");
                }

            } catch (InterruptedException e) {
                //check if interruption was due to a more pressing request
                if (!elevator.getCurrentFloor().equals(requestFloor.getDestination())) {
                    elevator.getRequestSet().add(requestFloor);
                }
            }
        }
    }


    private void goDown(int floor) throws InterruptedException {
        ElevatorOperator elevator = ElevatorOperator.getInstance();
        Integer currentFloor = elevator.getCurrentFloor();
        while (currentFloor > floor) {
            elevator.moveDown();
            currentFloor = elevator.getCurrentFloor();
        }
    }

    private void goUp(int floor) throws InterruptedException {
        ElevatorOperator elevator = ElevatorOperator.getInstance();
        Integer currentFloor = elevator.getCurrentFloor();
        while (currentFloor < floor) {
            elevator.moveUp();
            currentFloor = elevator.getCurrentFloor();
        }
    }
}
