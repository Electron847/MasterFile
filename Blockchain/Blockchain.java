/*
1. Seth Weber
2. Java version 1.8
3. Compilation Instructions:
    > run the provided blockScript.scpt file provided
4. Run Instructions  (REVIEW THESE PROCESS COORDINATION SCRIPTS)
   List of files needed for running the program
    - Blockchain.java
    - BlockchainLog.txt
    - BlockInput0.txt
    - BlockInput1.txt
    - BlockInput2.txt
5.Notes
    -

*/

//import sun.security.rsa.RSAPublicKeyImpl;
//import sun.security.provider.SHA;
import java.math.BigInteger;    //used to handle the RSA integer size
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;  //used to handle exceptions in the key process
import java.security.spec.RSAPublicKeySpec;         //used for generating an RSA key
import java.util.*;                                 //utilities library
import java.io.*;                                   //input/output library
import java.net.*;                                  //networking library
import java.util.concurrent.*;                      //concurrent utilities library
import java.security.*;                             //security library
import javax.xml.bind.*;                            //javax xml bind library
import javax.xml.bind.Unmarshaller;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;        //extension of the javax xml bind library with XmlElement
import javax.xml.bind.annotation.XmlRootElement;    //extension of the javax xml bind library with XmlRoot
import java.io.StringWriter;
import java.io.StringReader;


class PublicKeyWorker extends Thread {  //handle extension of publickeyserver runnable

    static PublicKey[] pubKeyArray = new PublicKey[3];  //initiates an array of PublicKey objects named 'pubKeyArray' set to array size of 3
    Socket sock;                        //initiate socket use
    PublicKey publicanKey;                //initiate PublicKey object variable 'publicKey'

    PublicKeyWorker (Socket s) {sock = s;}  //instantiates class with Socket variable 'sock' set to instantiated parameter 's'
    public void run(){                      //runs below code once instantiated thread is encountered
        try                                 //enter try block
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));   //BufferedReader object variable 'in' set equal to new inputstreamreader of data coming in from instantiated 'sock'
            String pubKey = in.readLine();  //String object variable 'pubKey' set to the BufferedReader 'in' method .readline of 'sock' inputstream
            String modulus = in.readLine(); //String object variable 'modulus' set to NEXT BufferedReader 'in' method .readline of 'sock' inputstream
            String publicExponent = in.readLine();  //String object variable 'publicExponent' set to NEXT BufferedReader 'in' method .readline of 'sock' inputstream
            String sPID = in.readLine();   //string object of our PID

            publicanKey = CreateRSAPublicKey(modulus,publicExponent); //publicanKey variable equals a CreateRSAPublicKey object parameterized to modulus and public exponent
            System.out.println("New RSAPublicKey = " + publicanKey);    //prints out RSA public keys for inspection

            int PID = Integer.parseInt(sPID);   //integer object variable 'PID' equals integer object running a .parseInt function parameterized to 'sPID'
            System.out.println(PID);            //print statement for double checking what PID we're using for inspection
            pubKeyArray[PID] = publicanKey;     //PublicKey array object variable 'pubKeyArray' equals 'publicankey'

            sock.close();   //Socket 'sock' closed
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException x){x.printStackTrace();} //exit try block and set catch conditions
    }

    public PublicKey CreateRSAPublicKey(String modulus, String publicExponent) throws NoSuchAlgorithmException, InvalidKeySpecException
    {   //creates a public RSA key from the
        PublicKey myPublicKey;  //initializes PublicKey object variable 'myPublicKey'

        byte[] modulusBytes = modulus.getBytes();           //byte array object variable 'modulusBytes' equals modulus string object invoking .getBytes()
        byte[] publicExponentBytes = publicExponent.getBytes(); //byte array object variable 'publicExponentBytes' equals publicExponent string object invoking .getBytes()
        BigInteger modulusBigInt = new BigInteger(modulusBytes);    //BigInteger object to handle the size of our bit lengths
        BigInteger publicExponentBigInt = new BigInteger(publicExponentBytes);  //same as above
        RSAPublicKeySpec rsaPublicKey= new RSAPublicKeySpec(modulusBigInt, publicExponentBigInt);   //RSAPublicKeySpec object variable 'rsaPublicKey' equals new object parameterized with modulusBigInt and publicExponentBigInt
        KeyFactory keyFact = KeyFactory.getInstance("RSA");
        myPublicKey = keyFact.generatePublic(rsaPublicKey);
        return myPublicKey;
    }
}

