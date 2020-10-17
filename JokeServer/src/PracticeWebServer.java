/*--------------------------------------------------------

1. Seth Weber

2. Java version used, if not the official version for the class:

1.8

3. Precise command-line compilation examples / instructions:
> javac MyWebServer.java

4. Precise examples / instructions to run this program:
> java MyWebServer

5. List of files needed for running the program.
 a. MyWebServer.java
 b. http-streams.txt
 c. serverlog.txt
 d. checklist-mywebserver.html

5. Notes
----------------------------------------------------------*/

import java.net.*;
import java.io.*;
import java.util.*;

class WebServerWorker extends Thread {
    Socket connectedSocket;
    WebServerWorker (Socket clientSocket) {connectedSocket = clientSocket;}
    public void run()
    {
        try
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
                if (enteredRequest.equals("favicon.ico"))
                {
                    System.out.println("favicon detected");
                }
                else if(enteredRequest.contains("..")) //If a request is attempting access to system root directory
                {
                    throw new RuntimeException(); //throw out runtime exception so debugging from client side doesn't happen
                }
            }
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

    public void deliverFileToBrowser(String filename, PrintStream browserDisplay, String typeOfContent) throws IOException //function to dynamically create html browser displays
    {
        if(filename.startsWith("/")) //if our filename begins with a '/' character
        {
            filename = filename.substring(1); //we extract the filename beginning at position index 1, which means the leading '/' character is now removed
        }
        InputStream fileStream = new FileInputStream(filename); //we initiate an InputStream class object var 'fileStream' to equal a FileInputStream object functioning on our 'filename' String variable
        File newFile = new File(filename); //we start a new File class object and give it the String object 'filename' which is the name of our file to display
        browserDisplay.print("HTTP/1.1 200 OK" + "Content-Length: " + newFile.length() + "Content-Type: " + typeOfContent + "\r\n\r\n"); //transmission of header info with parameters filled in
        System.out.println("Seth's MyWebServer is now serving file: " + filename + " streaming content type " + typeOfContent); //Prints message to server console regarding file being displayed
        byte[] streamData = new byte[50000];        //initiates a new array of bytes to hold the data stream coming from 'fileStream' InputStream above
        int byteCount = fileStream.read(streamData); //initiates an int variable 'byteCount' to the number of bytes 'streamData' is inputting to the 'fileStream' through the .read method
        browserDisplay.write(streamData, 0, byteCount);     //'browserDisplay' PrintStream object runs its .write method on the 'streamData' from index 0 through 'byteCount'
        browserDisplay.flush();                         //the PrintStream 'browserDisplay' variable is flushed of any remaining bits
        fileStream.close();                             //the InputStream 'fileStream' variable is closed and signifies an end to this call of 'deliverFileToBrowser'
    }

    public void addNums(String stringFromBrowser, PrintStream browserDisplay, String typeOfContent) throws UnsupportedEncodingException, MalformedURLException
    {
        browserDisplay.println("HTTP/1.1 200 OK Content-Type: " + typeOfContent + "\r\n\r\n");  // header info
        browserDisplay.println("<html><head></head><body>"); // html
        browserDisplay.println("<body bgcolor = '00015'>"); // background color of black
        Map<String,String> qCouples = new LinkedHashMap<>();  // map to hold string couples
        URL url = new URL("http:/" + stringFromBrowser); // create new URL
        String qry = url.getQuery();  // grabs the query
        String[] pairs = qry.split("&");  // splits them where the & is
        int indexOfEqualSign;
        for(int i = 0; i < pairs.length; i++)
        {
            indexOfEqualSign = pairs[i].indexOf("=");  // find the index of the equals sign
            qCouples.put(URLDecoder.decode(pairs[i].substring(0, indexOfEqualSign), "UTF-8"), URLDecoder.decode(pairs[i].substring(indexOfEqualSign + 1), "UTF-8")); // put them in qcouples
        }
        try
        {
            int sum = Integer.parseInt(qCouples.get("num1")) + Integer.parseInt(qCouples.get("num2"));  // get the users numbers and add them
            String result = String.format("<font size =50><font color = #3399FF>%s, the sum of %s + %s = </font><font color='pink'><b>%d</b></font></font>\n", qCouples.get("person"), qCouples.get("num1"), qCouples.get("num2"),sum);  // format the output
            browserDisplay.println(result);  // print the output to the browser
            System.out.println(qCouples.get("person") + " " + "requested " + qCouples.get("num1") + "+" + qCouples.get("num2"));  // print info to the server log
        } catch (java.lang.NumberFormatException e)
        {
            browserDisplay.println("<font color=#FF0000><b>One of the inputs is not a number or is too big!</b></font>");  // error checking
        }
        browserDisplay.println("</body></html>"); // close html tags
        browserDisplay.flush(); // flushes any leftover bits in stream
    }

