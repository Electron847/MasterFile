import java.io.*; // imports in previously-authored java files that attain and surrender information to processes
import java.net.*; //imports in previously-authored java files that allow interaction with other systems through networking processes

public class InetClient{
    public static void main (String args[]) {
        String serverName;          //String type object 'serverName' variable declared
        if (args.length < 1) serverName = "localhost";      //serverName assigned 'localhost' if args.length < 1
        else serverName = args[0];                          //otherwise serverName assigned the args[0] expression
        System.out.println("Seth Weber's inet client, running on Java 1.8");
        System.out.println("Utilizing server : " + serverName + "@ Port: 50000");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));   //assigns BufferedReader object variable 'in' to function call of InputStreamReader performing System function call running .in method
        try {
            String name;
            do
                {System.out.print("Enter a hostname or an IP address, (quit) to end: ");
                System.out.flush ();                        //flushes System.out... of any remaining bytes in the stream so no ambiguity
                name = in.readLine ();                      //assigns String variable 'name' to input stream performing readLine () method
                if (name.indexOf("just quit") < 0) getRemoteAddress(name, serverName);     //performs getRemoteAddress function with parameters filled (name, serverName)
                } while (name.indexOf("quit") < 0);             //System.out.println ("Cancelled by user request.");
        }
        catch(IOException x) {x.printStackTrace();}
    }


    static String toText (byte ip[]) {
        //IDE says this toText function is never used to I don't understand why it's here...yet
        StringBuffer result = new StringBuffer ();
        for (int i = 0; i < ip.length; ++ i) {
            if (i > 0) result.append (".");
            result.append (0xff & ip[i]);
        }
        return result.toString ();
    }
    static void getRemoteAddress (String name, String serverName){
        Socket sock;                    //Socket type object with variable name 'sock' declared
        BufferedReader fromServer;      //BufferedReader type object with variable name 'fromServer' declared
        PrintStream toServer;           //PrintStream type object with variable name 'toServer' declared
        String textFromServer;          //String type object with variable name 'textFromServer' declared

        try{
            sock = new Socket(serverName, 50000);           //sock variable set to new Socket class object with parameters filled (serverName, port: 50000)
            //Below declarations allow manipulation of the incoming and outgoing data streams
            fromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));  //fromServer variable set to new BufferedReader class object
            toServer = new PrintStream(sock.getOutputStream());                             //toServer variable set to new PrintStream class object
            toServer.println(name);                             //toServer function running .println method and parameter filled with 'name' which is either host name or IP
            toServer.flush();                                   //flushes stream of any remaining bytes
            for (int i = 1; i <=3; i++){
                textFromServer = fromServer.readLine();
                if (textFromServer != null) System.out.println(textFromServer); //if the textFromServer string is not 'null' run the System.out.println function
            }
            sock.close();       //closes the socket connection to the server
        } catch (IOException x) {
            System.out.println ("Socket error");        //Prints the error message if IOException is caught
            x.printStackTrace ();
        }
    }
}