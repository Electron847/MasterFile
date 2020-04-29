#pragma config(StandardModel, "EV3_REMBOT")

int screen_x_bounds[2]= {0 , 178}; //Horizontal
int screen_y_bounds[2]= {0 , 128}; //Vertical
int ellipse_width 		= 8;
int ellipse_height 		= 8;

int ellipse_center[2]	= {	(rand() % (screen_x_bounds[1] - (ellipse_width / 2))) + screen_x_bounds[0] + (ellipse_width / 2) ,
													(rand() % (screen_y_bounds[1] - (ellipse_height / 2))) + screen_y_bounds[0] + (ellipse_height / 2)};

int velocity_max = 3;
int velocity_range = (velocity_max * 2) + 1;
int velocity[2] = {(rand() % velocity_range) - velocity_max , (rand() % velocity_range) - velocity_max};

int frame_delay = 1000/30;

void show_ellipse(){
	int ellipse_top 		= ellipse_center[1] + (ellipse_height / 2);
	int ellipse_bottom	= ellipse_center[1] - (ellipse_height / 2);
	int ellipse_left 		= ellipse_center[0] + (ellipse_width / 2);
	int ellipse_right		= ellipse_center[0] - (ellipse_width / 2);
	drawEllipse(ellipse_left , ellipse_top , ellipse_right , ellipse_bottom);
	sleep(frame_delay);
	eraseEllipse(ellipse_left , ellipse_top , ellipse_right , ellipse_bottom);
	return;
}

void change_direction(char d){
	//writeDebugStreamLine("change_direction(%c)" , d);
	if(d == 'x') velocity[0] *= -1;
	if(d == 'y') velocity[1] *= -1;
	return;
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
	eraseEllipse(ellipse_left , ellipse_top , ellipse_right , ellipse_bottom);
	ellipse_center[0] += velocity[0];
	ellipse_center[1] += velocity[1];
	bool touching_wall[] = {(ellipse_left <= screen_x_bounds[0]) || (ellipse_right >= screen_x_bounds[1]) ,
													 (ellipse_bottom <= screen_y_bounds[0]) || (ellipse_top >= screen_y_bounds[1])};
	if(touching_wall[0] == true){
		change_direction('x');
	}
	if(touching_wall[1] == true){
		change_direction('y');
	}
	return;
}

task debugging(){
	while(true){
		writeDebugStreamLine("ellipse_center_x:%d ellipse_center_y:%d" , ellipse_center[0] , ellipse_center[1]);
		writeDebugStreamLine("velocity_x:%d velocity_y:%d" , velocity[0] , velocity[1]);
		writeDebugStreamLine("");
		sleep(frame_delay);
	}
}


task main(){
	clearDebugStream();
	startTask(debugging);
	show_ellipse();
	while(true){
		update_ellipse_loc();
	}
}
