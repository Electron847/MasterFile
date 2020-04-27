package HW2;

import junit.framework.TestCase;
import java.util.*;

public class AssertExp1Test extends TestCase
{
    public void testMinValue()
    {
        assertEquals(AssertExp1.minValue(new double[]{-7}), -7.0, 0);
        assertEquals(AssertExp1.minValue(new double[]{1, -4, -7,7,8,11}),-7.0,0);
        assertEquals(AssertExp1.minValue(new double[]{-13, -4, -7, 7, 8, 11}),-13.0,0);
        assertEquals(AssertExp1.minValue(new double[]{1, -4, -7, 7, 8, 11, -9}), -9.0, 0);
    }

    public void testMinPosition()
    {
        assertEquals(AssertExp1.minPosition(new double[]{-7}),0,0);
        assertEquals(AssertExp1.minPosition(new double[]{-13, -4, -7, 7, 8, 11}),0, 0);
        assertEquals(AssertExp1.minPosition(new double[]{1, -4, -7, 7, 8, 11, -9}), 6, 0);
        assertEquals(AssertExp1.minPosition(new double[]{1, -4, -7, 7, -8, 11, 9}), 4, 0);
       // assertEquals(AssertExp1.minPosition(new double[]{1, -14, -7, 7, 8, 11, -9}), 6, 0);
    }

    public void testNumUnique()
    {
        assertEquals(AssertExp1.numUnique(new double[]{ }), 0,0);
        assertEquals(AssertExp1.numUnique(new double[]{7}), 1, 0);
        assertEquals(AssertExp1.numUnique(new double[]{11, 11, 11, 11}), 1, 0);
        assertEquals(AssertExp1.numUnique(new double[]{11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88}), 8, 0);
        assertEquals(AssertExp1.numUnique(new double[]{11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88}), 8, 0);

    }

    public void testRemoveDuplicates()
    {
        assertEquals(Arrays.toString(AssertExp1.removeDuplicates(new double[]{})), Arrays.toString(new double[]{}));
        assertEquals(Arrays.toString(AssertExp1.removeDuplicates(new double[]{11})), Arrays.toString(new double[]{11}));
        assertEquals(Arrays.toString(AssertExp1.removeDuplicates(new double[]{11,11,11,11})), Arrays.toString(new double[]{11}));
        assertEquals(Arrays.toString(AssertExp1.removeDuplicates(new double[]{11,11,11,11,22,33,44,44,44,44,44,55,55,66,77,88,88})), Arrays.toString(new double[]{11.0, 22.0, 33.0, 44.0, 55.0, 66.0, 77.0,88.0}));
        assertEquals(Arrays.toString(AssertExp1.removeDuplicates(new double[]{11,22,33,44,44,44,44,44,55,55,66,77,88})), Arrays.toString(new double[]{11.0, 22.0, 33.0, 44.0, 55.0, 66.0 ,77.0 ,88.0}));
    }
}