class KeyServer implements Runnable { //Kicks off a runnable class object 'KeyServer' thread
    public void run()   //run below code
    {
        int q_len = 6;  //queue length
        Socket sock;    //Socket variable 'sock' initialized
        System.out.println("Starting Key Server input thread using " + Integer.toString(Ports.KeyServerPort));  //print statement to check where our key server is
        try     //enter try block
        {
            ServerSocket serverSocketsock = new ServerSocket(Ports.KeyServerPort, q_len); //ServerSocket 'serverSocket' variable equal to a new ServerSocket set to port where KeyServerPort resides at q_len set
            while (true) //infinite loop
            {
                sock = serverSocketsock.accept();   //Socket 'sock' accepts new ServerSocket named 'serverSocket'
                new PublicKeyWorker (sock).start(); //new PublicKeyWorker thread extension is set to Socket 'sock' and started
            }
        }catch (IOException ioe) {System.out.println(ioe);} //exit try block and set catch conditions
    }
}

class UnverifiedBlockServer implements Runnable //kick of a runnable class object 'UnverifiedBlockServer' thread
{
    BlockingQueue<String> queue;    //initializes a BlockingQueue object of strings named 'queue'
    int PID;                        //initialize an integer variable 'PID'
    //KeyPair keyPair;

    UnverifiedBlockServer(BlockingQueue<String> queue, int PID) //inner instance to set parameters for iteslf
    {
        this.queue = queue; //the queue being used is this.queue
        this.PID = PID;     //the PID being used is this.PID
        //this.keyPair = keyPair;

    }

    class UnverifiedBlockWorker extends Thread { // Class definition
        Socket sock; // Class member, socket, local to Worker.
        UnverifiedBlockWorker (Socket s) {sock = s;} // Constructor, assign arg s to local sock
        public void run()
        {  //automatically run below code
            try //enter try block
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));   //BufferedRead object variable 'in' equals new bufferedreader parameterized to a new inputstreamreader parameterized to socket 'sock' .get inputstream method
                String data = in.readLine ();   //String object variable 'data' equals the first line being read from buffered reader 'in'
                System.out.println("Put in priority queue: " + data + "\n");    //print statement to make visible 'data'
                queue.put(data);    //the queue we are using gets 'data' put into it
                sock.close();   //Socket 'sock' is closed
            } catch (Exception x){x.printStackTrace();} //exit try block and set catch
        }
    }

    public void run()   //automatically run below code
    {
        int q_len = 6;  //the length of the queue for our port number requests per cycle
        Socket sock;    //Socket object variable 'sock' initialized
        System.out.println("Starting the Unverified Block Server input thread using " +
                Integer.toString(Ports.UnverifiedBlockServerPort));
        try //enter try block
        {
            ServerSocket servsock = new ServerSocket(Ports.UnverifiedBlockServerPort, q_len);   //ServerSocket object 'servsock' equals new ServerSocket parameterized to the
            while (true)
            {
                sock = servsock.accept(); // Got a new unverified block
                new UnverifiedBlockWorker(sock).start(); // So start a thread to process it.
            }
        }catch (IOException ioe) {System.out.println(ioe);} //exit try block and set catch conditions
    }
}

