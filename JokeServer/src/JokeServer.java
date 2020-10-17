/*--------------------------------------------------------

1. Seth Weber / 1-27-19:
2. Java version 1.8 used:
3. Precise command-line compilation examples / instructions:
    > javac JokeServer.java

4. Precise examples / instructions to run this program:

    e.g.:

    In separate shell windows:
    > java JokeServer
    > java JokeClient
    > java JokeClientAdmin

All acceptable commands are displayed on the various consoles.

This runs across machines, in which case you have to pass the IP address of
the server to the clients. For exmaple, if the server is running at
140.192.1.22 then you would type:

> java JokeClient 140.192.1.22
> java JokeClientAdmin 140.192.1.22

5. List of files needed for running the program.

    e.g.:
    a. checklist.html
    b. JokeServer.java
    c. JokeClient.java
    d. JokeClientAdmin.java

6. Notes:

    e.g.:

    I faked the random number generator. I have a bug that comes up once every
    ten runs or so. If the server hangs, just kill it and restart it. You do not
    have to restart the clients, they will find the server again when a request
    is made.
----------------------------------------------------------*/


import java.io.*;   //imports input output java lib files
import java.net.*;  //imports networking java lib files
import java.util.*; //imports utility java lib files

class clientWorker extends Thread {

    //Socket workerSocket;
    private static StatelyMode clientWorkerState;   //initiates a clientWorkerState variable of the StatelyMode class
    private static LinkedList<OnServer> listOfJokes;    //initiates a listOfJokes variable of LinkedList class passed the OnServer class as parameter
    private static LinkedList<OnServer> listOfProverbs; //initiates a listOfProverbs variable of LinkedList class passed the OnServer class as parameter
    private static final String j1 = "What's the best part about being a nihilist? Me";     //unchanged joke string set to variable j1
    private static final String j2 = "The shovel was a ground-breaking invention";          //unchanged joke string set to variable j2
    private static final String j3 = "Two chemists walk into a bar the first says 'I'll have h20' the second says 'I'll have h20 too'. The second dies.";   //unchanged joke string set to variable j3
    private static final String j4 = "How did the hipster chemist burn his hand? He picked up his beaker before it was cool";   //unchanged joke string set to variable j4
    private static final String p1 = "Discretion is the greater part of valor";     //unchanged proverb string set to variable p1
    private static final String p2 = "Don't worry if plan A fails, there are 25 more letters in the alphabet";  //unchanged proverb string set to variable p2
    private static final String p3 = "Life is short, smile while you have teeth";   //unchanged proverb string set to variable p3
    private static final String p4 = "The road to success is always under construction";    //unchanged proverb string set to variable p4
    private static final String quitMsg = "Shutting down";  //unchanged string shutdown message set to variable quitMsg

    Socket workerSocket;         //initializes workerSocket variable of the Socket class

    clientWorker(Socket s) {     //passes clientWorker thread Socket class argument 's'
        workerSocket = s;        //workerSocket assigned to argument 's' which is of Socket class

        listOfJokes = new LinkedList<OnServer>();           //sets listOfJokes to new LinkedList
        listOfJokes.add(new OnServer(j1, "JA"));        //fills my listOfJokes LinkedList with newly added indexes
        listOfJokes.add(new OnServer(j2, "JB"));
        listOfJokes.add(new OnServer(j3, "JC"));
        listOfJokes.add(new OnServer(j4, "JD"));
        listOfProverbs = new LinkedList<OnServer>();        //sets listOfProverbs to new LinkedList
        listOfProverbs.add(new OnServer(p1, "PA"));     //fills my listOfJokes LinkedList with newly added indexes
        listOfProverbs.add(new OnServer(p2, "PB"));
        listOfProverbs.add(new OnServer(p3, "PC"));
        listOfProverbs.add(new OnServer(p4, "PD"));
        clientWorkerState = StatelyMode.captureStatus();    //clientWorkerState set equal to StatelyMode class function running a captureStatus function
    }

