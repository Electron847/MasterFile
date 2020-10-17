/* 2012-05-20 Version 2.0

Thanks John Reagan for this well-running code which repairs the original
obsolete code for Elliott's HostServer program. I've made a few additional
changes to John's code, so blame Elliott if something is not running.

-----------------------------------------------------------------------

Play with this code. Add your own comments to it before you turn it in.

-----------------------------------------------------------------------
NOTE: This is NOT a suggested implementation for your agent platform,
but rather a running example of something that might serve some of
your needs, or provide a way to start thinking about what YOU would like to do.
You may freely use this code as long as you improve it and write your own comments.

-----------------------------------------------------------------------

TO EXECUTE: 

1. Start the HostServer in some shell. >> java HostServer

1. start a web browser and point it to http://localhost:1565. Enter some text and press
the submit button to simulate a state-maintained conversation.

2. start a second web browser, also pointed to http://localhost:1565 and do the same. Note
that the two agents do not interfere with one another.

3. To suggest to an agent that it migrate, enter the string "migrate"
in the text box and submit. The agent will migrate to a new port, but keep its old state.

During migration, stop at each step and view the source of the web page to see how the
server informs the client where it will be going in this stateless environment.

-----------------------------------------------------------------------------------

COMMENTS:

This is a simple framework for hosting agents that can migrate from
one server and port, to another server and port. For the example, the
server is always localhost, but the code would work the same on
different, and multiple, hosts.

State is implemented simply as an integer that is incremented. This represents the state
of some arbitrary conversation.

The example uses a standard, default, HostListener port of 1565.

-----------------------------------------------------------------------------------

DESIGN OVERVIEW

Here is the high-level design, more or less:

HOST SERVER
  Runs on some machine
  Port counter is just a global integer incrememented after each assignment
  Loop:
    Accept connection with a request for hosting
    Spawn an Agent Looper/Listener with the new, unique, port

AGENT LOOPER/LISTENER
  Make an initial state, or accept an existing state if this is a migration
  Get an available port from this host server
  Set the port number back to the client which now knows IP address and port of its
         new home.
  Loop:
    Accept connections from web client(s)
    Spawn an agent worker, and pass it the state and the parent socket blocked in this loop
  
AGENT WORKER
  If normal interaction, just update the state, and pretend to play the animal game
  (Migration should be decided autonomously by the agent, but we instigate it here with client)
  If Migration:
    Select a new host
    Send server a request for hosting, along with its state
    Get back a new port where it is now already living in its next incarnation
    Send HTML FORM to web client pointing to the new host/port.
    Wake up and kill the Parent AgentLooper/Listener by closing the socket
    Die

WEB CLIENT
  Just a standard web browser pointing to http://localhost:1565 to start.

  -------------------------------------------------------------------------------*/


import java.io.*;
import java.net.*;
/**
 * HostServer Notes: This went pretty smoothly for me, although I did have to edit the HTML functions
 * to get an accurate content length so things would be compatible with browsers other than IE. I also modified
 * things to eliminate inaccurate state numbers based on fav.ico requests. If the string person wasnt found,
 * the requests was ignored
 */

/**
 * AgentWorker
 *
 * AgentWorker objects are created by AgentListeners and process requests made at the various
 * active ports occupied by agentlistener objects. They take a request and look for the string
 * migrate in that request(supplied from a get parameter via an html form). If migrate is found, 
 * the worker finds the next availabel port and switches teh client to it. 
 *
 * I made a small modification because my browser kept requesting fav.ico. So I verified that we receive
 * a person attribute before processing the request as valid(and incrementing agent state)
 *
 */
class AgentWorker extends Thread {

    Socket sock; //Socket object variable 'sock' created
    agentHolder parentAgentHolder; //agentHolder object variable 'parentAgentHolder' created
    int localPort; //integer object variable 'localPort' created
    AgentWorker (Socket s, int prt, agentHolder ah)
    {
        sock = s;
        localPort = prt;
        parentAgentHolder = ah;
    }
    public void run()   //automatically run below code when prompted
    {
        PrintStream out = null;
        BufferedReader in = null;
        String NewHost = "localhost";
        int NewHostMainPort = 1565; //port used
        String buf = "";    //String object variable 'buf' equals empty string
        int newPort;    //integer object 'newPort' created
        Socket clientSock;  //Socket object variable 'clientSock' created
        BufferedReader fromHostServer;  //BufferedReader object variable 'fromHostServer' created
        PrintStream toHostServer;   //PrintStream object variable 'toHostServer' created

        try     //enter try block
        {
            out = new PrintStream(sock.getOutputStream());  //print stream 'out' equals new PrintStream object pulling from 'sock'
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));  //buffered reader 'in' equals new buffered reader pulling from Socket 'sock'
            String inLine = in.readLine();  //String object variable 'inLine' equals buffered reader 'in' running readline()
            StringBuilder htmlString = new StringBuilder(); //StringBuilder object 'htmlString' equals new StringBuilder object
            System.out.println();
            System.out.println("Request line: " + inLine);  //print to console

