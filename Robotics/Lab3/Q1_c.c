#pragma config(StandardModel, "EV3_REMBOT")

int linenumber = 0;
string message = "";

int process_message_hertz = 1;
int manage_display_hertz = 3;
int debug_hertz = 5;

task process_message(){
	while(true){
		linenumber %= 8;
		linenumber += 1;
		stringFormat(message , "%d squared is %d" , linenumber , pow(linenumber , 2));

		sleep(1000 / process_message_hertz);
	}
}

task manage_display(){
	while(true){
		eraseDisplay();
		displayTextLine(linenumber , message , pow(linenumber , 2));

		sleep(1000 / manage_display_hertz);
	}
}
task Debug(){
		while(true){
		writeDebugStreamLine("linenumber = %d  |  message = %s" , linenumber , message);

		sleep(1000 / debug_hertz);
	};
}

task main(){
	clearDebugStream();

	startTask(process_message);
	startTask(manage_display);
	startTask(Debug);
	while(true){
	}

}
