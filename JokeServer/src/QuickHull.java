/*
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickHull  //name of the java class file
{
    public ArrayList<Point> quickHull(ArrayList<Point> points)  //name of the public array of points passed to the algorithm of quickhull
    {
        ArrayList<Point> convexHull = new ArrayList<Point>(); //local name of array list of points that create the convex hull
        if (points.size() < 3)  //checks to make sure the set of points fed into convex hull is less than three
            return (ArrayList) points.clone();  //returns a clone of the points if the set is less than three, program stops here
        //array list of points is greater than 3 so lets make a convex hull
        int minPoint = -1;  //minPoint int variable set to -1
        int maxPoint = -1;  //maxPoint int variable set to -1
        int minX = Integer.MAX_VALUE;   //minX int variable set to //
        int maxX = Integer.MIN_VALUE;   //maxX int variable set to//
        for (int i = 0; i < points.size(); i++)     //for all entries in the array, iterate through and perform below operations
        {
            if (points.get(i).x < minX) //if the point at array index i has the x-cord less than minX perform next step(s)
            {
                minX = points.get(i).x; //minX set to the x-cord of the point initiating this step
                minPoint = i;   //set minPoint to index i, exit if statement
            }
            if (points.get(i).x > maxX) //if the point at array index i has the x-cord greater than maxX perform next step(s)
            {
                maxX = points.get(i).x; //maxX set to the x-cord of the point initiating this step
                maxPoint = i;   //maxPoint set to index i, exit if statement
            }
        }
        Point A = points.get(minPoint); //Point object variable A set to most current minPoint in convex hull
        Point B = points.get(maxPoint); //Point object variable B set to most current maxPoint in convex hull
        convexHull.add(A);  //convexHull arrayList adds point A to itself
        convexHull.add(B);  //convexHull arrayList adds point B to itself
        points.remove(A);   //point array removes point A
        points.remove(B);   //point array removes point B

        ArrayList<Point> leftSet = new ArrayList<Point>();  //array list of points dedicated to leftSet is created
        ArrayList<Point> rightSet = new ArrayList<Point>(); //array list of points dedicated to rightSet is created

        for (int i = 0; i < points.size(); i++)
        {
            Point p = points.get(i);                //Point object variable p set to point array index i
            if (pointLocation(A, B, p) == -1)       //if pointLocation of A related to B related to point p at index i is -1 enter if block
                leftSet.add(p);                     //if above is true leftSet adds Point object variable p
            else if (pointLocation(A, B, p) == 1)   //else if pointLocation of A related to B related to point p at index i is equal to 1 enter block
                rightSet.add(p);                    //if above is true rightSet adds Point object variable p
        }
        hullSet(A, B, rightSet, convexHull);        //hullSet function gets sent parameters A, B, rightSet, and convexHull
        hullSet(B, A, leftSet, convexHull);         //hullSet function gets sent parameters A, B, leftSet, and convexHull

        return convexHull;                          //return the convexHull
    }

    public int distance(Point A, Point B, Point C)  //function to define distances for manipulation
    {
        int ABx = B.x - A.x;                        //int A cross B x-cord set to x-cord of Point B minus x-cord of Point A
        int ABy = B.y - A.y;                        //int A cross B y-cord set to y-cord of Point B minus y-cord of Point A
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);    //num for cross vector set to A cross B x-cord times (y-cord of A minus y-cord of C) minus A cross B y-cord times (x-cord of A minus x-cord of C)
        if (num < 0)    //if num is less than 0
            num = -num; //set num to it's negative
        return num;     //otherwise return num as it is
    }

    public void hullSet(Point A, Point B, ArrayList<Point> set,
                        ArrayList<Point> hull)                //function that returns the points generating the convexHull
    {
        int insertPosition = hull.indexOf(B);   //int object variable insertPosition set to hull index of Point B
        if (set.size() == 0)    //if set size is 0
            return;             //return nothing
        if (set.size() == 1)    //if set size is 1
        {
            Point p = set.get(0);   //Point object variable p set to set collection at position index 0
            set.remove(p);          //now remove the Point object variable p from the set
            hull.add(insertPosition, p);    //hull adds this point at the insertPosition of the second point object
            return;     //return from void function
        }
        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++)
        {
            Point p = set.get(i);
            int distance = distance(A, B, p);
            if (distance > dist)
            {
                dist = distance;
                furthestPoint = i;
            }
        }
        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);

        // Determine who's to the left of AP
        ArrayList<Point> leftSetAP = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++)
        {
            Point M = set.get(i);
            if (pointLocation(A, P, M) == 1)
            {
                leftSetAP.add(M);
            }
        }

        // Determine who's to the left of PB
        ArrayList<Point> leftSetPB = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++)
        {
            Point M = set.get(i);
            if (pointLocation(P, B, M) == 1)
            {
                leftSetPB.add(M);
            }
        }
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);

    }

    public int pointLocation(Point A, Point B, Point P)
    {
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }

    public static void main(String args[])
    {
      */
/*
        ArrayList<Point> points = new ArrayList<Point>();
        File file = new File("/Users/sethweber/Downloads/TestFiles-Graham/case1.txt");
        try {
            System.out.println("hello");
            int proxy = 0;
            Scanner ls = new Scanner(file);
            System.out.println(ls.nextInt());
            System.out.println(ls.nextInt());
            System.out.println(ls.nextInt());
            for (int i = 0; i <= file.length(); i++){
                int x = ls.nextInt();
                int y = ls.nextInt();
                Point e = new Point(x, y);
                points.add(i, e);
            }

            System.out.println(points);
            } catch (FileNotFoundException e) {e.printStackTrace();}
*//*



        File file = new File("/Users/sethweber/Downloads/TestFiles-Graham/case1.txt");


        System.out.println("Quick Hull Test");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of points");
        int N = sc.nextInt();


        ArrayList<Point> points = new ArrayList<Point>();
        System.out.println("Enter the coordinates of each points: <x> <y>");
        for (int i = 0; i < N; i++)
        {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Point e = new Point(x, y);
            points.add(i, e);
        }


        QuickHull qh = new QuickHull();
        ArrayList<Point> p = qh.quickHull(points);
        System.out.println("The points in the Convex hull using Quick Hull are: ");
        for (int i = 0; i < p.size(); i++)
            System.out.println("(" + p.get(i).x + ", " + p.get(i).y + ")");
        sc.close();
    }
}*/
