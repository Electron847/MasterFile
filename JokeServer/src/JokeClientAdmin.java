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

import java.io.*;       // imports in previously-authored java files that attain and surrender information to processes
import java.net.*;      //imports in previously-authored java files that allow interaction with other systems through networking processes

public class JokeClientAdmin {              //class that delegates what data structure/set is transmitting to client
    public static void main(String args[]) throws IOException {     //main() declared
        String server;  //String class object variable 'server' initialized
        if (args.length < 1) server = "localhost";  //if argument length is less than 1 variable 'server' is set to 'localhost'
        else server = args[0];                      //else variable 'server' set to argument array index 0
        System.out.println("Admin now speaking with server : " + server + " at Port 50000");    //print message when server is identified to interact with
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));      //BufferedReader class object variable 'in' set to new BufferedRead object running function with new input streamreader passed system.in

        try {
            String modeSwitch;      //String class object variable 'modeSwitch' initialized

            do {
                System.out.println("Type in mode letter and press enter: ");    //asks user for an input option
                System.out.println("Press: j to enter Joke Mode - p to enter Proverb Mode - q to quit");    //prints out a statement outlining the commands to type
                System.out.flush();     //flushes system.out stream
                modeSwitch = in.readLine();     //modeSwitch variable set to 'in' BufferedReader running readline function
                if (modeSwitch.indexOf("q") < 0) ;  //if modeSwitch
                sendModeToServer(modeSwitch, server);   //sendModeToServer function with parameters based on modeSwitch and server variables
            } while (modeSwitch.indexOf("quit") < 0);
            System.out.println("Request cancelled by user");    //prints out user request cancellation message
        } catch (IOException x) {       //catch input/output exception variable 'x'
            x.printStackTrace();        //traces path of input/output exception for debugging
        }
    }


    static void sendModeToServer(String modeSwitch, String server) {
        Socket sock;                //Socket class object variable 'sock' initialized
        PrintStream toServ;         //PrintStream class object variable 'toServ' initialized
        BufferedReader fromServ;    //BufferedReader class object variable initialized
        String stringFromServ;      //String class object variable 'stringFromServ' initialized

        try {
            sock = new Socket(server,50000);    //connect to clientAdminPort set up in clientAdminLoop started in JokeServer main()
            fromServ = new BufferedReader(new InputStreamReader(sock.getInputStream()));    //sets fromServ BufferedReader class object with parameters filled for input stream reader with parameter set to Socket class sock variable running get-input-stream function
            toServ = new PrintStream(sock.getOutputStream()); //sets toServ PrintStream class object to new instance with parameter set to Socket class 'sock' variable running get-output-stream method
            toServ.println(modeSwitch);     //prints a stream of PrintStream object variable toServ running println function
            toServ.flush();         //flushes any leftoer bits out of the toServ print stream
            stringFromServ = fromServ.readLine();   //String object variable stringFromServ set to BufferedReader object variable fromServ running readline function
            if (stringFromServ != null) System.out.println(stringFromServ);     //if stringFromServ is not equal to 'null'
            sock.close();

        } catch (IOException v) {       //catches input/output exceptions
            System.out.println("Error in socket");  //in the case of IOException prints specified error message
            v.printStackTrace();    //traces an exception to method/line causing system error
        }

    }
}

