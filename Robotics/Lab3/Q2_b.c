#pragma config(StandardModel, "EV3_REMBOT")


bool touching_wall = false;
int motor_speed_left 	= 16;
int motor_speed_right = 16;

int monitor_sensor_touch_hertz 	= 32; //These all need to be high, because the touch sensor has to be polled
int controller_motor_hertz			= 32; // often to keep the robot from pushing into the object that is touched.
int main_hertz 									= 64;

task monitor_sensor_touch(){
	while(true){
		touching_wall = !getTouchValue(S1);

		sleep(1000 / monitor_sensor_touch_hertz);
	}
}

task controller_motor(){
	int previous_left		= -1;
	int previous_right	= -1;

	while(true){
		if(	motor_speed_left != previous_left || motor_speed_right != previous_right){
			setMotorSpeed(motorB , motor_speed_left);
			setMotorSpeed(motorC , motor_speed_right);
		}

		sleep(1000 / controller_motor_hertz);
	}
}

task main(){
	startTask(monitor_sensor_touch);
	startTask(controller_motor);

	while(true){
		if(!touching_wall){
			motor_speed_left 	= 0;
			motor_speed_right = 0;
		}
		sleep(1000 / main_hertz);
	}

	while(true){};
}
