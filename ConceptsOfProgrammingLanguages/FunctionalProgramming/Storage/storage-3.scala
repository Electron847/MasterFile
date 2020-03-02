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
 * 3. You MAY use (re)assignment to variables and "val" and "var" declarations (you will have to).
 *
 * 4. In all of the exercises in this file, you can only add code between the
 *    existing "{...}" for the val/def.  That is, you cannot add a new val/def directly
 *    inside the "storage" object.
 *
 * SUBMISSION
 *
 * 1. Submit this file on D2L before the deadline.
 *
 * 2. Late submissions will not be permitted because solutions will be discussed in class.
 *
 */


object storage {

  // EXERCISE 1: Complete the following definition, so that "constant5" is a function that returns 5
  // whenever it is invoked.
  val constant5: () => Int = { () => 5 }

  // EXERCISE 2: Complete the following definition, so that "constant" is a function that when
  // invoked with integer n returns a function that returns n whenever it is invoked.
  val constant : Int => () => Int = {
    n: Int => () => n
  }

  // EXERCISE 3: Complete the following definition, so that "counter0" is a (stateful) function that
  // returns 0 when it is first invoked, then 1, then 2, etc.
  // REMEMBER: you can use "var" but everything you add has to be inside the "{...}" body of "counter0".
  // This rule applies throughout this assignment.
  val counter0 : () => Int =
  {
    var x = 0; () => {x = x + 1; x}
  }

  // EXERCISE 4: Complete the following definition, so that "counter" is a (stateless) function that
  // when invoked with integer n returns a (stateful) function that returns n when it is first
  // invoked, then n+1, then n+2, etc.  The counters must be independent, i.e., running "counter (0)"
  // twice should yield two functions that do not interfere with one another's state.
  val counter : Int => () => Int =
  {
     n:Int => {var nCount = n; () =>
     {
       val result = nCount
       nCount = nCount + 1
       result}
     }
  }

  // EXERCISE 5: Complete the following definition, so that "getAndSet" is a (stateless) function
  // that when invoked with integer n returns a pair of functions (that share state) that allow
  // reading and writing a var that is initialized with integer n.  The first function in the pair
  // should be the reader.  The second function in the pair should be the writer.  For example, the
  // following expression should return 10: { val (get, set) = getAndSet (5); set (10); get () }
  // Multiple calls to "getAndSet" should yield independent pairs, i.e., the first pair returned
  // should not share any state with the second pair returned.
  val getAndSet : Int => (() => Int, Int => Unit) =
  {
    n =>                                  //invoking the function with 'n'
    {
      var parameter = n                   //initializing a var named 'parameter' with integer 'n'
      def get: () => Int = () =>          //the reader 'get' is a parameterless function that returns an Int
      {
        parameter                         //Int returned within this functions scope
      }
      def set: Int => Unit = y =>         //the writer 'set' takes an Int and returns a void 'Unit' - Int initialised to variable name 'y'
      {
        parameter = y                     //reader function Int 'parameter' variable becomes the writer Int 'y'
      }
      (get,set)
    }
  }

  // EXERCISE 6: Complete the following definition, so that "getAndSetSpy" is a (stateful) function
  // that when invoked it returns a pair.  The second component of the pair should behave like
  // "getAndSet" above (with the exception noted next).  The first component of the pair is a
  // function that, when invoked, returns the total number of times that a "set" call has been made.
  // That number should cover all calls to "set" made in all pairs created via "getAndSetSpy".  That is,
  // the total number is a piece of state shared all "set" functions created via "getAndSetSpy".
  val getAndSetSpy : () => (() => Int, Int => (() => Int, Int => Unit)) = {
    var result = 0
    () => {
      val getterSetter: Int =>(() => Int, Int => Unit) = {
        n =>
          var x = n
          def get: () => Int = () =>
          {
            x
          }
          def set: Int => Unit = (y: Int) =>
          {
            x = y
            result = result + 1
          }
          (get,set)
      }
      val cntr: () => Int = () => result
      (cntr,getterSetter)
    }
  }
}

