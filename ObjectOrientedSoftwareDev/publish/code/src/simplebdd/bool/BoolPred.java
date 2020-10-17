package simplebdd.bool;

/**
 * An implementation of Boolean Predicates using BDDs.
 * The functions which take arguments of type BoolPred may throw a
 * ClassCastException if given an object that was not created by
 * BoolPred.factory().
 */
public interface BoolPred {
  public static BoolPred T = new True ();
  public static BoolPred F = new False ();
  public static BoolPredFactory factory = new BPFactory ();
  public static BoolPredFunctions functions = new BPFunctions ();
  
  public BoolPred not  ();
  public BoolPred and  (BoolPred p);
  public BoolPred or   (BoolPred p);
  public BoolPred xor  (BoolPred p);
  public BoolPred impl (BoolPred p);
  public BoolPred iff  (BoolPred p);
  public BoolPred ite  (BoolPred p, BoolPred q);
  
  public String name();
}

interface pBoolPred extends BoolPred, Comparable<BoolPred> {
  public String id();
  public void initToGraphString();
  public void toGraphString(StringBuilder b);
  public int compareTo (BoolPred that);
}

// in the comparison order, T and F are biggest (and they are both bigger than each other!)
class True implements pBoolPred {
  public BoolPred not  ()           { return F; }
  public BoolPred and  (BoolPred p) { return p; }
  public BoolPred or   (BoolPred p) { return this; }
  public BoolPred xor  (BoolPred p) { return p.not (); }
  public BoolPred impl (BoolPred p) { return p; }
  public BoolPred iff  (BoolPred p) { return p; }
  public BoolPred ite  (BoolPred p, BoolPred q) { return p; }
  
  public String name()      { return "0"; }
  public String id()        { return "0"; }
  public String toString () { return "true"; }
  
  public int compareTo (BoolPred that) { return 1; }
  public void initToGraphString() {}
  public void toGraphString(StringBuilder b) {}
}

class False implements pBoolPred {
  public BoolPred not  ()           { return T; }
  public BoolPred and  (BoolPred p) { return this; }
  public BoolPred or   (BoolPred p) { return p; }
  public BoolPred xor  (BoolPred p) { return p; }
  public BoolPred impl (BoolPred p) { return T; }
  public BoolPred iff  (BoolPred p) { return p.not (); }
  public BoolPred ite  (BoolPred p, BoolPred q) { return q; }
  
  public String name()      { return "1"; }
  public String id()        { return "1"; }
  public String toString () { return "false"; }
  
  public int compareTo (BoolPred that) { return 1; }
  public void initToGraphString() {}
  public void toGraphString(StringBuilder b) {}
}

class Cond implements pBoolPred {
  private final String _id;
  private final String _name;
  final pBoolPred _t;
  final pBoolPred _f; 
  Cond (String id, String name, BoolPred t, BoolPred f) {
    _id = id; _name = name; _t = (pBoolPred)t; _f = (pBoolPred)f;
  }
  
  public final BoolPred not  ()           { return ite (F, T); }
  public final BoolPred and  (BoolPred p) { return ite (p, F); }
  public final BoolPred or   (BoolPred p) { return ite (T, p); }
  public final BoolPred xor  (BoolPred p) { return ite (p.not (), p); }
  public final BoolPred impl (BoolPred p) { return ite (p, T); }
  public final BoolPred iff  (BoolPred p) { return ite (p, p.not ()); }
  public final BoolPred ite  (BoolPred p, BoolPred q) {
    return BPFactory.buildCond (this, (pBoolPred)p, (pBoolPred)q);
  }
  
  public String name() { return _name; }
  public String id() { return _id; }
  public String toString () { 
    if ( _t == T && _f == F) {
      return _name;
    } else if (_t == F && _f == T) {
      return "!" + _name;
    } else {
      return "(" + _name + " ? " + _t + " : " + _f + ")";
    }
  }

  public int compareTo (BoolPred that) { return _name.compareTo (that.name()); }

  private boolean _printed = false;
  public void initToGraphString() {
    _printed=false;
    _t.initToGraphString();
    _f.initToGraphString();
  }
  public void toGraphString(StringBuilder b) {
    if (!_printed) { b.append (id() + " ? " + _t.id() + " : " + _f.id() + "\n"); }
    _printed = true; 
    _t.toGraphString(b);
    _f.toGraphString(b);
  }
}

