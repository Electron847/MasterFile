#pragma config(StandardModel, "EV3_REMBOT")

int screen_x_bounds[2]= {0 , 178}; //Horizontal
int screen_y_bounds[2]= {0 , 128}; //Vertical
int ellipse_width 		= 32;
int ellipse_height 		= 32;

int ellipse_center[2]	= {	(rand() % (screen_x_bounds[1] - (ellipse_width / 2))) + screen_x_bounds[0] + (ellipse_width / 2) ,
													(rand() % (screen_y_bounds[1] - (ellipse_height / 2))) + screen_y_bounds[0] + (ellipse_height / 2)};

int velocity_max = 3;
int velocity_range = (velocity_max * 2) + 1;
int velocity[2] = {(rand() % velocity_range) - velocity_max , (rand() % velocity_range) - velocity_max};

int frame_delay = 1000/5;

float total_time = 0;
float total_degrees_traveled = 0;

float ellipse_speed = sqrt(pow(velocity[0] , 2) + pow(velocity[1] , 2));
float motor_power_level = 10 * ellipse_speed;

void show_ellipse(){
	int ellipse_top 		= ellipse_center[1] + (ellipse_height / 2);
	int ellipse_bottom	= ellipse_center[1] - (ellipse_height / 2);
	int ellipse_left 		= ellipse_center[0] + (ellipse_width / 2);
	int ellipse_right		= ellipse_center[0] - (ellipse_width / 2);
	drawEllipse(ellipse_left , ellipse_top , ellipse_right , ellipse_bottom);
	sleep(frame_delay);
	eraseEllipse(ellipse_left , ellipse_top , ellipse_right , ellipse_bottom);
	total_time = total_time + frame_delay;
	return;
}

void change_direction(char d){
	//writeDebugStreamLine("change_direction(%c)" , d);
	if(d == 'x') velocity[0] *= -1;
	if(d == 'y') velocity[1] *= -1;
	return;
}

const float degreesPerRotation = 360.0;
const float time_for_rotation = 6900;
const float time_per_degree = time_for_rotation / degreesPerRotation;

void pointTurnRight(int degree)
{
	//int timePerDegree = time_per_degree * degree;
	setMotorSpeed(leftMotor, 0);
	setMotorSpeed(rightMotor, 0);
	setMotorSpeed(leftMotor, motor_power_level);
	setMotorSpeed(rightMotor, -motor_power_level);
}

void pointTurnLeft(int degree)
{
	//int timePerDegree = time_per_degree * degree;
	setMotorSpeed(leftMotor, 0);
	setMotorSpeed(rightMotor, 0);
	setMotorSpeed(leftMotor, -motor_power_level);
	setMotorSpeed(rightMotor, motor_power_level);

}

void moveForward(int sleep_time)
{
	setMotorSpeed(leftMotor, 0);
	setMotorSpeed(rightMotor, 0);
	setMotorSpeed(leftMotor, motor_power_level);
	setMotorSpeed(rightMotor, motor_power_level);
}
void moveBackward(int sleep_time)
{
	setMotorSpeed(leftMotor, 0);
	setMotorSpeed(rightMotor, 0);
	setMotorSpeed(leftMotor, -motor_power_level);
	setMotorSpeed(rightMotor, -motor_power_level);
	//sleep(sleep_time);

}


/**/
void update_ellipse_loc(){
	//writeDebugStreamLine("update_ellipse_loc()");
	int ellipse_top 		= ellipse_center[1] + (ellipse_height / 2) + velocity[1];
	int ellipse_bottom	= ellipse_center[1] - (ellipse_height / 2) + velocity[1];
	int ellipse_left 		= ellipse_center[0] - (ellipse_width / 2) + velocity[0];
	int ellipse_right		= ellipse_center[0] + (ellipse_width / 2) + velocity[0];
	drawEllipse(ellipse_left , ellipse_top , ellipse_right , ellipse_bottom);
	sleep(frame_delay);
	total_time = total_time + frame_delay;
	eraseEllipse(ellipse_left , ellipse_top , ellipse_right , ellipse_bottom);
	ellipse_center[0] += velocity[0];
	ellipse_center[1] += velocity[1];
	bool touching_wall[] = {(ellipse_left <= screen_x_bounds[0]) || (ellipse_right >= screen_x_bounds[1]) ,
													 (ellipse_bottom <= screen_y_bounds[0]) || (ellipse_top >= screen_y_bounds[1])};
	if((touching_wall[0] == true) && (ellipse_left <= screen_x_bounds[0])){
		change_direction('x');
		pointTurnLeft(degreesPerRotation / 6);
		total_time = 0;
	}
	if((touching_wall[0] == true) && (ellipse_right >= screen_x_bounds[1])){
		change_direction('x');
		pointTurnRight(degreesPerRotation / 6);
		total_time = 0;
	}
	if((touching_wall[1] == true) && (ellipse_top >= screen_y_bounds[1])){
		change_direction('y');
		moveForward(total_time);
		total_time = 0;
	}
	if((touching_wall[1] == true) && (ellipse_bottom <= screen_y_bounds[0])){
		change_direction('y');
		moveBackward(total_time);
		total_time = 0;
	}
	return;
}

task main(){
	clearDebugStream();

	show_ellipse();
	while(true){
		update_ellipse_loc();
	}
}
