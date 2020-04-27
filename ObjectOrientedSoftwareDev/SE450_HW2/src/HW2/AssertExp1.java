package HW2;

public class AssertExp1 {

    /***
     * @param list;
     * minValue returns the minimum value in an array of doubles. You can assume
     * the array is nonempty and has no duplicates. Your solution must go
     * through the array exactly once. Your solution must not call any other
     * functions. Here are some examples (using "==" informally):
     *
     * -7 == minValue(new double[] { -7 }) -7 == minValue(new double[] { 1, -4,
     * -7, 7, 8, 11 }) -13 == minValue(new double[] { -13, -4, -7, 7, 8, 11 })
     * -9 == minValue(new double[] { 1, -4, -7, 7, 8, 11, -9 })
     * @return min
     */


    public static double minValue(double[] list){
        if (list == null) throw new AssertionError("list is null and has no elements");
        double min = list[0];
        for (int index = 0; index != list.length; index++){
            assert (index >= 0 && index <= list.length);
            if(list[index] < min){
                if (min == list[index]) throw new AssertionError();
                //assert (min != list[index]);
                assert (min > list[index]);
                min = list[index];
            }
        }
        return min;
    }

    /***
     * @param list
     * minPosition returns the position of the minimum value in an array of
     * doubles. The first position in an array is 0 and the last is the
     * array.length-1. You can assume the array is nonempty and has no
     * duplicates. Your solution must go through the array exactly once. Your
     * solution must not call any other functions. Here are some examples (using
     * "==" informally):
     *
     * 0 == minPosition(new double[] { -7 }) 2 == minPosition(new double[] { 1,
     * -4, -7, 7, 8, 11 }) 0 == minPosition(new double[] { -13, -4, -7, 7, 8, 11
     * }) 6 == minPosition(new double[] { 1, -4, -7, 7, 8, 11, -9 })
     * @return min
     */

    public static int minPosition(double[] list)
    {
        int min = 0;
        for (int i = 0; i < list.length; ++i){
            assert (i < list.length);
            if (list[i] < list[min]){
                assert (list[min] != list[i]);
                assert (list[min] > list[i]);
                min = i;
            }
        }
        assert (min >= 0 && min <= list.length);
        return min;
    }


    /***
     * @param list
     * numUnique returns the number of unique values in an array of doubles.
     * Unlike the previous questions, the array may be empty and it may contain
     * duplicate values. Also unlike the previous questions, you can assume the
     * array is sorted.
     *
     * Your solution must go through the array exactly once. Your solution must
     * not call any other functions. Here are some examples (using "=="
     * informally):
     *
     * 0 == numUnique(new double[] { }) 1 == numUnique(new double[] { 11 }) 1 ==
     * numUnique(new double[] { 11, 11, 11, 11 }) 8 == numUnique(new double[] {
     * 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }) 8
     * == numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66,
     * 77, 88 })
     * @return total
     */

    public static int numUnique(double [] list){
        int total = 0;
        assert (total == 0);

        if(list.length == 0){
            assert (list.length == 0);
            return total;
        }

        assert (list.length >= 1);
        total = 1;

        for(int i = 0; i != list.length; i++){

            assert (i >= 0 &&  i <= list.length);
            if(i + 2 <= list.length){

                if(list[i] != list[i + 1]){
                    total += 1;
                }
            }
        }

        assert (total >= 1);
        return total;
    }

    /***
     * @param list
     * removeDuplicates returns the collection of unique values in an array of
     * doubles. You may assume that the list is sorted, as you did for
     * numUnique.
     *
     * Your solution may call numUnique, but should not call any other
     * functions. After the call to numUnique, you must go through the array
     * exactly one more time. Here are some examples (using "==" informally):
     *
     * new double[] { } == removeDuplicates(new double[] { }) new double[] { 11
     * } == removeDuplicates(new double[] { 11 }) == removeDuplicates(new
     * double[] { 11, 11, 11, 11 }) new double[] { 11, 22, 33, 44, 55, 66, 77,
     * 88 } == removeDuplicates(new double[] { 11, 11, 11, 11, 22, 33, 44, 44,
     * 44, 44, 44, 55, 55, 66, 77, 88, 88 }) == removeDuplicates(new double[] {
     * 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })
     * @return res
     */

    public static double[] removeDuplicates(double[] list){
        int change = numUnique(list);
        if(change == 0 || list == null){
            return list;
        }
        assert (change >= 1);
        double[] res = new double[change];
        int resspot = 0;
        res[resspot] = list[0];
        for (int i = 0; i != list.length; i++){

            assert (i >= 0 && i <= list.length);
            if(res[resspot] != list[i]){
                res[resspot + 1] = list[i];
                resspot += 1;
            }

        }
        return res;
    }
}
