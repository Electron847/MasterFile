
import java.io.*;  // imports in previously-authored java files that attain and surrender information to processes
import java.net.*; //imports in previously-authored java files that allow interaction with other systems through networking processes


class Worker1 extends Thread {
    Socket sock;                    //class definition of the Worker thread spawned below in the main function
    Worker1(Socket s) {sock = s;}    //local variable to Worker class of object

    public void run() {
        PrintStream out = null;         //sets the PrintStream variable 'out' equal to null
        BufferedReader in = null;       //sets the BufferedReader variable 'in' equal to null

        try {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));  //looks for an incoming stream of information and assigns it to running process variable 'in'
            out = new PrintStream(sock.getOutputStream());      //looks for an outgoing stream of information and assigns it to running process variable 'out'
            try {
                String name;                                    //String object variable 'name' declared
                name = in.readLine();                           //assigns name to expression 'in' function performing readLine method
                System.out.println("Looking up " + name);
                printRemoteAddress(name, out);                  //calls printRemoteAddress function with parameters 'name' and 'out'

            }
            catch (IOException x){System.out.println("Server read error");
            x.printStackTrace();
                                 }
            sock.close(); //closes connection to the server but not server
        }
        catch(IOException ioe) {System.out.println(ioe);}   //catches input output exception
    }

    static void printRemoteAddress(String name,PrintStream out){
        try{ out.println("Looking up " + name + ". . .");
            InetAddress machine = InetAddress.getByName(name);          //InetAddress object variable 'machine' set equal to InetAddress method of getByName with paraameter filled
            out.println("Host name : " + machine.getHostName());        // InetAddress object variable 'machine' set equal to InetAddress method of getByName with paraameter filled
            out.println("Host IP address : " + toText(machine.getAddress())); // Print out toText function with parameter filled by InetAddress variable 'machine' manipulated by .getAddress method
        } catch (UnknownHostException ex) {
            out.println("This was a failed attempt to reach server and look up " + name);
        }
    }

    static String toText ( byte ip[]){
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < ip.length; ++i) {
            if (i > 0) result.append(".");
            result.append(0xff & ip[i]);
        }
        return result.toString();
    }
}

public class InetServer {
    public static void main(String a[]) throws IOException {
        int queueRequest_len = 8;                                          //has to do with number of requests to handle in queue
        int port = 50000;                                       //high port number
        Socket sock;                                            //Socket object from imported library
        ServerSocket servsock = new ServerSocket(port, queueRequest_len);  //ServerSocket object from imported library
        System.out.println ("Seth Weber's Inetserver 1.8 starting up, listening for client requests at port 50000.\n");
        while(!false){
            sock = servsock.accept(); //Socket variable taking on the ServerSocket method of accepting connection
            new Worker1(sock).start(); //Once connection accepted new Worker class object initiated
        }
    }
}