    public void accessDirectory(String directoryName, PrintStream browserDisplay, String typeOfContent) throws IOException //function to create directories
    {
        String directory2 = directoryName; //designate the String 'directoryName' var to a placeholder var
        BufferedWriter hyperLink = new BufferedWriter(new FileWriter("hyperLink.html")); //  new file to write
        File firstFile = new File("./" + directoryName + "/");  // first directory
        File[] filesInDirectory = firstFile.listFiles();  // grab the list of files
        hyperLink.write("<html><head></head>"); // write some html
        hyperLink.write("<body link='yellow'");  //
        hyperLink.write("<body bgcolor = 'red'>"); //
        hyperLink.write("<font size=80><font color = 'red'> Directory: " + directoryName + "</font></font>" + "<br>");  // display what directory the page is showing
        hyperLink.write("<a href=\"" + "http://localhost:2540" + "/\">" + "To Home/Root" + "</a>"); //
        if(directoryName.endsWith("/") && !directoryName.equals("/"))
        {  // check to see if we need to display the up one button
            int reversePage;
            String[]  substrings = directoryName.split("/");
            reversePage = substrings[1].length() + 1;  // figure out much to cut the directory by if user does want to go up
            directoryName = directoryName.substring(0, directoryName.length() - reversePage - 1);  // cut the current directory by one level
            hyperLink.write("&nbsp;" + "&nbsp;" + "&nbsp;" + "<a href=\"" + "http://localhost:2540" + directoryName + "\">" + "To Previous" + "</a>"); //
        }
        hyperLink.write("<br><br>"); //writes html 'break' tags in order to place directory access hyperlinks between them
        for(File f: filesInDirectory)
        {  // for each file
            String fileName = f.getName();  // get the name of the file, put it in fileName
            if (fileName.startsWith("."))
            {
                continue;
            }
            if (fileName.startsWith("hyperLink.html"))
            {  //the file being viewed
                continue;  // we don't need to make a hyperlink for the temporary display page
            }
            if (f.isDirectory())
            { // if it is a directory
                hyperLink.write("<a href=\"" + fileName + "/\">/" + fileName + "</a> <br>");  // create a clickable hyperlink for it
            }
            if (f.isFile())
            { // if it is a file
                hyperLink.write("<a href=\"" + fileName + "\" >" + fileName + "</a> <br>"); // create a clickable hyperlink for it
            }
            hyperLink.flush(); // flush
        }
        hyperLink.write("</body></html>");  // close html tags
        File tempDisplayFile = new File("hyperLink.html");
        InputStream stream = new FileInputStream("hyperLink.html");
        browserDisplay.println("HTTP/1.1 200 OK" + "Content-Length: " + tempDisplayFile.length() + "Content-Type: "  + typeOfContent + "\r\n\r\n");  // send appropriate header
        System.out.println("Seth's MyWebServer now serving directory: " + directory2); // server log
        byte[] displayFileBytes = new byte[10000]; // array to hold display file bytes. 10000 bytes can hold a little less than 10KB
        int numberOfBytes = stream.read(displayFileBytes);  // get number of bytes
        browserDisplay.write(displayFileBytes, 0, numberOfBytes);  // write the display file bytes to browser
        hyperLink.close(); // close buffered reader
        browserDisplay.flush(); // flush
        stream.close(); // close to remove resource leak
        tempDisplayFile.delete();  // delete the displayfile
    }
}
public class PracticeWebServer {
    public static void main(String[] args) throws IOException {
        int portUsed = 2540; //Required port number
        int q_len = 10; //Queue length of clients
        ServerSocket serverSocket = new ServerSocket(portUsed, q_len); //Initializing a server
        Socket socket;  //Initializing a socket to plug into the server
        System.out.println("Seth running Practice MyWebServer at port number " + portUsed + "."); //Initial message to server console
        while (!false)
        {
            socket = serverSocket.accept(); //server accepts socket plugged into it
            new WebServerWorker(socket).start(); //starts thread with new server worker in the socket that the server has accepted
        }
    }
}