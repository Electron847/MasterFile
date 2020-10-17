import java.util.Comparator;

public class Point3 {
    public final double x;
    public final double y;
    public static final Comparator<Point3> sortX = new compareX();
    public static final Comparator<Point3> sortY = new compareY();

    public Point3(double x, double y){
        this.x = x;
        this.y = y;
    }



    private static class compareX implements Comparator<Point3>{
        //comparator for comparing x-coordinates
        public int compare(Point3 o1, Point3 o2) {
            if (o1.x < o2.x) return -1;
            if (o1.x > o2.x) return +1;
            return 0;
        }
    }

    private static class compareY implements Comparator<Point3>{
        //comparator for comparing y-coordinates
        public int compare(Point3 o1, Point3 o2) {
            if (o1.y < o2.y) return -1;
            if (o1.y > o2.y) return +1;
            return 0;
        }
    }

}