#pragma config(StandardModel, "EV3_REMBOT")

//inconsistent turn times between group members 
//inconsistent turn times between programs
const float time_for_360 	=	7569.987;
const float time_per_deg	=	time_for_360 / 360;
const int time_for_1_meter = 8550;
const float time_for_1_centimeter	= time_for_1_meter / 100;

void pointTurn(int deg){
	int deg_ms = time_per_deg * deg;
	//displayTextLine(3 , "%d degree point turn" , deg);
	setMotorSpeed(motorB , -20);
	setMotorSpeed(motorC , 20);
	sleep(deg_ms);
	setMotorSpeed(motorB , 0);
	setMotorSpeed(motorC , 0);
}

void moveStraight(int sleep_time){
	setMotorSpeed(motorB , 50);
	setMotorSpeed(motorC , 50);
	sleep(sleep_time);
	setMotorSpeed(motorB , 0);
	setMotorSpeed(motorC , 0);
}

int distance_cm_to_ms(int cm){
	return cm * time_for_1_centimeter;
}

task main()
{
	displayTextLine(1 , "Start of the program");
	sleep(1000);
	for(int i=0;i<5;i++){
		displayTextLine(2 , "Iteration: %d", i+1);
		for(int j=0;j<2;j++){
			displayTextLine(3 , "Direction command: %s", "out");
			moveStraight(distance_cm_to_ms(100));
			pointTurn(180);
			displayTextLine(3 , "Direction command: %s", "back");
			moveStraight(distance_cm_to_ms(100));
			pointTurn(180);
		}
	}
	eraseDisplay();
	displayTextLine(1 , "End of the program");
}
