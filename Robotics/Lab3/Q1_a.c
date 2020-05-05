#pragma config(StandardModel, "EV3_REMBOT")

int linenumber = 0;
string message = "";

task process_message(){
	while(true){
		linenumber %= 8;
		linenumber += 1;
		stringFormat(message , "%d squared is %d" , linenumber , pow(linenumber , 2));
		sleep(1000/1);
	}
}

task main(){
	startTask(process_message);

	while(true){};
}
