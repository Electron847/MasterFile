#pragma config(StandardModel, "EV3_REMBOT")
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

void HellowWorld()
{
	displayTextLine(1, "Hellow World!");
	sleep(10000);
}

task main()
{
	HellowWorld();
}
