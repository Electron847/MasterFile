aspect TraceCalls {
  pointcut nonstatic(): call(* ListVisitor.*(..)) || call(* List.accept(..));
  Object around(Object caller, Object callee) :
    nonstatic() && this(caller) && target(callee)
  {
    System.out.println (">>> " + caller + " calls " + callee);
    return proceed(caller, callee);
  }
}