    public void run() {
        PrintStream out = null;     //initializes Printstream class variable out to null;
        BufferedReader in = null;   //initializes BufferedReader class variable in to null;

        try {
            in = new BufferedReader(new InputStreamReader(workerSocket.getInputStream()));  //BufferedReader variable in set to new instance with parameter filled by a streamreader attaining the workerSocket input stream
            out = new PrintStream(workerSocket.getOutputStream());  //PrintStream variable out set to new instance with parameter filled by workerSocket's output stream
            try {
                String requestByClient = in.readLine();     //set String class variable 'requestByClient' to 'in' BufferedReader variable running a readline method
                if (requestByClient.equals("")) {
                    int status = clientWorkerState.retrieveStatus();    //if requestByClient exists int status variable is set to clientWorkerState variable in the StatelyMode object class running retrieveStatus function
                    out.println(status);                                //'out' variable of the PrintStream object class to runs println function with parameter filled by 'status' variable
                    System.out.println("Current Status: " + status + "\n"); //prints in JokeServer window current status of clientWorkerState
                }
                String jokeOrProverbRequest = in.readLine();        //jokeOrProverbRequest variable set to 'in' BufferedReader object running readline function
                System.out.println("client's request being processed: " + jokeOrProverbRequest);    //prints out to server that client is being processed
                fireOffServerData(jokeOrProverbRequest, out);   //run fireOffServerData function running parameters of jokeOrProverbRequest stream
                System.out.println("client's request is now processed");    //prints to server that clients request have been handled
            } catch (IOException x) {       //catches input/output exceptions
                System.out.println("Server reader error");  //prints out error message to user for debugging
            }
            workerSocket.close();       //closes socket created on behalf of the workerSocket variable
        } catch (IOException ioe) {     //catches input/output exception
            System.out.println(ioe);    //prints out error message based on system err ioe
        }
    }
    static void fireOffServerData(String jokeOrProverbRequest, PrintStream out) //handles transmission to JokeClient
    {
        int stateOfRequest = clientWorkerState.retrieveStatus();    //stateOfRequest equals clientWorkerState running a retriveStatus function within the StatelyMode class
        if(jokeOrProverbRequest.equals(""))     //if jokeOrProverbRequest equals an empty string
        {
            out.println("Server " + quitMsg);    //sets print stream to the server quit message
            //return;
        }
        else
        {
            int request = Integer.parseInt(jokeOrProverbRequest);   //sets int variable 'request' equal to an integer parsing function running on the jokeOrProverbRequest function
            if (request < 4)    //if request integer is less than 4 perform bracket tasks
            {
                OnServer wordString = null;     //set joke or proverb variable wordString to null
                wordString = (stateOfRequest < 1) ? listOfJokes.get(request) : listOfProverbs.get(request); //wordString is equal to listOfJokes.get(request) or listOfProverbs.get(request) depending on the truth or falsity of (stateOfRequest < 1)
                out.println(wordString.retrieveArguments());    //sets print stream to the println function running parameters of the wordString argument performing a retrieveArguments function
                System.out.println("Transmission Sent \n");     //prints out statement letting server know it's message was transmitted
                return;
            }
            System.out.println("Problem inside fireOffServerData() \n");    //otherwise a problem occurred inside function
        }
    }
}
    class OnServer  //class which manipulates data held on server
    {
        private String jokeProverbString;       //String variable 'jokeProverbString' created
        private String jokeOrProverbLabel;      //String variable 'jokeOrProverbLabel' created
        private boolean transmitted;        //boolean variable 'transmitted' created

        public OnServer(String argument, String ID) //instance of the class created with parameters of String argument and String ID
        {
            this.jokeProverbString = argument;  //sets current jokeProverbString to String expression 'argument'
            this.jokeOrProverbLabel = ID;       //sets current jokeOrProverbLabel to String expression 'ID'
            this.transmitted = false;           //sets current transmitted boolean variable to false
        }
        public String retrieveArguments()   //creates function to allow other functions to call on for retrieving argument passed to OnServer instance
        {
            transmitted = true;         //transmitted boolean variable set to true
            return this.toString();
        }
        @Override                   //overrides class operation with this function
        public String toString()    //toString function
        {
            String outResult = jokeProverbString + " " + "("+ jokeOrProverbLabel +")";     //
            return outResult;
        }
        public boolean transmissionSent()       //function to transmit boolean state change to second server
        {
            boolean transmission = transmitted;
            return transmission;
        }
    }


class clientAdminLoop implements Runnable {
    protected int clientAdminPort;
        public void run() { //begins execution of the Runnable class

            int queueAdminClient = 8;   //int variable queueAdminClient equal to 8
            clientAdminPort = 50000;    //sets port JokeClientAdmin will use
            Socket adminSocket;         //Socket class object variable 'adminSocket' created
            try {   //block that catches exceptions
                ServerSocket ServerSocket = new ServerSocket(clientAdminPort, queueAdminClient);    //ServerSocket class object variable 'ServerSocket' equal to new ServerSocket class running paramters of clientAdminPort and queueAdminClient
                System.out.println("Administrative Joke Server now online and listening for AdminClients"); //message letting server know administrative socket is connected
                while (!false){
                    adminSocket = ServerSocket.accept();   //connects adminSocket to ServerSocket
                    new clientAdminWorker(adminSocket).start(); //spawns a new worker for the client admin
                }
            } catch (IOException ioex){         //catch input output exception
                System.out.println(ioex);
            }
        }
    }

