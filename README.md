# Elevator - I did it my way (sing it !)
multi threaded elevator - lets go !


elevator current logic :
- request are processed in order but with considarion to the elevator current location and direction for efficancy
- when theres no request the elevator waits at the last floor


implemantation deatails : 
-  request listener thread - read "press buttuns" from the console and pass them to the ElevatorOperator
-  Request-Processor thread - fetches next existing jobs from the ElevatorOperator(pick up requests) and process as needed 
-  ElevatorOperator - singelton elevator operating system ,hold all jobs, handle job priority and act


whats next ? 
- enable use of elevators with different logic (ptiority or action)
- enable system with mutiple elevators
- expend user request
- limit elevator capacity 
- open close doors
- light logic 

bye bye for now :) 
