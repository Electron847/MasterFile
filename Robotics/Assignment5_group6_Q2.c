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
		else{
			speedB = 25;
			speedC = 25;
	}

		sleep(mainhertz);
}


}
