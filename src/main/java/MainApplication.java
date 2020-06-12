import handlers.RequestListener;
import handlers.RequestProcessor;
import operator.ElevatorOperator;

public class MainApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to my elevation system - its just like time travel but different");


        // Request-Listener to read requests
        Thread requestListenerThread = new Thread(new RequestListener(), "Request-Listener");


        // Request-Processor to  process requests
        Thread requestProcessorThread = new Thread(new RequestProcessor(), "Request-Processor");



        ElevatorOperator.getInstance().setRequestProcessorThread(requestProcessorThread);


        requestListenerThread.start();
        requestProcessorThread.start();


    }
}


