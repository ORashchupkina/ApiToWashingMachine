# ApiToWashingMachine

Backend service to control a washing machine. The opportunities of the service is to get state of washing machine and send it tasks.
The API is REST based and the state and tasks to the appliance is persisted in txt file in user directory in folder “WashingMachine”.

As application server was used GlassFish.

To set task to appliance (owner of the washing machine run this request from his mobile phone or ect, and new task appear in the service) you should run function:
test.RequestOwnerWashingMachine.setTask
mode, temperature, speed and time – is the parameters of http metod, that describe settings for starting the washing machine. They can be any, the server doesn’t check them.
http://localhost:8080/washingMachineApi/api/settask

To send task to washing machine (washing machine run this request to get tasks) you should run function:
test.RequestWashingMachine.getTaskFrService
http://localhost:8080/washingMachineApi/api/gettask

To get state of washing machine (washing machine run this request to send its state to the service):
Test.RequestWashingMachine. sendState
state, error - is the parameters of http metod, that describe state of washing machine and some errors, if them occurs
http://localhost:8080/washingMachineApi/api/state

