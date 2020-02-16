/*

Seth Weber
Applied Algorithms 421
Programming Assignment 1


In order to run program with each test file go down to main()
and for each: File file1-file6, path names should be changed
to the pathname where file is located on YOUR machine for access
to the contents.

**   PROGRAM WILL NOT COMPILE UNTIL
**   File file = new File( * change pathname here * )
**   HAS SPECIFIED PATHNAME IN PARAMETER
**   SUPPLIED BY GRADER

Any further instructions for running program are supplied in main() function
- Program originally coded and compiled with IntelliJ IDE
- Java Version 1.8

*/

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConvexHull  //name of the java class file
{
    public ArrayList<Point> convexerList(ArrayList<Point> points)  //name of the public array of points passed to the algorithm of quickhull
    {
        ArrayList<Point> convexHull = new ArrayList<Point>(); //local name of array list of points that create the convex hull
        if (points.size() < 3)  //checks to make sure the set of points fed into convex hull is less than three
            return (ArrayList) points.clone();  //returns a clone of the points if the set is less than three, program stops here

        //array list of points is greater than 3 so lets make a convex hull
        int lowPoint = -1;  //minPoint int variable set to -1
        int highPoint = -1;  //maxPoint int variable set to -1
        int minX = Integer.MAX_VALUE;   //minX int variable set to //
        int maxX = Integer.MIN_VALUE;   //maxX int variable set to//
        for (int i = 0; i < points.size(); i++)     //for all entries in the array, iterate through and perform below operations
        {
            if (points.get(i).x < minX) //if the point at array index i has the x-cord less than minX perform next step(s)
            {
                minX = points.get(i).x; //minX set to the x-cord of the point initiating this step
                lowPoint = i;   //set minPoint to index i, exit if statement
            }
            if (points.get(i).x > maxX) //if the point at array index i has the x-cord greater than maxX perform next step(s)
            {
                maxX = points.get(i).x; //maxX set to the x-cord of the point initiating this step
                highPoint = i;   //maxPoint set to index i, exit if statement
            }
        }
        Point A = points.get(lowPoint); //Point object variable A set to most current minPoint in convex hull
        Point B = points.get(highPoint); //Point object variable B set to most current maxPoint in convex hull
        convexHull.add(A);  //convexHull arrayList adds point A to itself
        convexHull.add(B);  //convexHull arrayList adds point B to itself
        points.remove(A);   //point array removes point A
        points.remove(B);   //point array removes point B

        ArrayList<Point> leftSet = new ArrayList<Point>();  //array list of points dedicated to leftSet is created
        ArrayList<Point> rightSet = new ArrayList<Point>(); //array list of points dedicated to rightSet is created

        for (int i = 0; i < points.size(); i++)
        {
            Point proxyPoint = points.get(i);                //Point object variable p set to point array index i
            if (locationOfPoints(A, B, proxyPoint) == -1)       //if pointLocation of A related to B related to point p at index i is -1 enter if block
                leftSet.add(proxyPoint);                     //if above is true leftSet adds Point object variable p
            else if (locationOfPoints(A, B, proxyPoint) == 1)   //else if pointLocation of A related to B related to point p at index i is equal to 1 enter block
                rightSet.add(proxyPoint);                    //if above is true rightSet adds Point object variable p
        }
        hullPointSet(A, B, rightSet, convexHull);        //hullSet function gets sent parameters A, B, rightSet, and convexHull
        hullPointSet(B, A, leftSet, convexHull);         //hullSet function gets sent parameters A, B, leftSet, and convexHull
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

    public void hullPointSet(Point A, Point B, ArrayList<Point> set,
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
        int dist = Integer.MIN_VALUE;   //integer object variable dist set to Integer.Min_value
        int farPoint = -1;         //integer object variable furthestPoint set to negative one
        for (int i = 0; i < set.size(); i++)    //for each index in the set
        {
            Point p = set.get(i);               //Point object variable p set to the value in the set at index i
            int distance = distance(A, B, p);   //int object variable distance is set to distance function with parameters Point A, Point B, point p
            if (distance > dist)                //if distance of above three points is greater than the Integer.Min_Value enter below
            {
                dist = distance;                //dist is set to the distance of three points A, B, and p
                farPoint = i;              //furthestPoint set to index i in the set
            }
        }

        Point P = set.get(farPoint);       //Point object variable p set equal to the farPoint in the set
        set.remove(farPoint);              //remove this farPoint from the set
        hull.add(insertPosition, P);       //the hull gets point P added to it at the insertionPoint which currently is the index position of point B

        ArrayList<Point> leftSetAP = new ArrayList<Point>();   // Find points to the left of point A and set P
        for (int i = 0; i < set.size(); i++)        //for indexes in the set up to the size of the set enter below for block
        {
            Point M = set.get(i);                   //Point M set equal to the value of the set at index position i
            if (locationOfPoints(A, P, M) == 1)     //if cross product of these points A P M is greater than 1 it is now set equal to 1 and enters below if block
            {
                leftSetAP.add(M);                   //add point M to the array list of points
            }
        }

        ArrayList<Point> leftSetPB = new ArrayList<Point>();    //Find points to the left of point B and set P
        for (int i = 0; i < set.size(); i++)                    //for indexes in the set up to the size of the set enter below for block
        {
            Point M = set.get(i);                   //Point M set equal to the value of the set at index position i
            if (locationOfPoints(P, B, M) == 1)     //if cross product of these points B P M is greater than 1 it is now set equal to 1 and enters below if block
            {
                leftSetPB.add(M);                   //add point M to the array list of points
            }
        }
        hullPointSet(A, P, leftSetAP, hull);    //the set of points in our
        hullPointSet(P, B, leftSetPB, hull);    //the set of points fed into
    }

    public int locationOfPoints(Point A, Point B, Point P)  //finds cross products for us to decide if points in hullPointSet get added to the hull or not
    {
        int crossProduct = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);    //integer object variable set to an equation that shows whether the cross product of 3 points we plug in is positive, negative or zero
        if (crossProduct > 0)    //if cross product is greater than one
            return 1;   //return 1 for simplicity-sake to define actions to be executed
        else if (crossProduct == 0)  //else if cross product is 0
            return 0;   //return 0
        else
            return -1;
    }

    public static void main(String args[])
    {

        ArrayList<Point> points = new ArrayList<Point>();   //ArrayList object variable points holds our array list of points we can add to dynamically


        //  MAKE SURE TO CHANGE BELOW PATHNAME(S) TO THE PATHNAME SPECIFIED ON
        //  YOUR MACHINE  **THESE PATHNAMES WILL NOT WORK**
        //  ONCE PATHNAME IS CHANGED
        //  ** Scanner ls = new Scanner(file1); **
        //  ** Scanner ls = new Scanner( ** change this parameter accordingly ** )

        File file1 = new File("/Users/sethweber/Downloads/TestFiles-Graham/case1.txt");
        File file2 = new File("/Users/sethweber/Downloads/TestFiles-Graham/case2.txt");
        File file3 = new File("/Users/sethweber/Downloads/TestFiles-Graham/case3.txt");
        File file4 = new File("/Users/sethweber/Downloads/TestFiles-Graham/case4.txt");
        File file5 = new File("/Users/sethweber/Downloads/TestFiles-Graham/case5.txt");
        File file6 = new File("/Users/sethweber/Downloads/TestFiles-Graham/case6.txt");


        try {                               //try block for file import trying
        Scanner ls = new Scanner(file1);    //Scanner object variable
        while(ls.hasNext())                 //while the scanner object ls hasNext available data for reading remain in while block
            {
                int x = Integer.valueOf(ls.next()); //int x set equal to the integer parsing value of the string value at ls.next()
                System.out.println(x);          //print statement to check validity of x-coordinate
                int y = Integer.valueOf(ls.next()); //int y set equal to the integer parsing value of the string value at ls.next()
                System.out.println(y);          //print statement to check validity of y-coordinate
                Point abs = new Point(x,y);     //Point object variable abs set equal to a new Point object with x and y coordinate parameters filled in from dynamically allocated variables
                points.add(abs);                //this Point object variable abs is now added to the ArrayList object variable points
            }
        ls.close();         //close the Scanner object
    }catch (FileNotFoundException x) {x.printStackTrace();} //catch statement to make sure our File is found, if not it prints a traceable error statement

        ConvexHull myConvexHull = new ConvexHull();     //ConvexHull object variable myConvexHull set equal to a new ConvexHull object not filled in
        ArrayList<Point> pointArray = myConvexHull.convexerList(points);     //
        System.out.println("Convex hull is comprised of the points : ");
        for (int i = 0; i < pointArray.size(); i++)
            System.out.println("(" + pointArray.get(i).x + ", " + pointArray.get(i).y + ")");

    }
}