/* We have received unverified blocks into a thread-safe concurrent access queue. Just for the example, we retrieve them
in order according to their blockID. Normally we would retreive the block with the lowest time stamp first, or? This
is just an example of how to implement such a queue. It must be concurrent safe because two or more threads modifiy it
"at once," (mutiple worker threads to add to the queue, and consumer thread to remove from it).*/

class UnverifiedBlockConsumer implements Runnable   //kicks off runnable implementation of unverified block consumer thread
{
    BlockingQueue<String> queue;    //initialize a BlockingQueue object paramterized with strings to variable name 'queue'
    int PID;                        //integer object variable 'PID'
    UnverifiedBlockConsumer(BlockingQueue<String> queue, int PID)   //
    {
        this.queue = queue; //Constructor binds our priority queue to the local variable.
        this.PID = PID;     //constructor binds our PID to a local variable
    }

    public void run() //automatically run below code when inside this runnable
    {
        String data;    //String object variable 'data' initialized
        PrintStream toServer;   //PrintStream object variable 'toServer'
        Socket sock;        //Socket 'sock' initialized
        String newblockchain;
        String fakeVerifiedBlock;       //String object variable 'fakeVerifiedBlock'

        System.out.println("Starting the Unverified Block Priority Queue Consumer thread.\n");  //print statement to tell us our unverified block consumer thread is entering a run()
        try //enter try block
        {
            while(true) //infinite loop
            {
                data = queue.take(); // Will blocked-wait on empty queue
                System.out.println("Consumer got unverified: " + data); //print statement that shows what data is being taken out of the priority queue


                //unmarshall data here back into a BlockRecord blockRecord
                // blockRecord.gets()
                // use these to update setVerificaitonProcess()
                // update the previous hash + blockNum + seed
                // insert this new SHA hash into SHA hash string
                // sign this HASH string, update SignedSHA hash header slot

                // Ordinarily we would do real work here, based on the incoming data.
                int j; // Here we fake doing some work (That is, here we could cheat, so not ACTUAL work...)
                for(int i=0; i< 100; i++)
                { // put a limit on the fake work for this example
                    j = ThreadLocalRandom.current().nextInt(0,10);
                    try{Thread.sleep(500);}catch(Exception e){e.printStackTrace();}
                    if (j < 3) break; // <- how hard our fake work is; about 1.5 seconds.
                }

                //delete this fake work loop above
                //insert WorkA from instructions (given-delete main in workA and feed in data)

                System.out.println("data = " + data);

                if(Blockchain.blockchain.indexOf(data) < 0)         //if the index of Blockchain.blockchain.indexOf(data) is less than 0 enter block
                {
                    fakeVerifiedBlock = "[" + data + " verified by P" + PID + " at time "
                            + Integer.toString(ThreadLocalRandom.current().nextInt(100,1000)) + "]\n";
                    System.out.println(fakeVerifiedBlock);
                    String tempblockchain = fakeVerifiedBlock + Blockchain.blockchain; // add the verified block to the chain


                    //string tempBlockchain = data + Blockchain.blockchain;
                    //could also add blockrecord.toString() as well instead of data

                    //getVerifciaiton process on the block to be appended.
                    // if else block
                    // if (VP = 0 ) credit0 +=1; (repeat for 1 and 2)

                    for(int i=0; i < Blockchain.numProcesses; i++){ // send to each process in group, including us:
                        sock = new Socket(Blockchain.serverName, (Ports.BlockchainServerPortBase + i));
                        toServer = new PrintStream(sock.getOutputStream());
                        toServer.println(tempblockchain);   //print statement to server console 'tempblockchain'
                        toServer.flush();     //flush stream
                        sock.close();   //close Socket 'sock'
                    }
                }
                Thread.sleep(1500);         //waits for new blockchain to be posted before processing next block
            }
        }catch (Exception e) {System.out.println(e);}   //exit try block with catch statement and conditions
    }
}

// Incoming proposed replacement blockchains. Compare to existing. Replace if winner:



class BlockchainServer implements Runnable //runnable class instance
{
    public void run()   //automatically enters below code blocks
    {
        int q_len = 6;  //integer object 'q_len' set to 6 for amount of requests taken in per/time
        Socket sock;    //Socket object 'sock' initialized
        System.out.println("Starting the Blockchain server input thread using Port :" + Integer.toString(Ports.BlockchainServerPort));
        try //enter try block
        {
            ServerSocket servsock = new ServerSocket(Ports.BlockchainServerPort, q_len);    //SeverSocket variable 'servsock' equals new ServerSocket parameterized with blockchainserverport port number and q_len
            while (true)                //infinite loop to process below code
            {
                sock = servsock.accept();   //Socket 'sock' equals ServerSocket 'servsock' invoking .accept() function
                new BlockchainWorker (sock).start();    //new BlockchainWorker thread extension at Socket 'sock' invokes .start() function
            }
        }catch (IOException ioe) {System.out.println(ioe);} //exit try block and set catch fields
    }
}

class BlockchainWorker extends Thread
{ // Class definition
    Socket sock; // Class member, socket, local to Worker.
    BlockchainWorker (Socket s) {sock = s;} // Constructor, assign arg s to local sock
    public void run()   //automatically run below code
    {
        try //enter try block
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));   //bufferedreader 'in' equala a buffered reader fed with inpustreamreader of sock.getinputstream
            String data = "";           //empty string initiation
            String data2;               //string object variable 'data2'
            while((data2 = in.readLine()) != null)  //while data2 has a more lines to read
            {
                data = data + data2;        //set data to previous data added with data2 variable
            }
            Blockchain.blockchain = data; // Would normally have to check first for winner before replacing.
            System.out.println("         --NEW BLOCKCHAIN--\n" + Blockchain.blockchain + "\n\n");



            // wrote a custom updated BlockchainLedger.xml function here.
            // pass in PID and reMarshall data
            // update in a for loop for every new block that is sent to BlockchainWorker

            sock.close();   //close our socket
        } catch (IOException x){x.printStackTrace();}   //exit try block and set catch features
    }
}


@XmlRootElement
class BlockRecord{  //BlockRecord class for data manipulation and marshalling efforts

    String SHA256String;
    String SignedSHA256;
    String BlockID;
    String VerificationProcessID;
    String CreatingProcess;
    String PreviousHash;
    String Fname;
    String Lname;
    String SSNum;
    String DOB;
    String Diag;
    String Treat;
    String Rx;

    // blocknumber setter and getters
    // add sigUUID setter / getter



    public String getASHA256String() {return SHA256String;}                                 //creates all setters and getters
    @XmlElement                                                                             //for blockrecord marshalling
    public void setASHA256String(String SH){this.SHA256String = SH;}

    public String getASignedSHA256() {return SignedSHA256;}
    @XmlElement
    public void setASignedSHA256(String SH){this.SignedSHA256 = SH;}

    public String getACreatingProcess() {return CreatingProcess;}
     @XmlElement
    public void setACreatingProcess(String CP){this.CreatingProcess = CP;}

    public String getAVerificationProcessID() {return VerificationProcessID;}
      @XmlElement
    public void setAVerificationProcessID(String VID){this.VerificationProcessID = VID;}

    public String getABlockID() {return BlockID;}
      @XmlElement
    public void setABlockID(String BID){this.BlockID = BID;}

    public String getFSSNum() {return SSNum;}
      @XmlElement
    public void setFSSNum(String SS){this.SSNum = SS;}

    public String getFFname() {return Fname;}
      @XmlElement
    public void setFFname(String FN){this.Fname = FN;}

    public String getFLname() {return Lname;}
       @XmlElement
    public void setFLname(String LN){this.Lname = LN;}

    public String getFDOB() {return DOB;}
      @XmlElement
    public void setFDOB(String DOB){this.DOB = DOB;}

    public String getGDiag() {return Diag;}
      @XmlElement
    public void setGDiag(String D){this.Diag = D;}

