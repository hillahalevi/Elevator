package handlers;

import operator.ElevatorOperator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestListener implements Runnable {

    private static final String POSITIVE_INTEGERS_REGEX = "\\d{1,2}";

    @Override
    public void run() {

        while (true) {
            String floorNumberStr = null;
            try {
                // Read input from console
                BufferedReader pressReader = new BufferedReader(new InputStreamReader(System.in));
                floorNumberStr = pressReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            handlePressInput(floorNumberStr);


        }
    }

    private void handlePressInput(String input) {
        if (isValidNumber(input)) {
            System.out.println("you Pressed : " + input);
            ElevatorOperator elevator = ElevatorOperator.getInstance();
            UserRequest userRequest = new UserRequest(Integer.parseInt(input));
            elevator.addRequest(userRequest);
        } else {
            System.out.println("floor number is invalid : " + input);
            //todo throw exception
        }
    }

    private boolean isValidNumber(String numberInput) {
        return (numberInput != null) && numberInput.matches(POSITIVE_INTEGERS_REGEX);
    }


}
