#pragma config(StandardModel, "EV3_REMBOT")
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

const float time_for_360 = 8175;
const float time_per_degree = time_for_360 / 360;

void pointTurn(int sleep_time)
{
	//int degreePerMillisecond = time_per_degree * degree;
	setMotorSpeed(motorB, -20);
	setMotorSpeed(motorC, 20);
	sleep(sleep_time);
	setMotorSpeed(motorB, 0);
	setMotorSpeed(motorB, 0);

}
task main()
{
	displayTextLine(1, "Q4b: %f", time_per_degree * 5);
	pointTurn(time_per_degree * 5);
}
