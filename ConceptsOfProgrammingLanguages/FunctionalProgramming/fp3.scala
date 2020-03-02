// GENERATED
/* INSTRUCTIONS
 *
 * Complete the exercises below.  For each "EXERCISE" comment, add
 * code immediately below the comment.
 *
 * Please see README.md for instructions, including compilation and testing.
 * 
 * GRADING
 * 
 * 1. Submissions MUST compile using SBT with UNCHANGED configuration and tests with no
 *    compilation errors.  Submissions with compilation errors will receive 0 points.
 *    Note that refactoring the code will cause the tests to fail.
 *
 * 2. You MUST NOT edit the SBT configuration and tests.  Altering it in your submission will
 *    result in 0 points for this assignment.
 *
 * 3. You MUST NOT use while loops or (re)assignment to variables (you can use "val" declarations,
 *    but not "var" declarations).  You must use recursion instead.
 *
 * 4. You may declare auxiliary functions if you like.
 *
 * SUBMISSION
 *
 * 1. Submit this file on D2L before the deadline.
 *
 * 2. Late submissions will not be permitted because solutions will be discussed in class.
 * 
 */

object fp3 {

  // EXERCISE 1: complete the following recursive definition of a "member" function 
  // to check whether an element "a" is a member of a list of integers "xs".
  // Your implementation of "member" MUST be recursive and not use the builtin "contains" method from the List class.
  // EXAMPLES: 
  // - member (5, List (4, 6, 8, 5)) == true
  // - member (3, List (4, 6, 8, 5)) == false
  def member (a : Int, xs : List[Int]) : Boolean = {

    xs match{
      case Nil => false              // In the case the list 'xs' is empty return a false value
      case front :: remainder =>     // When xs is a an iterable list
        if (front == a) {true}       // If the front element of the list is equal to 'a', the identified integer being searched for in the list, return true
        else {member(a, remainder)}  // Else recursively iterate through the remainder of the list to determine if 'a' is a member of the remaining elements
    }
  }

  // EXERCISE 2: complete the following recursive definition of an "allEqual" function
  // to check whether all elements in a list of integers are equal.
  // EXAMPLES:
  // - allEqual (Nil) == true
  // - allEqual (List (5)) == true
  // - allEqual (List (5, 5, 5)) == true
  // - allEqual (List (6, 5, 5, 5)) == false
  // - allEqual (List (5, 5, 6, 5)) == false
  // - allEqual (List (5, 5, 5, 6)) == false
  def allEqual (xs : List[Int]) : Boolean = {
    xs match{
      case Nil => true                              //Where xs is an empty list return default value of true
      case loneNumber :: Nil => true                //Where xs is a list with a single element return value of true
      case constant :: front :: remainder =>        //When there's a list of more than one element the first element becomes the 'constant' which is checked against the rest of the list which is defined with the next element after 'constant' being the 'front' and the remaining elements the 'remainder'
        (constant == front) && allEqual(front :: remainder) //
    }
  }

  // EXERCISE 3: complete the definition of the following function that computes the length of
  // each String in a list, and returns the original Strings paired with their length.
  // For example:
  // 
  //   stringLengths (List ("the", "rain")) == List (("the", 3), ("rain", 4))
  //
  // You must not use recursion directly in the function.  You can use the "map" method 
  // of the List class.
  def stringLengths (xs:List[String]) : List[(String, Int)] = {
    xs match{
      case Nil => List()    //When xs is an empty list return an empty list default value
      case current :: next => (current,current.length) :: stringLengths(next) //When xs is a list of strings evaluate the 'current' string element's length using the .length() built in function and iteratively cons this String, Int tuple with the values returned by the recursive function calls on 'next'
    }

    /*val xsLength = xs.length
    if (xsLength == 0){List()}*/
  }

