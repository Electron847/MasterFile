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



import java.io.*; // imports in previously-authored java files that attain and surrender information to processes
import java.net.*; //imports in previously-authored java files that allow interaction with other systems through networking processes
import java.util.*; //imports in previously-authored java utility files

public class JokeClient {
    private static String clientName;   //initializes String class object variable clientName
    private static int stateOfModes;      //int stateOfModes created
    final String clientID = UUID.randomUUID().toString();   //initialize a String variable 'clientID' for attempt to store state on secondServerName
    private static boolean[] jokeModeState;          //boolean array class object variable 'jokeModeState' initialized
    private static boolean[] proverbModeState;       //boolean array class object variable 'proverbModeState' initialized
    private static int jokeIndex;               //int variable 'jokeIndex' initialized
    private static int proverbIndex;            //int variable 'proverbIndex; initialized
    private static String serverName;       //initialize String object variable 'serverName'

    public static void main(String args[]) {
        //String serverName;          //String type object 'serverName' variable declared
        if (args.length < 1) serverName = "localhost";      //serverName assigned 'localhost' if args.length < 1
        else serverName = args[0];                          //otherwise serverName assigned the args[0] expression

        System.out.println("Utilizing server : " + serverName + "@ Port: 48000");   //Tells client user which Port theyre using on the JokeServer
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));   //assigns BufferedReader object variable 'in' to function call of InputStreamReader performing System function call running .in method

        jokeIndex = 0;                  //sets jokeIndex int variable to 0
        proverbIndex = 0;               //sets proverbIndex int variable to 0
        stateOfModes = 0;               //sets stateOfModes int variable to 0
        jokeModeState = new boolean[4];         //initates jokeModeState boolean array to 4 indexes one for each joke
        proverbModeState = new boolean[4];      //initates proverbModeState boolean array to 4 indexes one for each proverb

        try {
            System.out.println("Please enter your name (quit) to end : ");  //asks user for their name
            System.out.flush();         //flushes system of any extra bits in println function
            clientName = in.readLine();     //assigns clientName variable to BufferedReader
            String clientID = UUID.randomUUID().toString();     //assigns a UUID to this clientName variable - I have not yet used it in a request by the client
            String trigger;     //initialize String object variable 'trigger' for use below
            System.out.println("clientID for " + clientName + " is " + clientID);       //prints out startup message of clientName and it's UUID
            do {
                System.out.println("Hello " + clientName + " - Press Enter for a Joke/Proverb or type 'q' and press Enter to quit \n");   //tells use to hit Enter key for joke/proverb
                System.out.flush();                             //flushes System.out of any leftover bits
                trigger = in.readLine();                        //trigger set equal to BufferedReader class object variable 'in' performing readline function
                if(trigger.equals("q"))                         //checks to see if the 'q' quit trigger was entered
                    System.out.println("system commanded to quit"); //print message when 'q' is Entered
                else {
                    getJokeOrProverb(trigger); }             //if trigger sent was not the quit command getJokeOrPrverb with parameter 'trigger'
            } while (trigger.indexOf("q") < 0);
        } catch (IOException x) {                       //catches input/output exception
            x.printStackTrace();                //traces exception error through system for debugging
        }
    }

    static int request4Joke()       //sets environment for randomly choosing jokes based on those available
                                    //when all jokes have been selected and transmitted resets joke indexes to 0 for false
    {
        List<Integer> indexes = new ArrayList<Integer>();   //initializes List class object with Integers in its parameter to variable 'indexes' and sets equal to a new ArrayList
        for(int index = 0; index < jokeModeState.length; index++) //start index at 0, if the index is less than the length of the arraylist perform bracket tasks and increment index by 1
        {
            if(!jokeModeState[index])               //if the jokeModeState array boolean value at index number specified in iteration is false
                indexes.add(index);                 // add the index to the List object variable 'indexes'
        }
        if(indexes.isEmpty())                       //if the List class object variable 'indexes' is empty
        {
            Arrays.fill(jokeModeState, false);  //fill the new array list with the jokeModeState boolean array values set to false
            return request4Joke();                  //return to the top of the function
        }
        Random generatorInt = new Random();     //initializes a new random integer generator to variable 'generatorInt'
        int instance = generatorInt.nextInt(indexes.size());    //instance int variable set to generatorInt which is a new Random() function running the nextInt function based on parameter set to size of the indexes array list
        int request = indexes.get(instance);        //request int variable set to indexes array list variable running the get function on the previous line's instance int variable
        jokeModeState[request] = true;          //jokeModeState boolean array at the array index specified by the 'request' int variable is set to true
        return request;     //returns the request to leave the function
    }

    //static int LoopThroughJokeRequests()      //i was planning for this function to need to be created however, the tasks within the brackets function perfectly without any function call needed
    {
        int jokeTold;   //int variable 'jokeTold' created
        if(jokeIndex > jokeModeState.length)    //if jokeIndex is greater than jokeModeState array length
        {
            jokeIndex = 0;      //jokeIndex equals 0
            Arrays.fill(jokeModeState,false);   //fill arrays with the boolean array 'jokeModeState' with all index values set to false
        }
        jokeTold = jokeIndex;   //jokeTold set to jokeIndex
        jokeModeState[jokeTold] = true; //index in jokeModeState corresponding to jokeTold is set to true
        jokeIndex += 1;             //jokeIndex incremented by one
        //return jokeTold;      //necessary with the creation of the LoopThroughJokeRequests() function, not necessary with it commented out
    }

    static int request4Proverb()            //sets environment for randomly choosing proverbs based on those available
                                            //when all proverbs have been selected and transmitted resets proverbs indexes to 0 for false
    {
        List<Integer> indexes = new ArrayList<>();  //initializes List class object with Integers in its parameter to variable 'indexes' and sets equal to a new ArrayList
        for(int index = 0; index < proverbModeState.length; index++)    //start index at 0, if the index is less than the length of the arraylist perform bracket tasks and increment index by 1
        {
            if(!proverbModeState[index])        //if the proverbModeState array boolean value at index number specified in iteration is false
                indexes.add(index);             // add the index to the List object variable 'indexes'
        }
        if(indexes.isEmpty())                   //if the List class object variable 'indexes' is empty
        {
            Arrays.fill(proverbModeState, false);   //fill the new array list with the proverbModeState boolean array values set to false
            return request4Proverb();                   //return to the top of the function
        }
        Random generatorInt = new Random();             //initializes a new random integer generator to variable 'generatorInt'
        int instance = generatorInt.nextInt(indexes.size());       //instance int variable set to generatorInt which is a new Random() function running the nextInt function based on parameter set to size of the indexes array list
        int request = indexes.get(instance);        //request int variable set to indexes array list variable running the get function on the previous line's instance int variable
        proverbModeState[request] = true;   //proverbModeState boolean array at the array index specified by the 'request' int variable is set to true
        return request;                 //returns the request to leave the function
    }
    //static int LoopThroughProverbRequests()       //i was planning for this function to need to be created however, the tasks within the brackets function perfectly without any function call needed
    {
        int proverbTold;        //int variable 'proverbTold' created
        if(proverbIndex > proverbModeState.length)  //if proverbIndex is greater than proverModeState array length
        {
            proverbIndex = 0;      //jokeIndex equals 0
            Arrays.fill(proverbModeState,false);    //fill arrays with the boolean array 'proverbModeState' with all index values set to false
        }
        proverbTold = proverbIndex;     //proverbTold set to proverbIndex
        proverbModeState[proverbTold] = true;   //index in proverbModeState corresponding to proverbTold is set to true
        proverbIndex += 1;      //proverbIndex is incremented by one
      //  return proverbTold;       //necessary with the creation of the LoopThroughProverbRequests() function, not necessary with it commented out
    }

    static int stateWritten(PrintStream toServ, BufferedReader fromServ) throws IOException //recovers state of server
    {
        toServ.println("");     //prints undetermined line of strings/character toServ (to JokeServer)
        int stateOfModes = Integer.parseInt(fromServ.readLine());   //sets stateOfModes int equal an integer parsing of the incoming argument fromServ(from JokeServer)
        return stateOfModes;        //
    }

    static void getJokeOrProverb(String trigger)    //function to create socket and communicate with JokeServer at Port 48000
    {
        Socket jokeClient;      //initializes Socket class object variable 'jokeClient'
        PrintStream toServ;     //initializes PrintStream class object variable 'toServ'
        BufferedReader fromServ;    //initializes BufferedReader class object variable 'fromServ'
        String stringFromServ;      //initializes String class object variable 'stringFromServ'
        try {
            jokeClient = new Socket(serverName, 48000);     //sets jokeClient Socket to new Socket class object open to serverName at Port 48000
            fromServ = new BufferedReader(new InputStreamReader(jokeClient.getInputStream()));  //sets fromServ variable equal to new BufferedReader class function with parameter as new input streamreader function whose parameter is filled by jokeClient socket running get-input-stream function
            toServ = new PrintStream(jokeClient.getOutputStream());         //
            int stateOfModes = stateWritten(toServ,fromServ);
            if(stateOfModes == 2)   //if stateOfModes is equal to 2
            {
                toServ.println("admin has closed connection");     //toServ
                jokeClient.close(); //closes socket opened by jokeClient
            }

            if (stateOfModes != 2) {        //if stateOfModes is not equal to 2 then enter statement tasks
                toServ.println((stateOfModes < 1) ? request4Proverb() : request4Joke()); //toServ gets sent either a request4Proverb or a request4Joke
            }
            toServ.flush(); //flushes the PrintStream class object variable 'toServ' of any leftover bits
            stringFromServ = fromServ.readLine();   //sets String class object variable 'stringFromServ' to BufferedReader object variable 'fromServ' performing readline function


            if(stringFromServ != null)  //if stringFromServ is not null perform tasks in brackets
            {
                System.out.println(stringFromServ); //prints joke or proverb coming from JokeServer
            }
            jokeClient.close(); //closes socket opened by jokeClient
        } catch (
                IOException x) {                    //catch input/output exceptions
            System.out.println("Error in socket connection");       //print message when socket connection error occurs
            x.printStackTrace();    //traces any error in socket connection through system
        }
    }
}

