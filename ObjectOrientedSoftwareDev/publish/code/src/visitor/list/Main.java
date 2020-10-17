package visitor.list;

/* public */
interface List {
  public <T> T accept(ListVisitor<T> v);
}

/* public */
class ListF {
  private ListF() {}
  public static final List nil = new Nil(); /* Singleton */
  public static final List cons(int hd, List tl) /* Factory */ {
    return new Cons(hd, tl);
  }
}

/* public */
interface ListVisitor<T> {
  public T visitNil();
  public T visitCons(int hd, List tl);
}

/*
 *************************************************************************
 * List classes.
 *************************************************************************
 */
class Nil implements List {
  Nil() {}
  public String toString() { return "nil"; }
  public <T> T accept(ListVisitor<T> v) {
    return v.visitNil();
  }
}

class Cons implements List {
  private final int _hd;
  private final List _tl;
  Cons(int hd, List tl) { _hd = hd; _tl = tl; }
  public String toString() { return _hd + "::" + _tl.toString(); }
  public <T> T accept(ListVisitor<T> v) {
    return v.visitCons(_hd, _tl);
  }
}

/*
 *************************************************************************
 * Visitor classes.
 * The visitor to a Cons is responsible for visiting the tl.
 *************************************************************************
 */
class Sum implements ListVisitor<Integer> {
  public Integer visitNil() { return 0; }
  public Integer visitCons(int hd, List tl) {
    return hd + tl.accept(this);
  }
}

class Reverse implements ListVisitor<List> {
  private List result = ListF.nil; // use a field to accumulate the value
  public List visitNil() { return result; }
  public List visitCons(int hd, List tl) {
    result = ListF.cons(hd, result);
    return tl.accept(this);
  }
}

/*
 *************************************************************************
 * A test case.
 *************************************************************************
 */
public class Main {
  public static void main(String[] args) {
    List test = ListF.cons(1, ListF.cons(2, ListF.cons(3, ListF.nil)));
    System.out.println(test);

    System.out.println(test.accept(new Sum()));

    System.out.println(test.accept(new Reverse()));
  }
}


/*
 *************************************************************************
 * Here is the corresponding SML code.
 * It is intended to match the Java as closely as possible.
 *************************************************************************
datatype List = Nil | Cons of int * List

fun toString (this : List) : string =
    case this of
        Nil => "nil"
      | Cons(hd, tl) => Int.toString(hd) ^ "::" ^ toString(tl)

fun sum (acceptor : List) : int =
    case acceptor of
        Nil => 0
      | Cons(hd, tl) => hd + sum(tl)

fun reverse (acceptor : List) : List =
    let fun reverseAux (acceptor : List, result : List) =
            case acceptor of
                Nil => result
              | Cons(hd, tl) => reverseAux(tl, Cons(hd,result))
    in
        reverseAux (acceptor, Nil)
    end

fun main () : unit =
    let
        val testList = Cons(1, Cons(2, Cons(3, Nil)))
        val _ = print(toString(testList) ^ "\n")
        val _ = print(Int.toString(sum(testList)) ^ "\n")
        val _ = print(toString(copy(testList)) ^ "\n")
        val _ = print(toString(reverse(testList)) ^ "\n")
    in
        ()
    end

 *************************************************************************
 */