            if(inLine.indexOf("migrate") > -1)
            {
                clientSock = new Socket(NewHost, NewHostMainPort);
                fromHostServer = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));    //'fromHostServer' equals buffered reader object pulling from clientSock
                toHostServer = new PrintStream(clientSock.getOutputStream());   //toHostServer equals printstream pulling data from clientSock
                toHostServer.println("Please host me. Send my port! [State=" + parentAgentHolder.agentState + "]"); //print statement sent to server console
                toHostServer.flush();   //flushes printstream
                for(;;)     //looks for port
                {
                    buf = fromHostServer.readLine();    //'buf' equals fromHostServer running readline function
                    if(buf.indexOf("[Port=") > -1)
                    {
                        break;
                    }
                }

                String tempbuf = buf.substring( buf.indexOf("[Port=")+6, buf.indexOf("]", buf.indexOf("[Port=")) ); //String 'tempbuf' equals formatted extraction of port number
                newPort = Integer.parseInt(tempbuf);    //'newPort' equals integer parsing of 'tempbuf'
                System.out.println("newPort is: " + newPort);   //print statement
                htmlString.append(AgentListener.sendHTMLheader(newPort, NewHost, inLine));  //apend AgentListener sending html header to htmlString
                htmlString.append("<h3>We are migrating to host " + newPort + "</h3> \n");  //append statement validating port migration to htmlString
                htmlString.append("<h3>View the source of this page to see how the client is informed of the new location.</h3> \n");   //append third header html field to htmlString
                htmlString.append(AgentListener.sendHTMLsubmit());  //append AgentListener sending html submission to htmlString
                System.out.println("Killing parent listening loop.");   //print statement
                ServerSocket ss = parentAgentHolder.sock;   //ServerSocket 'ss' equals parentAgentHolder implementing Socket 'sock'
                ss.close(); //close Socket
            } else if(inLine.indexOf("person") > -1)
            {
                parentAgentHolder.agentState++; //increment parentAgentHolder running agentState
                htmlString.append(AgentListener.sendHTMLheader(localPort, NewHost, inLine));    //append AgentListener sending html header to the htmlString
                htmlString.append("<h3>We are having a conversation with state   " + parentAgentHolder.agentState + "</h3>\n"); //append html inlined code to htmlString
                htmlString.append(AgentListener.sendHTMLsubmit());  //append agentlistenger implementing sendHTMLsubmit() function to htmlString

            } else {    //sets actions for invalid entry
                htmlString.append(AgentListener.sendHTMLheader(localPort, NewHost, inLine));
                htmlString.append("You have not entered a valid request!\n");
                htmlString.append(AgentListener.sendHTMLsubmit());
            }
            AgentListener.sendHTMLtoStream(htmlString.toString(), out); //AgentListener sends html stream to the print stream
            sock.close();   //close Socket
        } catch (IOException ioe) //exit try block
        {
            System.out.println(ioe);    //set catch action
        }
    }

}
/**
 * Basic agent holder object. Holds state info/resources
 * so we can track the agentState and pass it between ports
 */
class agentHolder   //class for holding state of agent
{
    ServerSocket sock;  //ServerSocket object 'sock' created
    int agentState; //'agentState' initialized
    agentHolder(ServerSocket s) { sock = s;}    //inner instatiation of class
}
/**
 * AgentListener objects watch individual ports and respond to requests
 * made upon them(in this scenario from a standard web browser); Craeted 
 * by the hostserver when a new request is made to 1565
 *
 */
class AgentListener extends Thread //extension thread of AgentListener class
{
    Socket sock;    //Socket 'sock' initialized
    int localPort;  //localPort initialized
    AgentListener(Socket As, int prt) //instantiation of AgentListener thread extension
    {
        sock = As;  //'sock' equals Socket parameter
        localPort = prt;    //localPort equals prt parameter
    }
    int agentState = 0; //begin 'agentState' to 0

