#pragma config(StandardModel, "EV3_REMBOT")
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

const unsigned int millis=1000;
const unsigned int rangeDelay=millis/25; // divide 1000 by the rate in Hz
const unsigned int motorDelay=millis/10;
const unsigned int mainDelay=millis/50;
const unsigned int displayDelay=millis/5;

// motor power levels
const unsigned int motorForwardPower=50;
const unsigned int motorIdlePower=0;

// distanct threshold in cm
const unsigned int distanceThreshold=20;

// states
enum STATES{IDLE=0,	FORWARD=1};
enum STATES state; // variable to hold current State

// sensor and actuator variables
unsigned int distance;
int motorPower;

string stateNames[]={"IDLE","FORWARD"};

/********* TASKS *************/
task RangeSensorTask(){
	while(1)
	{
		distance=getUSDistance(sonarSensor);
		sleep(rangeDelay);
	}
}

task MotorTask(){
	while(1)
	{
		setMotorSync(leftMotor,rightMotor,0,motorPower);
		sleep(motorDelay);
	}
}

task DisplayTask(){
	while(1)
	{
		displayTextLine(1,"State = %s",stateNames[state]);
		displayTextLine(3,"Distance = %d",distance);
		displayTextLine(5,"Motor Power = %d",motorPower);
		sleep(displayDelay);
	}
}

task main()
{

	//initialize states and variables
	state=IDLE;
	motorPower=motorIdlePower;

	// start tasks
	startTask(RangeSensorTask);
	startTask(MotorTask);
	startTask(DisplayTask);

	while(1)
	{
		// state transition table
		switch (state)
		{
		case IDLE:
			if(distance>=distanceThreshold)
			{
				state=FORWARD;
			}
			break;
		case FORWARD:
			if(distance<distanceThreshold)
			{
				state=IDLE;
			}
			break;
		default:
			state=IDLE;
			break;
		}
		// state execution table
		switch (state)
		{
		case IDLE:
			motorPower=motorIdlePower;
			break;
		case FORWARD:
			motorPower=motorForwardPower;
			break;
		default:
			motorPower=motorIdlePower;
			break;
		}
		sleep(mainDelay);
	}
}