/*--------------------------------------------------------
1. Seth Weber
2. Java version: 1.8
3. Exact command-line compilation instructions :
> javac MyWebServer.java

4. Exact instructions to run this program:
> java MyWebServer

5. Files needed to successfully run program.
 a. MyWebServer.java
 b. serverlog.txt
 c. http-streams.txt
 d. dog.txt
 e. cat.html
 f. checklist-mywebserver.html

6. Run localhost:2540 at FireFox browser line
----------------------------------------------------------*/
import java.net.*;  //uses java networking library
import java.io.*;   //include java input output library
import java.util.*; //include java utilities library

class serveWorker extends Thread {  //extends a class into a thread
    Socket connectedSocket;         //initiates a Socket class object variable 'connectedSocket'
    serveWorker (Socket clientSocket) {connectedSocket = clientSocket;}     //thread extension of the class 'serveWorker' is served the parameter 'clientSocket' which is taking the place of the 'connectedSocket' variable
    public void run()   //function that starts without being called and shares return data publicly
    {
        try             //beginning of try/catch block
        {
            PrintStream responseHandler = new PrintStream(connectedSocket.getOutputStream()); //Prints a stream of output through the socket connection for browser to interpret
            BufferedReader clientRequestReader =  new BufferedReader(new InputStreamReader(connectedSocket.getInputStream())); //BufferedReader gets input from the user through socket connection to browser
            String clientBrowserRequest = clientRequestReader.readLine(); //Turns user input into a String for handling requests made from browser to server
            String enteredRequest; //Initiates a String class object to identify the file being requested
            String extensionFormat = "";  //Initiates a String class object that holds an empty string so we may fill it with the content type of the browser request
            StringTokenizer headerSplitter = new StringTokenizer(clientBrowserRequest, " ");  //breaks the String class object that holds the browser request into String tokens denoted by the single space delimiter " "
            String methodToken = headerSplitter.nextToken(); //Assigns String class object variable to the '.nextToken' of the request which is the 'GET' or 'POST' HTTP method in the header
            if(methodToken.equals("GET")) //String class object variable above = 'GET'
            {
                enteredRequest = headerSplitter.nextToken(); //String class object variable 'enteredRequest' set to the next string token of 'headerSplitter' which is the filename in the HTTP header
              //  if (enteredRequest.equals("favicon.ico"))   //if the .nextToken() is a 'favicon.ico' enter if block
                //{
               //     System.out.println("favicon detected"); //print that favicon has been detected
               // }
                if(enteredRequest.contains("..")) //If a request is attempting access to system root directory
                {                                   //
                    throw new RuntimeException(); //throw out runtime exception so debugging from client side doesn't happen
                }                                   //
            }                                       //
            else //if the request string token is not 'GET'
            {
                enteredRequest = null; //we set 'nameOfFileRequested' to null for easier handling
            }
            if(enteredRequest == null) //if we've set nameOfFileRequested equal to 'null'
            {
                System.out.println("This is not an HTTP request"); //Print error message for debugging
                throw new RuntimeException();   //a runtime exception is needed to handle a possible refernce to a null pointer
            }
            else if(enteredRequest.endsWith(".txt")) //when the filename ends with extension '.txt'
            {
                extensionFormat = "text/plain"; //we will fill the 'typeOfContent' String variable initiated above to equal 'text/plain'
                deliverFileToBrowser(enteredRequest, responseHandler, extensionFormat); //Calls function that generates displayable information
            }
            else if(enteredRequest.endsWith(".html")) //when the filename ends with extension '.html'
            {
                extensionFormat = "text/html"; //we will fill the 'typeOfContent' String variable initiated above to equal 'text/html'
                deliverFileToBrowser(enteredRequest, responseHandler, extensionFormat); //Calls function that generates displayable information
            }
            else if(enteredRequest.contains("/cgi/addnums.fake-cgi"))
            {
                extensionFormat = "text/html"; // set content type
                addNums(enteredRequest, responseHandler, extensionFormat); // call addNums method
            }
            else if(enteredRequest.endsWith("/")) //when we're dealing with a directory path
            {
                extensionFormat = "text/html"; //we will fill the 'typeOfContent' String variable initiated above to equal 'text/html'
                accessDirectory(enteredRequest, responseHandler, extensionFormat);  //Generates the content as being a directory
            }
            else    //when we're dealing with a file having no directory or acknowledged file extension identifier
            {
                extensionFormat = "text/plain"; //Turn this content into a text display
                deliverFileToBrowser(enteredRequest, responseHandler, extensionFormat); //Call function to generate displayable text
            }
            connectedSocket.close();  //detaches socket connection plugged into the server socket
        } catch (IOException x) {x.printStackTrace();} //catches IOException and does a trace of the exception for easier debugging
    }
    public void deliverFileToBrowser(String filename, PrintStream browserDisplay, String extensionType) throws IOException //function to dynamically create html browser displays
    {
        if(filename.startsWith("/")) //if our filename begins with a '/' character
        {filename = filename.substring(1);} //we extract the filename beginning at position index 1, which means the leading '/' character is now removed
        InputStream fileStream = new FileInputStream(filename); //we initiate an InputStream class object var 'fileStream' to equal a FileInputStream object functioning on our 'filename' String variable
        File newFile = new File(filename); //we start a new File class object and give it the String object 'filename' which is the name of our file to display
        browserDisplay.print("HTTP/1.1 200 OK" + "Content-Length: " + newFile.length() + "Content-Type: " + extensionType + "\r\n\r\n"); //transmission of header info with parameters filled in
        System.out.println("Seth's MyWebServer now whipping up file: " + filename + " streaming extension type " + extensionType); //Prints message to server console regarding file being displayed
        byte[] streamData = new byte[50000];        //initiates a new array of bytes to hold the data stream coming from 'fileStream' InputStream above
        int byteCount = fileStream.read(streamData); //initiates an int variable 'byteCount' to the number of bytes 'streamData' is inputting to the 'fileStream' through the .read method
        browserDisplay.write(streamData, 0, byteCount);     //'browserDisplay' PrintStream object runs its .write method on the 'streamData' from index 0 through 'byteCount'
        browserDisplay.flush();                         //the PrintStream 'browserDisplay' variable is flushed of any remaining bits
        fileStream.close();                             //the InputStream 'fileStream' variable is closed and signifies an end to this call of 'deliverFileToBrowser'
    }
    public void accessDirectory(String directoryName, PrintStream browserDisplay, String stringArgument) throws IOException //function to create directories
    {
        String directoryServe = directoryName; //designate the String 'directoryName' var to a placeholder var
        BufferedWriter hyperLink = new BufferedWriter(new FileWriter("hyperLink.html"));       //create a 'hyperLink' object
        File firstInPath = new File("./" + directoryName + "/"); //designates root file in filepath
        File[] arrayOfFiles = firstInPath.listFiles(); //utilize java.oi.File.listFiles() method to attain the file list from the root path
        hyperLink.write("<html><head></head>"); // set up html page template
        hyperLink.write("<body link='yellow' vlink='lightblue'>");  //some html styling efforts
        hyperLink.write("<body bgcolor = 'black'>"); //little more styling effort
        hyperLink.write("<a href=\"" + "http://localhost:2540" + "/\">" + "Home/Root" + "</a>"); //write out an html formatted link for Home/Root directory access
        hyperLink.write("<br><br>"); //writes two new lines
        for(File index: arrayOfFiles) //iterate through each index/file in the arrayOfFiles
        {
            String thisFile = index.getName(); //performs .getName() on index position of file array and assigns the string value of the index to String object variable 'thisFile'
            if (thisFile.startsWith(".") || thisFile.startsWith("hyperLink.html")) //check if 'thisFile' is either the root directory or the the file being displayed to browser
            {continue;}                                                             //if it is continue on
            if (index.isFile()) //check if 'index' in 'arrayOfFiles' returns a boolean true value to the .isFile() function of the java.io library
            {hyperLink.write("<a href=\"" + thisFile + "\" >" + thisFile + "</a> <br>");} //.write a hyperlink to browser for 'thisFile' that has no subdirectory links
            if (index.isDirectory()) //check if 'index' in 'arrayOfFiles' returns boolean true value to the .isDirectory() function of the java.io library
            {hyperLink.write("<a href=\"" + thisFile + "/\">/" + thisFile + "</a> <br>");} //.write a hyperlink to the browser for 'thisFile' with subdirectories denoted by the '/' character
            hyperLink.flush();          //flushes BufferedWriter stream of any leftover bits so subsequent queries are correct
        }
        hyperLink.write("</body></html>");  // close html tags
        File directoryLinkName = new File("hyperLink.html"); //initiate new File object variable 'directoryLinkName' with an html pathname
        InputStream directoryStream = new FileInputStream("hyperLink.html"); //initiate InputStream object variable 'directoryStream' to a new FileInputStream object functioning on an html pathname
        browserDisplay.println("HTTP/1.1 200 OK" + "Content-Length: " + directoryLinkName.length() + "Content-Type: " + stringArgument + "\r\n\r\n"); //transmit HTTP header to the browser per request
        System.out.println("Seth's MyWebServer is now accessing directory: " + directoryServe);  //Console print statement to the server
        byte[] byteStream = new byte[50000];                              //line up a byte array variable, I set mine to a size of 50000 for flexibility and future use
        int directoryStreamLength = directoryStream.read(byteStream);     //investigate the length of of the byteStream
        browserDisplay.write(byteStream, 0, directoryStreamLength);   //calls Printstream object method .write on the byteStream
        hyperLink.close();                                                //closes the BufferedWriter
        browserDisplay.flush();                                           //Printstream variable 'browserDisplay' is flushed of leftover bits
        directoryStream.close();                                          //closes directoryStream InputStream after flush
        //tempDisplayFile.delete();  // delete the displayfile
    }
    public void addNums(String stringFromBrowser, PrintStream browserDisplay, String contentExt) throws MalformedURLException
    {
        browserDisplay.println("HTTP/1.1 200 OK Content-Type: " + contentExt + "\r\n\r\n"); //broadcast HTTP response to the GET
        browserDisplay.println("<html><title></title><body>");                                //frames up start of html page
        browserDisplay.println("<body bgcolor = '00335'>");                                 //
        Map<String,String> personNumbersPair = new TreeMap<>();                             //Initiates Map<K,V> interface variable 'personNumberPair' with parameters String, String for us to be able to input string pairs we want associated with each other
        URL addNumsPage = new URL("http:/" + stringFromBrowser);                      //creates URL with the http protocol added
        String queryString = addNumsPage.getQuery();                                        //returns string in the browser line past the '?' as the query
        String[] clientInputs = queryString.split("&");                              //initiates a string array where we hold our 'pair' index variables and received from the query string .split by the '&' symbol
        for(int index = 0; index < clientInputs.length; index++)                            //traverse the remaining string indexes of clientInputs
        {                                                                                   //
            int equalsSign = clientInputs[index].indexOf("=");                              //identify where equals signs are in the remaining query string
            personNumbersPair.put(clientInputs[index].substring(0, equalsSign), clientInputs[index].substring(equalsSign + 1)); //put the K,V values in the 'personNumberPair' Map with A being associated to B by the '=' symbol in remaining string query of clientInput indexes
        }
        try
        {
            int addition = Integer.parseInt(personNumbersPair.get("num1")) + Integer.parseInt(personNumbersPair.get("num2")); //int variable 'addition' set equal to an Integer.parseInt() of a personNumbersPair.get() on 'num1' and 'num2' strings indicated
            browserDisplay.println(String.format("<font size = 35><font color = 'turquoise'>%s </font><br>",personNumbersPair.get("person")));
            browserDisplay.println(String.format("<font size = 25><font color = 'green'>MyWebServer computed %s + %s </font><br>", personNumbersPair.get("num1"), personNumbersPair.get("num2")));
            browserDisplay.println(addition);  // print the output to the browser
            System.out.println(personNumbersPair.get("person") + " " + "wants to know this -> " + personNumbersPair.get("num1") + " + " + personNumbersPair.get("num2"));
        } catch (java.lang.NumberFormatException e) {browserDisplay.println("<font color='lightblue'><b>Either the form is not completely filled out<br>-OR-<br>There is an incorrect number input</b></font>");}
        browserDisplay.println("</body></html>");       //end PrintStream inside of html
        browserDisplay.flush();                         //flushes PrintStream of any leftover bits
    }
}
public class MyWebServer {  //MyWebServer class
    public static void main(String[] args) throws IOException { //main function of MyWebServer class
        int port = 2540; //Required port number
        int q_len = 10; //Queue length of clients
        ServerSocket serverSocket = new ServerSocket(port, q_len); //Initializing a server
        Socket socket;  //Initializing a socket to plug into the server
        System.out.println("Seth running MyWebServer at port number " + port + "."); //Initial message to server console
        while (!false) //infinite loop
        {
            socket = serverSocket.accept(); //server accepts socket plugged into it
            new serveWorker(socket).start(); //starts thread with new server worker in the socket that the server has accepted
        }
    }
}