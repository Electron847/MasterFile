public class HTTP_Request {
    //create constructor that accepts a string argument

    String filename;
    public HTTP_Request(String request) {
        //get first line of request containing http GET
        //filename entry
        String lines[] = request.split("\n");
        //split first line by spaces and select second index element
        //should hold filename
        filename = lines[0].split(" ")[1];
    }
}
