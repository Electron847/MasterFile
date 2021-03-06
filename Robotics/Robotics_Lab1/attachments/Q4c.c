#pragma config(StandardModel, "EV3_REMBOT")
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

const float time_for_360 = 8200;
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
	//uncomment the degree test you'd like to run
	//leave all other test commented out

	pointTurn(time_per_degree * 30);
	//pointTurn(time_per_degree * 45);
	//pointTurn(time_per_degree * 90);
	//pointTurn(time_per_degree * 135);
	//pointTurn(time_per_degree * 180);
	//pointTurn(time_per_degree * 360);
}
