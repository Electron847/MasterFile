/*
Q1_a
have 2 tasks one to get the color value and one to control the motors. use global variables for the color value and the motor speed. start the motors going forward speed of 25 and start
sensing the color infront. once the color value changes to 5, which correlates to red, set the motor speed to 0.
Q1_b
One of the challanges would be mismatching the motors and the sensors timing, leading to a not instant reaction. This can be addressed by shortening the refresh rates
*/

int globalColorValue = 0;
int speedB = 25;
int speedC = 25;
const unsigned int sensorhertz = 1000/25;
const unsigned int motorhertz = 1000/20;
const unsigned int displayhertz = 1000/5;
const unsigned int mainhertz = 1000/50;

task senseColors(){
	while(true){
	globalColorValue = getColorName(S3);
	sleep(sensorhertz);
	}
}

task moveUntil(){
	while(true){
	setMotorSpeed(motorB,speedB);
	setMotorSpeed(motorC,speedC);
	sleep(motorhertz);
	}
}

task display(){
		while(true){
		displayTextLine(1,"%d",globalColorValue);
		sleep(displayhertz);
	}
}

task main()
{
	startTask(senseColors);
	startTask(moveUntil);
	startTask(display);

	while(true){
		if(globalColorValue==5){
			speedB = 0;
			speedC = 0;
	}
		sleep(mainhertz);
}


}
