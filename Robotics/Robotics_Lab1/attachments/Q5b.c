#pragma config(StandardModel, "EV3_REMBOT")

//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

const int time_for_1_meter = 8550;
const float time_for_1_centimeter = time_for_1_meter / 100;

void moveStraight(int sleep_time){
	setMotorSpeed(motorB, 50);
	setMotorSpeed(motorC, 50);
	sleep(sleep_time);
	setMotorSpeed(motorB, 0);
	setMotorSpeed(motorC, 0);
	}

task main()
{
	int time = time_for_1_centimeter * 5;
	displayTextLine(1, "Q5: %f", time);
	moveStraight(time);
}