  // EXERCISE 4: complete the function definition for "delete1" that takes
  // an element "x" and a list "ys", then returns the list where any
  // occurrences of "x" in "ys" have been removed.  Your definition of
  // "delete1" MUST be recursive.
  // EXAMPLE:
  // - delete1 ("the", List ("the","the","was","a","product","of","the","1980s")) == List ("was","a","product","of","1980s")
  def delete1 [X] (x:X, ys:List[X]) : List[X] = {
    ys match{
      case Nil => List()    //When the list is empty return an empty list
      case front :: back =>         //Else the list is not empty and has a front and back duality
        if(front == x){             //If the front element equals value of the designated 'x' element enter block
          delete1(x,back)           //Execute delete function on front element, currently equal to designated 'x' element then call delete() function feeding 'x' and 'back' at parameter
        }else{                      //Else front != x so enter block
          front :: delete1(x,back)  //Current front element cons to the list of recursively iterated elements through the delete() function that haven't been equal to 'x'
        }
    }
  }


  // EXERCISE 5: complete the function definition for "delete2" below.  It must 
  // have the same behavior as "delete1".  It must be written using "for comprehensions" 
  // and not use recursion explicitly.
  def delete2 [X] (x:X, ys:List[X]) : List[X] = {
    for(y <- ys; if x != y)     //For every element 'y' in list 'ys' IF identified 'x' is not equal to 'y'
      yield y                   //Yield the value of 'y' in the returned 'ys' list
  }

  // EXERCISE 6: complete the function definition for "delete3" below.  It must 
  // have the same behavior as "delete1".  It must be written using the
  // builtin "filter" method for Lists and not use recursion explicitly.
  def delete3 [X] (x:X, ys:List[X]) : List[X] = {
    ys.filter(y => x != y)    //Using built-in 'filter' function on list 'ys' return the 'y' values where 'x' is not equal to 'y'
  }

  // EXERCISE 7: complete the function definition for "removeDupes1" below.
  // It takes a list as argument, then returns the same list with
  // consecutive duplicate elements compacted to a single element.  
  // Duplicate elements that are separated by at least one distinct
  // element should be left alone.
  // EXAMPLE:
  // - removeDupes1 (List (1,1,2,3,3,3,4,4,5,6,7,7,8,9,2,2,2,9)) == List (1,2,3,4,5,6,7,8,9,2,9)
  def removeDupes1 [X] (xs:List[X]) : List[X] = {
    def auxillary [X] (temp:X, ys:List[X]): List[X] = {
      ys match{
        case Nil =>                     //When 'ys' is an empty list
          if(temp != Nil){temp :: Nil}    //If 'temp' is not equal to an 'nil' return 'temp' cons with 'nil' denoting a single element list
          else{List()}                  //Otherwise return an empty list default value
        case front :: remainder =>      //When 'ys' is not an empty list denote 'front' as the first element in this iterable list and 'remainder' as the remaining elements
          if(front != temp){temp :: auxillary(front, remainder)}  //If the 'front' element is not equal to designated 'temp' temporarily identified element, enter block that cons's the 'temp' element to the list of recursively returned values of the auxillary() function that repeats this action
          else{auxillary(front,remainder)}  //Otherwise the 'front' element is equal to the 'temp' element denoting a duplicate which compacts the 'front' and 'temp' elements, and recursively calls auxillary() function to repeat these actions
      }
    }
    xs match{
      case Nil => List()         //When 'xs' is an empty list return an empty list default value
      case single :: Nil => List(single)  //When 'xs' is a single element list return that list
      case front :: remainder =>          //When 'xs' is a list of multiple elements
        auxillary(front, remainder)       //Recursively call the auxillary() helper function to create a temporary 'temp' variable to be used as a comparator for identifying duplicate elements in list 'xs'
    }
  }



