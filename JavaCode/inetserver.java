import java.io.*; // imports in previously-authored java files that attain and surrender information to processes 
import java.net.*; //imports in previously-authored java files that allow interaction with other systems through networking processes

class Worker extends Thread {  //class definition of the Worker thread spawned below in the main function
	Socket sock;              //local variable to Worker class of object
	Worker (Socket s) {sock = s;} //Constructer that takes assigns arg s to local variable sock
		
		public void run() {
			//get IO stream in and out of socket
			PrintStream out = null;
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(sock.getInputStream())); //looks for an incoming stream of information and assigns it to running process variable 'in'
				out = new PrintStream(sock.getOutputStream()); //looks for an outgoing stream of information and assigns it to running process variable 'out'
				
				try {
					String name;
					name = in.readLine();
					System.out.println("Looking up " + name);
					printRemoteAddress(name, out);
				}	catch (IOException x) {
					System.out.println("Server read error");
					x.printStackTrace();
				}
				sock.close(); //closes connection not server
			}catch (IOException ioe) {System.out.println(ioe);}
		}
		
		static void printRemoteAddress (String name, PrintStream out) {
			try {
				out.println("Looking up " + name + "...");
				InetAddress machine = InetAddress.getByName(name);
				out.println("Host name : " + machine.getHostName()); //prints this out to client request
				out.println("Host IP : " + toText (machine.getAddress()));
			}   catch(UnknownHostException ex) {
			    out.println("Failed in attempt to look up " + name);
			}
		}
		
		static String toText (byte ip []) {
			StringBuffer result = new StringBuffer ();
			for (int i = 0; i < ip.length; ++i) {
				if (i > 0) result.append (".");
				result.append (0xff & ip[i]);
			}
			return result.toString();
		}
	}

public class inetserver {
	public static void main (String a[]) throws IOException {
		int q_len = 6;
		int port = 1565;
		Socket sock;
		ServerSocket servsock = new ServerSocket (port, q_len);
		System.out.println("Seth Weber's iNet server 1.8 starting up, listening at Port 1565.\n");
		while (true) {
			sock = servsock.accept();
			new Worker(sock).start();
		}
	}
}