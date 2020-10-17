package packageinterface.i;

/**
 * An interface.
 * The methods only accept implementations that are created using this interface.
 */
public interface I {
  // By convention, all types mentioned in the public interface must be
  // public types, even when not strictly necessary.  For example, it would
  // be allowable to declare aConstant at type pI, but javadoc is too stupid
  // to print sensible documentation...
  public static IFunctions f = new IFunctionsObj();
  public static I aConstant = new IObj();
  public void publicMethod(I that);
}

interface pI extends I {
  // Package-private interfaces may mention the package-private types.
  // Although utilityMethod is "public", it can only be called inside this
  // package, since the interface itself is package-private.
  public void utilityMethod(pI x);
}

class IObj implements pI {
  public void publicMethod(I thatI) {
    pI that = (pI) thatI;
    this.utilityMethod(that);
    that.utilityMethod(this);
  }
  public void utilityMethod(pI x) {
    /* ... */
  }
}
    
  
  