  // EXERCISE 8: write a function "removeDupes2" that behaves like "removeDupes1",
  // but also includes a count of the number of consecutive duplicate
  // elements in the original list (thus producing a simple run-length
  // encoding).  The counts are paired with each element in the output
  // list.
  // EXAMPLE:
  // - removeDupes2 (List (1,1,2,3,3,3,4,4,5,6,7,7,8,9,2,2,2,9)) == List ((2,1),(1,2),(3,3),(2,4),(1,5),(1,6),(2,7),(1,8),(1,9),(3,2),(1,9))
  def removeDupes2 [X] (xs:List[X]) : List[(Int, X)] = {
    def auxillary [X] (temp:X, ys:List[X], count:Int): List[(Int,X)] = {
      ys match{
        case Nil =>                         //When 'ys' is nil
          if(temp != Nil){(count,temp) :: Nil}  //If 'temp' element is not equal to nil return the count of 'temp' and 'temp' element cons'd with 'Nil'
          else{Nil}                         //Otherwise return default value of 'Nil'
        case front :: remainder =>          //When 'ys' is not Nil
          if(front != temp){(count,temp) :: auxillary(front, remainder,1)}  //Repeated logic from RemoveDupes1 with the added field of 'count' which starts at count = 1
          else{auxillary(front, remainder, count+1)}    //Otherwise count is incremented by +1 for every instance of compacting 'front' element with 'temp' element
      }
    }
    xs match{
      case Nil => List()
      case single :: Nil => List((1, single))
      case front :: remainder =>
        auxillary(front, remainder, 1)
    }
  }



  // EXERCISE 9: complete the following definition of a function that splits a list
  // into a pair of two lists.  The offset for the the split position is given
  // by the Int argument.
  // The behavior is determined by:
  //
  //   for all n, xs:
  //     splitAt (n, xs) == (take (n, xs), drop (n, xs))   
  //
  // Your definition of "splitAt" must be recursive and must not use "take" or "drop".
  //
  // Your definition of "splitAt" must only traverse the list once.  So
  // you cannot define your own versions of "take"/"drop" and use them
  // (because that would entail one traversal of the list with "take"
  // and then a second traversal with "drop").
  def splitAt [X] (n:Int, xs:List[X]) : (List[X], List[X]) = {
    (n,xs) match{
      case (_,Nil) => (List(), List())    //When xs is an empty list return a pair of empty lists as default
      case (splitIndex, front :: remainder) =>      //When 'xs' is not empty and a splitIndex is provided (n)
        val (list1, list2) = splitAt(splitIndex - 1, remainder)    //Define a val pair for the two lists being created (list1, list2) which are equal to the recursive function call of splitAt() with parameters being the splitIndex - 1 (n - 1) and the 'remainder' of the list
        if (splitIndex > 0){(front :: list1, list2)}    //If our splitIndex is larger than 0 we have our 'front' element cons'd with the next 'front' element which makes up list1 up until the splitIndex where the rest of the list becomes list2
        else {(list1, front :: list2)}          //Otherwise the splitIndex equals 0 in which case the list1 is comprised solely of the element in index 0 of the list and list2 is the cons of the 'front' element (starting at index = 1) with the 'remainder' of the list
    }
  }


  // EXERCISE 10: complete the following definition of an "allDistinct" function that checks
  // whether all values in list are distinct.  You should use your "member" function defined earlier.
  // Your implementation must be recursive.
  // EXAMPLE:
  // - allDistinct (Nil) == true
  // - allDistinct (List (1,2,3,4,5)) == true
  // - allDistinct (List (1,2,3,4,5,1)) == false
  // - allDistinct (List (1,2,3,2,4,5)) == false
  def allDistinct (xs : List[Int]) : Boolean = {
    xs match{
      case List() => true          //When there's an empty list return a true value
      //case s::Nil => true     *for extraneous cases with list of one element
      case front :: remainder =>  //In the case there's an iterable list of elements
        if(member(front, remainder)){false} //If plugging the front and remainder elements into the function member() defined earlier yields a true value from that function call then not all elements are distinct, return false
        else{allDistinct(remainder)}        //Otherwise the front element is not a member of any of the previously identified elements
    }
  }
}