class clientAdminWorker extends Thread {        //extended worker bee thread for the client admin class
    private Socket adminSocket;                 //Socket class object variable 'adminSocket' created
    private String currentMode;                 //String class object variable 'currentMode' created
    clientAdminWorker(Socket adminSocket)       //instance of 'clientAdminWorker' running parameter of 'adminSocket'
    {this.adminSocket = adminSocket;}           //current 'adminSocket' set equal to 'adminsocket' parameter field
    public void run()                           //run statement
    {
        PrintStream out = null;                 //Printstream class object variable 'out' set to null
        BufferedReader in = null;               //BufferedReader class object variable 'in' set to null
        StatelyMode clientWorkerState = StatelyMode.captureStatus();    //StatelyMode class object variable 'clientWorkerState' set equal to StatelyMode class running captureStatus function
        //currentMode = "";
        try
        {
            in = new BufferedReader(new InputStreamReader(adminSocket.getInputStream()));   //'in' variable set to BufferedReader function running input stream reader function running adminSocket.getInputStream function
            out = new PrintStream(adminSocket.getOutputStream());   //'out' variable set to PrintStream with parameter adminSocket running get output stream function
            try
            {
                String trigger;         //String class object variable 'trigger' created
                trigger = in.readLine();    //trigger set equal to 'in' variable running readline function
                switch(trigger)         //switch statement with the parameter 'trigger'
                {
                    default: clientWorkerState.writeStatelyMode(0); //default case
                    case "j": clientWorkerState.writeStatelyMode(0); //case where admin types 'j'
                    break;
                    case "p": clientWorkerState.writeStatelyMode(1); //case where admin types 'p'
                    break;
                    case "q": clientWorkerState.writeStatelyMode(2); //case where admin types 'q'
                    break;
                }
                switch (trigger)        //another switch statement to print out current server mode
                {
                    case "j": currentMode = "joker"; break;     //when 'j' is typed set currentMode to 'joker'
                    case "p": currentMode = "philosopher"; break;   //when 'p' is typed set currentMode to 'philosopher'
                    case "q": currentMode = "quit"; break;      //when 'q' is typed set currentMode to 'quit'
                    default: currentMode = "joker";             //default is 'joker'
                }
                out.println("Administrator has changed program mode to " + currentMode);    //print message to administrator
                adminChange(trigger);       //run adminChange function with parameter 'trigger'

            }catch(IOException v)       //catches input output exception
            {
                System.out.println("Server input problem");
                v.printStackTrace();        //traces input output exception error for debugging
            }
        }catch(IOException ioex)        //catches input output exception
        {
            System.out.println(ioex);
        }
    }

    public void adminChange(String trigger)     //runs the adminChange function to pass the mode change
    {
        switch (trigger)        //switch statement with parameter 'trigger'
        {
            case "j": currentMode = "joker"; break;     //when 'j' is typed set currentMode to 'joker'
            case "p": currentMode = "philosopher"; break;   //when 'p' is typed set currentMode to 'philosopher'
            case "q": currentMode = "quit"; break;      //when 'q' is typed set currentMode to 'quit'
            default: currentMode = "joker";             //default is 'joker'
        }
    }
}

class StatelyMode   //embedded class to create clientWorkerState
{
    private int status;     //int variable 'status' created
    private static StatelyMode statusZeitgeist;     //StatelyMode variable 'statusZeitgeist' created
    private StatelyMode(){this.status = 0;}         //run instance of StatelyMode function and set it to current int status
    public static StatelyMode captureStatus()       //StatelyMode class function 'captureStatus' created
    {
        if(statusZeitgeist == null)                 //if statusZeitgeist equals null enter statement body
            statusZeitgeist = new StatelyMode();    //statusZeitgeist set equal to new instance of StatelyMode function
        return statusZeitgeist;                     //return the statusZeitgeist to the class
    }
    public void writeStatelyMode(int trigger)   //runs 'this' method on 'status' int variable to set status instance to trigger
    {
        this.status = trigger;                  //current status set equal to the 'trigger' int
    }
    public int retrieveStatus()                 //int retrieveStatus function created
    {
        int transmit = this.status;             //int variable 'transmit' set equal to current 'status'
        return transmit;                        //return the int variable 'transmit'
    }
}



class JokeServer {
    public static void main (String a[]) throws IOException {
        int queue_len = 8;  //length for the queue of client requests
        int clientPort = 48000; //port number listening for JokeClient's socket
        Socket jokeClient; //creates new socket for JokeClient to connect with JokeServer
        clientAdminLoop CAL = new clientAdminLoop(); //creates path for clientAdminLoop thread
        Thread CAL_thread = new Thread(CAL); //initiates new thread to a variable
        CAL_thread.start(); //starts thread that will wait for JokeClientAdmin communication to server
        ServerSocket clientServerSocket = new ServerSocket(clientPort, queue_len); //clientServerSocket is created out of a new ServerSocket class object with parameters as clientPort and queue_len
        System.out.println("This funky Joker Server is Firing up, ears to the ground at port 48000. \n");   //server startup message
        while (!false){         //run loop constantly
            jokeClient = clientServerSocket.accept();     //always accepts clients
            new clientWorker(jokeClient).start();   //spawns new clientWorker thread
        }
    }
}



