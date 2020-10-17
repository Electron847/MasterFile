package simplebdd.bool;

import util.HashMap3;

public interface BoolPredFactory {
  public BoolPred buildVar (String name);
}

class BPFactory implements BoolPredFactory {
  private static final HashMap3 flyweight = new HashMap3 ();
  private static int _COUNT = 0;
  private static pBoolPred min(pBoolPred p1, pBoolPred p2, pBoolPred p3) {
    pBoolPred result = p1;
    if (p2.compareTo(result) < 0) { result =  p2; }
    if (p3.compareTo(result) < 0) { result =  p3; }
    return result;
  }
  static BoolPred buildCond (Cond b, pBoolPred p, pBoolPred q) {
    BoolPred result = (pBoolPred)(flyweight.get (b, p, q));
    if (result == null) {
      // Since b is a Cond, n cannot be 0 or 1
      // But b have the same name as p or q
      String n = min(b, p, q).name();
      // The following two lines assign t and f properly but are hard to read
      // BoolPred t = (b.name()!=n ? b : b._t).ite((p.name()!=n ? p : ((Cond)p)._t), (q.name()!=n ? q : ((Cond)q)._t));
      // BoolPred f = (b.name()!=n ? b : b._f).ite((p.name()!=n ? p : ((Cond)p)._f), (q.name()!=n ? q : ((Cond)q)._f));
      BoolPred bt = (b.name() != n) ? b : b._t;
      BoolPred bf = (b.name() != n) ? b : b._f;
      BoolPred pt = (p.name() != n) ? p : ((Cond)p)._t;
      BoolPred pf = (p.name() != n) ? p : ((Cond)p)._f;
      BoolPred qt = (q.name() != n) ? q : ((Cond)q)._t;
      BoolPred qf = (q.name() != n) ? q : ((Cond)q)._f;
      BoolPred t = bt.ite(pt, qt);
      BoolPred f = bf.ite(pf, qf);
      if (t == f) {
        result = t;
      } else {
        result = (BoolPred)(flyweight.get (n, t, f));
        if (result == null) { 
          result = new Cond (n + (++_COUNT), n, t, f);
          flyweight.put (n, t, f, result);
        }
      }
      flyweight.put (b, p, q, result);
    }
    return result;
  }
  public BoolPred buildVar (String name) {
    BoolPred result = (BoolPred)(flyweight.get (name, BoolPred.T, BoolPred.F));
    if (result == null) { 
      result = new Cond (name + (++_COUNT), name, BoolPred.T, BoolPred.F);
      flyweight.put (name, BoolPred.T, BoolPred.F, result);
    }
    return result;
  }
}
