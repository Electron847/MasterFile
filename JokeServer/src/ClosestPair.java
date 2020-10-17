/*

Seth Weber
Closest Pair Algorithm Homework Assignment
May 20th, 2019

Run and created in IntelliJ IDEA 2018.3.3
JRE 1.8.0

! ! ! TO RUN = GO TO MAIN() AND CHANGE FILEPATHS TO LOCAL PATHS ! ! !

*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClosestPair {
    static double min = Integer.MAX_VALUE;
    static Point p1 =null;
    static Point p2 = null;

    public static class Point{
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void shortestDistance(List<Point> list) throws IllegalArgumentException{
        if(list.size()<2 || list == null) throw new IllegalArgumentException("Not Enough Points!");
        int n = list.size();
        Point[] pointsbyX = new Point[n];
        for(int i=0;i<n;i++){
            pointsbyX[i] = list.get(i);
        }

        //this will sort the array based on the specification of x-coordinate order
        Arrays.sort(pointsbyX, new Comparator<Point>() {
            public int compare(Point alpha_1, Point beta_1) {
                if(alpha_1.x != beta_1.x) return alpha_1.x - beta_1.x;
                else return alpha_1.y - beta_1.y;}
        });

        for(int i=0;i<n-1;i++){
            if(pointsbyX[i]==pointsbyX[i+1]){
                min = 0;
                p1 = pointsbyX[i];
                p2 = pointsbyX[i+1];
                break;
            }
        }
        Point[] pointsY = new Point[n];
        for (int i = 0; i < n; i++)
            pointsY[i] = pointsbyX[i];
        Point[] aux = new Point[n];
        closest(pointsbyX, pointsY, aux, 0, n-1);
    }

    private static double closest(Point[] pointsByX, Point[] pointsByY, Point[] aux, int lo, int hi) {
        if (hi <= lo) return Double.POSITIVE_INFINITY;
        int mid = lo + (hi - lo) / 2;
        Point middlePoint = pointsByX[mid];

        // compute closest pair with both endpoints in left subarray or both in right subarray
        double delta1 = closest(pointsByX, pointsByY, aux, lo, mid);
        double delta2 = closest(pointsByX, pointsByY, aux, mid+1, hi);
        double delta = Math.min(delta1, delta2);

        // merge the sub data structures back so that y-coordinates are take into account
        mergeSubStructures(pointsByY, aux, lo, mid, hi);

        int proxxy = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pointsByY[i].x - middlePoint.x) < delta)
                aux[proxxy++] = pointsByY[i];
        }

        for (int i = 0; i < proxxy; i++) {
            //appears n**2 however, golden ratio demands at most 7 iterations
            for (int j = i+1; (j < proxxy) && (aux[j].y - aux[i].y < delta); j++) {
                double distance = distance(aux[i], aux[j]);
                if (distance < delta) {
                    delta = distance;
                    if (distance < min) {
                        min = delta;
                        p1 = aux[i];
                        p2 = aux[j];
                        System.out.println("Getting closer! Distance = " + delta + " from (" + p1.x+","+p1.y + ") to (" + p2.x+","+p2.y+")");
                    }
                }
            }
        }
        return delta;
    }

    //function designed to merge the subarrays back
    private static void mergeSubStructures(Point[] a, Point[] aux, int lo, int mid, int hi) {
        //duplicate
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        //conditionally merge the sub structures
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    //create a boolean for yes/no decisions
    private static boolean less(Point alpha, Point beta) {
        return alpha.x < beta.x;
    }

    //distance generation between two points
    public static double distance(Point a, Point b){
        int x = a.x-b.x;
        int y = a.y-b.y;
        return Math.sqrt(x*x+y*y);
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        File file1 = new File("/Users/sethweber/Downloads/tests&solution/10points.txt");
        File file2 = new File("/Users/sethweber/Downloads/tests&solution/100points.txt");
        File file3 = new File("/Users/sethweber/Downloads/tests&solution/1000points.txt");

        try {
            Scanner ls = new Scanner(file2);
            while (ls.hasNext()) {
                int x = Integer.valueOf(ls.next());
                int y = Integer.valueOf(ls.next());
                Point abs = new Point(x, y);
                points.add(abs);
            }
            ls.close();
        } catch (FileNotFoundException x) {x.printStackTrace();}
        shortestDistance(points);
        System.out.println("The closest pair of points are ("+p1.x+","+p1.y+") ("+p2.x+","+p2.y+") and the distance between them is "+ min);
    }
}
