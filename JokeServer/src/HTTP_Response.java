import java.io.File;
import java.io.FileInputStream;

public class HTTP_Response {

    HTTP_Request req;
    //final string response generated to client
    String response;
    //root path of server
    String root = "/Users/sethweber/Desktop/htmlcheatsheet/atlasFrame.html";

    public HTTP_Response(HTTP_Request request) {

        req = request;
        //open file named in the request
        File f = new File(root + req.filename);

        try {
            //to read this file
            //FileInputStream fis = new FileInputStream(f);
            response = "HTTP/1.1 200";
            response += "Server: Seth's Java Server/1.0 \r\n";
            response += "Content-Type: text/html \r\n";
            response += "Connection: close \r\n";
            response += "Content-Length: " + f.length() + " \r\n";
            response += "\r\n"; // blank line to append file data
            FileInputStream fis = new FileInputStream(f);
            int s;
            while ((s = fis.read()) != -1){
                response += (char) s;

            } fis.close();

        } catch (Exception e){}
    }
}
