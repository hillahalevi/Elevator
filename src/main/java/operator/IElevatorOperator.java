package operator;

import handlers.UserRequest;

public interface IElevatorOperator {

    void moveDown() throws InterruptedException;

    void moveUp() throws InterruptedException;

    void addRequest(UserRequest request);

    UserRequest nextRequest();
}