    public String getGTreat() {return Treat;}
      @XmlElement
    public void setGTreat(String D){this.Treat = D;}

    public String getGRx() {return Rx;}
    @XmlElement
    public void setGRx(String D){this.Rx = D;}

}

public class Blockchain     //file class name
{
    static int PID;         //static integer object variable 'PID' initiated
    static String serverName = "localhost"; //designate name of the server we will multicast between - designate IP/inetAddress in real situations
    static String blockchain = "[Initial Block]"; //initiate blockchain's first block of data
    static int numProcesses = 3;                  //set the number of processes that will be used in scripting for N number of processes
    //static int numProcesses = 1;



    public Blockchain(int PID)                    //instantiates Blockchain class using the process PID as the parameter
    {this.PID = PID;}                             //

    public static KeyPair generateKeyPair(long seed) throws Exception
    {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom rng = SecureRandom.getInstance("SHA1PRNG", "SUN");
        rng.setSeed(seed);
        keyGenerator.initialize(1024, rng);

        return (keyGenerator.generateKeyPair());
    }


    public static String Marshaller(BlockRecord[] blockArrayNew, int index) throws JAXBException {
        String realBlock = null;
        String stringXML;
        int increment = index;

        index = 0;

        JAXBContext jaxbContext = JAXBContext.newInstance(BlockRecord.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StringWriter sw = new StringWriter();

        // CDE Make the output pretty printed:
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //System.out.println(index + " records read.");
        System.out.println("Names from input:");

        System.out.println("  " + blockArrayNew[index].getFFname() + " " + blockArrayNew[index].getFLname());

        System.out.println("\n");   //seperate

        //stringXML = sw.toString();

        File blockRecordFile = new File("blockRecordFile.xml");

        jaxbMarshaller.marshal(blockArrayNew[index], sw);
        jaxbMarshaller.marshal(blockArrayNew[index], blockRecordFile);

        realBlock = "(Block#" + Integer.toString(((Blockchain.PID+1)*10)+increment) + " from P"+ Blockchain.PID + ")";

        System.out.println(realBlock);

        String fullBlock = sw.toString();
        String XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
        String cleanBlock = fullBlock.replace(XMLHeader, "");                       // this is the block with the full data inserted
         //Show the string of concatenated, individual XML blocks:
        String XMLBlock = XMLHeader + "\n<BlockLedger>" + cleanBlock + "</BlockLedger>";
        System.out.println(cleanBlock);

        //MultiSendNewBlock(XMLBlock);
        return realBlock;
    }





    public BlockRecord[]    //BlockRecord array for public use

    BlockInput()            //Function utilized within the BlockRecord array
    {
        String FILENAME;    //String object variable 'FILENAME'
        String BlockReturned =null;
        BlockRecord[] blockArray = new BlockRecord[20]; //initialize BlockRecord[] object variable 'blockArray' equal to a new BlockRecord[] array with size set to 20 indexes

        //Tokenizes the file inputs and sets token names to the input array index of incoming corresponding field
        final int iFNAME = 0;   //sets first name of input line to BlockRecord[0]
        final int iLNAME = 1;   //sets last name of input line to BlockRecord[1]
        final int iDOB = 2;     //sets date of birth of input line to BlockRecord[2]
        final int iSSNUM = 3;   //sets social security number of input line to BlockRecord[3]
        final int iDIAG = 4;    //sets diagnosis of input line to BlockRecord[4]
        final int iTREAT = 5;   //sets treatment of input line to BlockRecord[5]
        final int iRX = 6;      //sets RX of input line to BlockRecord[6]

        int pnum;               //integer variable 'pnum' initialized

        if(PID == 0)         pnum = 0;  //if PID is equal to zero set the 'pnum' variable to zero
        else if (PID == 1)   pnum = 1;  //else if PID is equal to one set 'pnum' variable to one
        else if (PID == 2)   pnum = 2;  //else if PID is equal to two set 'pnum' variable to two
        else pnum = 0;  //if PID is not specified set default 'pnum' variable value to zero

        System.out.println("Process number: " + pnum + " Ports: " + Ports.UnverifiedBlockServerPort + " " +
                Ports.BlockchainServerPort + "\n");

        switch(pnum)    //utilize provided switch statement with parameter of 'pnum'
        {
            case 1: FILENAME = "BlockInput1.txt"; break;    //when 'pnum' is 1 set 'FILENAME' equal to 'BlockInput1.txt' and break out of switch statement
            case 2: FILENAME = "BlockInput2.txt"; break;    //when 'pnum' is 2 set 'FILENAME' equal to 'BlockInput2.txt' and break out of switch statement
            default: FILENAME= "BlockInput0.txt"; break;    //when 'pnum' is in it's primordial state set 'FILENAME' equal to 'BlockInput0.txt' and break out of switch statement
        }

        System.out.println("Using input file: " + FILENAME);    //print redundantly stating FILENAME we're using

        try //enter try block
        {
            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME)))  //enter try block set a BufferedReader variable 'br' to a new bufferedreader parameterized to new FileReader paramterized to FILENAME
            {
                String[] tokens = new String[10];   //set a string token array equal to a new string array of size 10
                String stringXML;                   //
                String InputLineStr;                //
                String suuid;                       //initialize a String variable 'suuid' to hold our UUID variable 'idA'
                UUID idA;                           //UUID object variable 'idA'

                JAXBContext jaxbContext = JAXBContext.newInstance(BlockRecord.class);   //jaxbcontext equals new instance of the blockrecord.class
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();             //marshaller object equals the blockrecord instance and creates a marshaller
                StringWriter sw = new StringWriter();                                   //create stringwriter object

                // CDE Make the output pretty printed:
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //formatting


                int n = 0;  //initialize an index placement variable for blockArray

                while ((InputLineStr = br.readLine()) != null) {    //while the 'InputLineStr' is still has input for the BufferedReader variable 'br' to .readline() from enter while loop
                   // System.out.println(InputLineStr);
                    blockArray[n] = new BlockRecord();              //set the blockArray equal to a new BlockRecord()

                    blockArray[n].setASHA256String("SHA string goes here...");  //
                    blockArray[n].setASignedSHA256("Signed SHA string goes here...");


                    idA = UUID.randomUUID();                           //set UUID object variable 'idA' to a randomized UUID function call
                    suuid = new String(UUID.randomUUID().toString());   //turn the above 'idA' into a String variable 'suuid'
                    blockArray[n].setABlockID(suuid);                   //call the BlockRecord() method .setABlockID(suuid) to feed in the UUID string


                    //create UUID SHA Hash and sign with Private Key
                    //blockArray[n].setSigUUID(signedUUID)


                    String temp = blockArray[n].getFDOB() + blockArray[n].getABlockID();
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(temp.getBytes());
                    byte byteData[] = md.digest();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < byteData.length; i++)
                    {
                        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                    }

                    String SHA256String = sb.toString();
                   // System.out.println(SHA256String);

                    blockArray[n].setASHA256String(SHA256String);
                    blockArray[n].setACreatingProcess("Process" + Integer.toString(pnum));
                    blockArray[n].setAVerificationProcessID("To be set later...");

                    tokens = InputLineStr.split(" +");          //splits the InputLineStr into tokens with initialized index variables set directly under the 'BlockInput()'
                    blockArray[n].setFSSNum(tokens[iSSNUM]);           //sets the field with the token parameter
                    blockArray[n].setFFname(tokens[iFNAME]);           //sets the field with the token parameter
                    blockArray[n].setFLname(tokens[iLNAME]);           //sets the field with the token parameter
                    blockArray[n].setFDOB(tokens[iDOB]);               //sets the field with the token parameter
                    blockArray[n].setGDiag(tokens[iDIAG]);             //sets the field with the token parameter
                    blockArray[n].setGTreat(tokens[iTREAT]);           //sets the field with the token parameter
                    blockArray[n].setGRx(tokens[iRX]);                 //sets the field with the token parameter

                  //MultiSendNewBlock(Marshaller(new BlockRecord[]{blockArray[n]}, n));
                    Marshaller(new BlockRecord[]{blockArray[n]}, n);    //Utilize Marshaller

                    n++;    //increment the blockArray
                }

            } catch (IOException e) {e.printStackTrace();}  //exit inner try block and set catch specs
        } catch (Exception e) {e.printStackTrace();}    //exit outter try block and set catch specs
        return blockArray;  //make the end product of this function the blockArray
    }


    public static void MultiSendNewBlock(String block) {    //function to Multicast
        Socket sock;                                        //regular Socket initialized to 'sock'
        PrintStream toServer;                               //printstream object variable 'toServer'

        try                                                 //enter try block
        {
            Thread.sleep(1000);
            String fakeBlockA = "(Block#" + Integer.toString(((PID+2))) + " from P"+ PID + ")";
            for(int i=0; i< numProcesses; i++)              //for the number of processes we're going to iterate through
            {
                sock = new Socket(serverName, Ports.UnverifiedBlockServerPortBase + i); //Socket 'sock' equals a new Socket set at the serverName and the unverifiedblockserverport where the process resides
                toServer = new PrintStream(sock.getOutputStream());               //printstream 'toServer' equals new Printstream object set to 'sock' method .getOutputStream
                toServer.println(block);                                               //prints the 'block' to the server
                toServer.flush();                                                   //flush the printstream
                sock.close();                                                       //close the Socket
            }
        }catch (Exception x) {x.printStackTrace ();}                              //exit the try block and set catch parameters
    }


    public void MultiSendPublicKeys(KeyPair keyPair)                   //function where we multisend the generated public keys to all processes for later verification
    {
        Socket sock;        //initialize Socket variable 'sock'
        PrintStream toServer;   //initialize Printstream variable 'toServer'

        try     //enter try block
        {
            for(int i=0; i< numProcesses; i++)  //interate through the amount of processes (numProcesses)
            {
                sock = new Socket(serverName, (Ports.KeyServerPortBase+i)); //Socket 'sock' equals new Socket object parameterized to 'serverName' and Ports running .KeyServerPortBase at the current process integer count
                toServer = new PrintStream(sock.getOutputStream()); //printstream object 'toServer' equals new printstream object parameterized Socket 'sock' .getOutputStream method
                toServer.println("Public Key" + keyPair.getPublic());  //print our public key modulus and public exponent in the keyServerPortBase
                toServer.println(PID);  //printstream runs the .println() parameterized with PID
                toServer.flush();   //flush the printsream 'toServer'
                sock.close();   //close Socket 'sock'
            }
            Thread.sleep(1000); // wait for keys to settle, normally would wait for an ack
        }catch (Exception x) {x.printStackTrace ();}    //exit try block and set catch execution
    }

    public static void main(String args[]) throws Exception //main function
    {
        int q_len = 8; //queue length of requests
        int PID = (args.length < 1) ? 0 : Integer.parseInt(args[0]); // Process ID parsed at runtime
        System.out.println("BlockFramework control by Seth Weber-c to quit.\n");    //print statement headlining terminal output
        System.out.println("Using processID " + PID + "\n");                //print statement telling us what PID we're using
        final BlockingQueue<String> queue = new PriorityBlockingQueue<>();          // Concurrent queue for unverified blocks
        new Ports().setPorts(PID);                                                  // Establish OUR port number scheme, based on PID
        KeyPair keyPair = new Blockchain(PID).generateKeyPair(999+PID);     //KeyPair object variable 'keyPair' equal to new Blockchain parameterized with PID running the .generateKeyPair method parameterized to 999+1

        new Thread(new KeyServer()).start();  //initiates new Thread to .start() a KeyServer runnable
        new Thread(new UnverifiedBlockServer(queue,PID)).start(); //initiates new Thread to .start() a UnverifiedBlockServer runnable
        new Thread(new BlockchainServer()).start();     //initiates new Thread to .start() a BlockchainServer runnable
        try{Thread.sleep(1000);}catch(Exception e){}     //sleep until all servers have started running

        new Blockchain(PID).MultiSendPublicKeys(keyPair);   //multicasts public keys to each

        if(PublicKeyWorker.pubKeyArray[0].equals(PublicKeyWorker.pubKeyArray[1]))       //if public key array indexes are equal
        {
            System.out.println("KEYS ARE EQUAL");   //print statement triggered if public keys multicast have a duplicate value
        }
        else {
            System.out.println("Keys sent are of differing value: Proceed!");  //print statement validating that all keys mulitcast out are different so processes can sign and be signed with handling unverified blocks
            //System.out.println(PublicKeyWorker.pubKeyArray[0]);
            //System.out.println(PublicKeyWorker.pubKeyArray[1]);
            //System.out.println(PublicKeyWorker.pubKeyArray[2]);
        }

        BlockRecord[] blockRecord = new Blockchain(PID).BlockInput(); //establishes a BlockRecord object array 'blockRecord' equal to a new Blockchain class instantiation running the .BlockInput() function
        int index = 0;  //initialize an integer object 'index' variable for iterating
        while(blockRecord[index]!=null) //while the BlockRecord object blockRecord[index] is not equal to 'null' enter while loop
        {
            index++;                    //increment the index until 'null' is reached, signifying the end of the blockRecord array
        }

        for(int i=0;i<index;i++)        //iterate through the index count achieved in above while loop
        {
            BlockRecord[] realBlock = new Blockchain(PID).BlockInput();
            System.out.println(realBlock[i].getFLname());
           // String realBlock = Marshaller(new BlockRecord[]{blockRecord[i]},i);
            new Blockchain(PID).MultiSendNewBlock(realBlock[i].getABlockID());  //multisend to the indexed PID the new blockchain
        }
         try{Thread.sleep(1000);}catch(Exception e){} //sleep until all unverified blocks have come into the queue from each process
         new Thread(new UnverifiedBlockConsumer(queue, PID)).start(); //once all blocks of data are in the queue and multicast to all process begin UnverifiedBlockConsumer thread
    }
}

