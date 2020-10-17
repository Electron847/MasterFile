package template.sort;

/* This example is from Robert C. Martin */
abstract class BubbleSorter {
  private int _operations = 0;
  protected int _length = 0;
  protected int doSort() {
    _operations = 0;
    if (_length <= 1) return _operations;
    for (int nextToLast = _length-2; nextToLast >= 0; nextToLast--)
      for (int index = 0; index <= nextToLast; index++) {
        if (outOfOrder(index)) swap(index);
        _operations++;
      }
    return _operations;
  }
  protected abstract void swap(int index);
  protected abstract boolean outOfOrder(int index);
}

class IntBubbleSorter extends BubbleSorter {
  private int[] _array = null;
  public int sort(int [] array) {
    _array = array;
    _length = _array.length;
    return doSort();
  }
  protected void swap(int index) {
    int temp = _array[index];
    _array[index] = _array[index+1];
    _array[index+1] = temp;
  }
  protected boolean outOfOrder(int index) {
    return (_array[index] > _array[index+1]);
  }
}

class DoubleBubbleSorter extends BubbleSorter {
  private double[] _array = null;
  public int sort(double [] array) {
    _array = array;
    _length = _array.length;
    return doSort();
  }
  protected void swap(int index) {
    double temp = _array[index];
    _array[index] = _array[index+1];
    _array[index+1] = temp;
  }
  protected boolean outOfOrder(int index) {
    return (_array[index] > _array[index+1]);
  }
}
