package clone.stack;
public final class IntegerStack implements Cloneable {
  private int[] _buffer;
  private int _top;
  
  public IntegerStack(int maxContents) {
    _buffer = new int[maxContents];
    _top = -1;
  }
  public void push(int val) {
    _buffer[++_top] = val;
  }
  public int pop() {
    return _buffer[_top--];
  }
  public Object clone() {
    try {
      IntegerStack result = (IntegerStack) super.clone();
      result._buffer = (int[]) _buffer.clone();
      return result;
    } catch (CloneNotSupportedException e) {
      // cannot happen
      throw new RuntimeException(e);
    }
  } 
}
