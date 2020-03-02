import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

class socketThread extends Thread {
    Socket pluggedThread;
    socketThread (Socket clientEnd){
        pluggedThread = clientEnd;
    }
    public void run() {
        try {
            //System.out.println("this is the beginning");
            PrintStream responseHandler = new PrintStream(pluggedThread.getOutputStream());
            BufferedReader clientRequestReader =  new BufferedReader(new InputStreamReader(pluggedThread.getInputStream()));
            String clientBrowserRequest = clientRequestReader.readLine();
            String enteredRequest;
            String requestExtension = "";
            StringTokenizer headerSplitter = new StringTokenizer(clientBrowserRequest, " ");
            String getOrPost = headerSplitter.nextToken();
            if(getOrPost.equals("GET")) {
                enteredRequest = headerSplitter.nextToken();
                if(enteredRequest.equals("favicon.ico")){
                    System.out.println("favicon detected and handled");
                }
                if(enteredRequest.contains("..")){
                    throw new RuntimeException();
                }
            }

            pluggedThread.close();
        }
        catch (IOException x) {x.printStackTrace();}
    }
}


public class mainServer {
    public static void main(String[] args) throws IOException { //main function of MyWebServer class
        int port = 2540; //Required port number
        int q_len = 10; //Queue length of clients

        ServerSocket serverSocket = new ServerSocket(port, q_len); //Initializing a server
        Socket socket;  //Initializing a socket to plug into the server
        System.out.println("Seth running MyWebServer at port number " + port + "."); //Initial message to server console
        while (!false) //infinite loop
        {
            socket = serverSocket.accept(); //server accepts socket plugged into it
            new socketThread(socket).start(); //starts thread with new server worker in the socket that the server has accepted
        }
    }
}
