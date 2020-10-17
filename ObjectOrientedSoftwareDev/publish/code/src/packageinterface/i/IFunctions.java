package packageinterface.i;

public interface IFunctions {
  public I newI();
  public void pseudoStaticFunction(I x);
}

class IFunctionsObj implements IFunctions {
  public I newI() {
    return new IObj();
  }
  public void pseudoStaticFunction(I xI) {
    pI x = (pI) xI;
    x.utilityMethod(null);
  }
}