    public void run()   //automatically run below code when prompted
    {
        BufferedReader in = null;   //BufferedReader 'in' set to null
        PrintStream out = null;     //PrintStream 'out' set to null
        String NewHost = "localhost";   //String object variable 'NewHost' equals 'localhost'
        System.out.println("In AgentListener Thread");  //print statement
        try     //enter try block
        {
            String buf; //string object variable 'buf' initialized
            out = new PrintStream(sock.getOutputStream());  //set print stream to the data coming from the Socket
            in =  new BufferedReader(new InputStreamReader(sock.getInputStream())); //set buffered reader to the input from the Socket
            buf = in.readLine();    //'buf' equals BufferedReader 'in' running .readline()
            if(buf != null && buf.indexOf("[State=") > -1) //if 'buf' is not null and a state exists enter if statement
            {
                String tempbuf = buf.substring(buf.indexOf("[State=")+7, buf.indexOf("]", buf.indexOf("[State="))); //pulls state from the readline()
                agentState = Integer.parseInt(tempbuf); //agentState equals integer parsing of 'tempbuf'
                System.out.println("agentState is: " + agentState); //prints to server console the agentState

            }

            System.out.println(buf);    //sends 'buf' variable to println function
            StringBuilder htmlResponse = new StringBuilder();   //initialize StringBuilder object variable 'htmlResponse' equals a new StringBuilder object
            htmlResponse.append(sendHTMLheader(localPort, NewHost, buf));   //append the sending of the html header parameteried from the 'localPort' to the new port 'NewHost' to the htmlResponse StringBuilder
            htmlResponse.append("Now in Agent Looper starting Agent Listening Loop\n<br />\n"); //append this line to the htmlResponse StringBuilder
            htmlResponse.append("[Port="+localPort+"]<br/>\n"); //append this statement to the htmlResponse StringBuilder
            htmlResponse.append(sendHTMLsubmit());  //append the submitted html function to the htmlResponse StringBuilder
            sendHTMLtoStream(htmlResponse.toString(), out); //sendHTMLtoStream function parameterized to the string object response and the PrintStream object 'out'
            ServerSocket servsock = new ServerSocket(localPort,2);  //ServerSocket object 'servsock' equals new ServerSocket object parameterized to current port and backlog queue request of 2
            agentHolder agenthold = new agentHolder(servsock);  //agentHolder object variable 'agenthold' equals new agentHolder object paramterized to 'servsock'
            agenthold.agentState = agentState;  //agenthold implementing agentState function equals current agentState
            while(true)     //infinite loop looking for connections
            {
                sock = servsock.accept();   //Socket equals same location the ServerSocket is accepting connections
                System.out.println("Got a connection to agent at port " + localPort);   //print statement validating a new connection
                new AgentWorker(sock, localPort, agenthold).start();    //with connection received start new AgentWorker thread
            }

        } catch(IOException ioe) //exit try block
        {
            System.out.println("Either connection failed, or just killed listener loop for agent at port " + localPort);
            System.out.println(ioe);    //establishes input output exception handling
        }
    }

    static String sendHTMLheader(int localPort, String NewHost, String inLine)  //for HTTP request
    {
        StringBuilder htmlString = new StringBuilder();     //initialize a stringbuilder for html string
        htmlString.append("<html><head> </head><body>\n");  //intialize html form
        htmlString.append("<h2>This is for submission to PORT " + localPort + " on " + NewHost + "</h2>\n");    //print statement in header field two of html form
        htmlString.append("<h3>You sent: "+ inLine + "</h3>");  //string from function parameter
        htmlString.append("\n<form method=\"GET\" action=\"http://" + NewHost +":" + localPort + "\">\n");  //send request prompt in browser for a new host at the newly incrememnted port
        htmlString.append("Enter text or <i>migrate</i>:"); //appends entered text to the migrated port
        htmlString.append("\n<input type=\"text\" name=\"person\" size=\"20\" value=\"YourTextInput\" /> <p>\n");   //input form
        return htmlString.toString();   //return statement
    }
    static String sendHTMLsubmit()  //finanlizes html
    {
        return "<input type=\"submit\" value=\"Submit\"" + "</p>\n</form></body></html>\n";
    }
    static void sendHTMLtoStream(String html, PrintStream out)  //function for HTTP response
    {

        out.println("HTTP/1.1 200 OK");                     //HTTP header fields
        out.println("Content-Length: " + html.length());
        out.println("Content-Type: text/html");
        out.println("");
        out.println(html);
    }

}
/**
 *
 * main hostserver class. this listens on port 1565 for requests. at each request,
 * increment NextPort and start a new listener on it. Assumes that all ports >3000
 * are free.
 */
public class HostServer //class name
{
    public static int NextPort = 3000;  //start the migration at this port
    public static void main(String[] a) throws IOException //main function with IO exception thrown
    {
        int q_len = 6;  //queue length for incoming requests
        int port = 1565;    //server port
        Socket sock;    //Socket initialized
        ServerSocket servsock = new ServerSocket(port, q_len);  //ServerSocket initialied
        System.out.println("Master receiver started at port 1565.");    //print statement
        System.out.println("Connect from 1 to 3 browsers using \"http:\\\\localhost:1565\"\n"); //print statement
        while(true)     //infinite loop
        {
            NextPort = NextPort + 1;    //incrememnt port at every iteration through the infinite loop
            sock = servsock.accept();   //start our Socket object at same port ServerSocket object is set to
            System.out.println("Starting AgentListener at port " + NextPort);   //print statement
            new AgentListener(sock, NextPort).start();  //start new AgentListener class at incremented port number
        }

    }
}