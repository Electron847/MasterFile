#pragma config(StandardModel, "EV3_REMBOT")

/*

globally scoped touching_wall
globally scoped motor_left_speed will 	= some number
globally scoped motor_right_speed will	= same as left motor's speed (to go straight)

globally scoped monitor_sensor_touch_hertz	= some number
globally scoped controller_motor_hertz			= some number
globally scoped main_hertz 									= some number bigger than the above two

task monitor_sensor_touch:
	while forever:
		update touching_wall with currect value getTouchValue(S1)
		sleep for 1000ms divided by monitor_sensor_touch_hertz

task controller_motor;
	while forever:
		if the motor values have changed:
			set left motor speed to motor_left_speed
			set right motor speed to motor_right_speed
		sleep for 1000ms divided by controller_motor_hertz

task main:
	start all tasks other than main (it is already running)
	if the robot touches a wall:
		set left and right motor speeds to 0
	sleep for 1000ms divided by main_hertz

*/

task main(){

}
