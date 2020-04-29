#pragma config(StandardModel, "EV3_REMBOT")
task main()
{
int X = rand() % 175;
int Y = rand() % 123 + 30;
int vx = rand()%7-3;
int vy = rand()%7-3;
int p=(10*sqrt((vx*vx)+(vy*vy)));
int aenc=0;
int benc=0;
float dista=0;
float distb=0;

drawEllipse(X,Y,X+5,Y-5);

sleep(1000);
while (1){

setMotorSync(leftMotor,rightMotor,0,p);
sleep(100);
eraseEllipse(X,Y,X+5,Y-5);
if(X>=174){
vx=vx*-1;
	setMotorSync(leftMotor,rightMotor,50,90);
	sleep(800);
	aenc=getMotorEncoder(leftMotor);
benc=getMotorEncoder(rightMotor);
dista=(aenc/360)*176;
distb=(benc/360)*176;
	displayTextLine(13,"Distance between bounces");
displayTextLine(14,"LeftMotor: %d mm", dista);
displayTextLine(15,"RightMotor: %d mm", distb);
resetMotorEncoder(leftMotor);
resetMotorEncoder(rightMotor);
}
if(X<=0){
vx=vx*-1;
	setMotorSync(leftMotor,rightMotor,-50,90);
	sleep(800);
	aenc=getMotorEncoder(leftMotor);
benc=getMotorEncoder(rightMotor);
dista=(aenc/360)*176;
distb=(benc/360)*176;
	displayTextLine(13,"Distance between bounces");
displayTextLine(14,"LeftMotor: %d mm", dista);
displayTextLine(15,"RightMotor: %d mm", distb);
resetMotorEncoder(leftMotor);
resetMotorEncoder(rightMotor);
}
if(Y>=123){
	vy=vy*-1;
	p=p*-1;
	aenc=getMotorEncoder(leftMotor);
benc=getMotorEncoder(rightMotor);
dista=(aenc/360)*176;
distb=(benc/360)*176;
		displayTextLine(13,"Distance between bounces");
displayTextLine(14,"LeftMotor: %d mm", dista);
displayTextLine(15,"RightMotor: %d mm", distb);
resetMotorEncoder(leftMotor);
resetMotorEncoder(rightMotor);
}
if(Y<=30){
	vy=vy*-1;
	p=p*-1;
	aenc=getMotorEncoder(leftMotor);
benc=getMotorEncoder(rightMotor);
dista=(aenc/360)*176;
distb=(benc/360)*176;
	displayTextLine(13,"Distance between bounces");
displayTextLine(14,"LeftMotor: %d mm", dista);
displayTextLine(15,"RightMotor: %d mm", distb);
resetMotorEncoder(leftMotor);
resetMotorEncoder(rightMotor);
}
X=X+vx;
Y=Y+vy;
drawEllipse(X,Y,X+5,Y-5);
aenc=getMotorEncoder(leftMotor);
benc=getMotorEncoder(rightMotor);
dista=(aenc/360)*176;
distb=(benc/360)*176;

}
}