class Ports     //class to handle port allocation
{
    public static int KeyServerPortBase = 4710;     //statically sets the key server to port 4710 to be incremented by each process later for communication to this server
    public static int UnverifiedBlockServerPortBase = 4820; //statically sets the unverified block server to port 4820 to be incremented by each process later for communication to this server
    public static int BlockchainServerPortBase = 4930;  //statically sets the blockchain server to port 4930 to be incremented by each process later for communication to this server

    public static int KeyServerPort;    //initiates key server port for communication capabilities from process to server
    public static int UnverifiedBlockServerPort;    //initiates unverified block server port for communication capabilities from process to server
    public static int BlockchainServerPort; //initiates blockchain server port for communication capabilities from process to server

    public void setPorts(int PID)   //function takes in an integer parameter of each processes' PID
    {
        KeyServerPort = KeyServerPortBase + (PID);  //adds process integer PID (1, 2, or 3) to 'KeyServerPortBase' to establish communication channel for keys
        UnverifiedBlockServerPort = UnverifiedBlockServerPortBase + (PID);  //adds process integer PID (1, 2, or 3) to 'UnverifiedBlockServerPortBase' to establish communication channel for unverified blocks
        BlockchainServerPort = BlockchainServerPortBase + (PID);    //adds process integer PID (1, 2, or 3) to 'BlockchainServerPortBase' to establish communication channel for keys for new and existing blockchains
    }
}