package basics.hashmutable;
import java.util.Date;
import java.util.Hashtable;
// example from http://www.vipan.com/htdocs/hashcode_help.html
public class Main {
  public static void main (String[] args) {
    Hashtable<Date,String> map = new Hashtable<Date,String>();
    long time = System.currentTimeMillis();
    Date dt = new Date(time);
    Date dt2 = new Date(time);
    
    System.out.println("dt.toString() = " + dt.toString());
    System.out.println("dt2.toString() = " + dt2.toString());
    System.out.println("Is dt2.equals(dt)? = " + dt2.equals(dt));
    
    map.put(dt, "blah");
    System.out.println("map.get(dt) = " + map.get(dt) );
    System.out.println("map.get(dt2) = " + map.get(dt2) );
    
    // Change dt by adding a day to its time
    long newTime = time + 24*60*60*1000;
    dt.setTime(newTime);
    
    System.out.println("\nAfter dt.setTime(newTime):");
    System.out.println("dt.toString() = " + dt.toString());
    System.out.println("dt2.toString() = " + dt2.toString());
    System.out.println("Is dt2.equals(dt)? = " + dt2.equals(dt));
    System.out.println("map.get(dt) = " + map.get(dt) );
    System.out.println("map.get(dt2) = " + map.get(dt2) );
    
    System.out.println("\nmap = " + map.toString() );
  }
}
