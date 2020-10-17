package util;

import java.util.HashMap;

/**
 * An implementation of a hash table where each value has a composite key
 * generated from 3 objects.  Acts like the Java HashMap except that:
 * <ul>
 * <li>None of the keys or values are allowed to be null.</li>
 * <li>None of the values are allowed to be arrays.</li>
 * <li>It uses == rather than .equals to compare objects for equality.</li>
 * </ul>
 * Hack hack hack for space efficiency...
 */
public class HashMap3 {
  
  Object[] values = new Object[128];
  Object[] keys1 = new Object[128];
  Object[] keys2 = new Object[128];
  Object[] keys3 = new Object[128];
  int size=0;
  
  public void put (Object key1, Object key2, Object key3, Object value) {
    if (size > values.length) {
      grow ();
    }
    putNoGrow (key1, key2, key3, value);
  }
  
  public void grow () {
    Object[] oldValues = this.values;
    Object[] oldKeys1 = this.keys1;
    Object[] oldKeys2 = this.keys2;
    Object[] oldKeys3 = this.keys3;
    this.values = new Object[oldValues.length * 2];
    this.keys1 = new Object[oldValues.length * 2];
    this.keys2 = new Object[oldValues.length * 2];
    this.keys3 = new Object[oldValues.length * 2];
    for (int i=0; i<oldValues.length; i++) {
      if (oldValues[i] == null) { 
        continue;
      } else if (oldValues[i] instanceof Object[]) {
        Object[] valuesBucket = (Object[])(oldValues[i]);
        Object[] keys1Bucket = (Object[])(oldKeys1[i]);
        Object[] keys2Bucket = (Object[])(oldKeys2[i]);
        Object[] keys3Bucket = (Object[])(oldKeys3[i]);
        for (int j=0; j<valuesBucket.length; j++) {
          if (valuesBucket[j] == null) {
            break;
          }
          putNoGrow (keys1Bucket[j], keys2Bucket[j], keys3Bucket[j], valuesBucket[j]);
        }
      } else {
        putNoGrow (oldKeys1[i], oldKeys2[i], oldKeys3[i], oldValues[i]);
      }
    }
  }
  
  public void putNoGrow (Object key1, Object key2, Object key3, Object value) {
    size++;
    int hash = hash3 (key1, key2, key3) % values.length;
    if (values[hash] == null) { 
      keys1[hash] = key1;
      keys2[hash] = key2;
      keys3[hash] = key3;
      values[hash] = value;
      return;
    } else if (values[hash] instanceof Object[]) {
      Object[] valuesBucket = (Object[])(values[hash]);
      Object[] keys1Bucket = (Object[])(keys1[hash]);
      Object[] keys2Bucket = (Object[])(keys2[hash]);
      Object[] keys3Bucket = (Object[])(keys3[hash]);
      for (int i=0; i<valuesBucket.length; i++) {
        if (valuesBucket[i] == null) {
          keys1Bucket[i] = key1;
          keys2Bucket[i] = key2;
          keys3Bucket[i] = key3;
          valuesBucket[i] = value;
          return;
        }
      }
      Object[] newValuesBucket = new Object[valuesBucket.length * 2];
      Object[] newKeys1Bucket = new Object[valuesBucket.length * 2];
      Object[] newKeys2Bucket = new Object[valuesBucket.length * 2];
      Object[] newKeys3Bucket = new Object[valuesBucket.length * 2];
      for (int i=0; i<valuesBucket.length; i++) {
        newValuesBucket[i] = valuesBucket[i];
        newKeys1Bucket[i] = keys1Bucket[i];
        newKeys2Bucket[i] = keys2Bucket[i];
        newKeys3Bucket[i] = keys3Bucket[i];
      }
      newValuesBucket[valuesBucket.length] = value;
      newKeys1Bucket[valuesBucket.length] = key1;
      newKeys2Bucket[valuesBucket.length] = key2;
      newKeys3Bucket[valuesBucket.length] = key3;
      values[hash] = newValuesBucket;
      keys1[hash] = newKeys1Bucket;
      keys2[hash] = newKeys2Bucket;
      keys3[hash] = newKeys3Bucket;
      return;
    } else {
      values[hash] = new Object[]{ values[hash], value };
      keys1[hash] = new Object[]{ keys1[hash], key1 };
      keys2[hash] = new Object[]{ keys2[hash], key2 };
      keys3[hash] = new Object[]{ keys3[hash], key3 };
      return;
    }
  }
  
  public Object get (Object key1, Object key2, Object key3) {
    int hash = hash3 (key1, key2, key3) % values.length;
    if (values[hash] == null) { 
      return null;
    } else if (values[hash] instanceof Object[]) {
      Object[] valuesBucket = (Object[])(values[hash]);
      Object[] keys1Bucket = (Object[])(keys1[hash]);
      Object[] keys2Bucket = (Object[])(keys2[hash]);
      Object[] keys3Bucket = (Object[])(keys3[hash]);
      for (int i=0; i<valuesBucket.length; i++) {
        if (keys1Bucket[i] == key1
            && keys2Bucket[i] == key2
            && keys3Bucket[i] == key3) {
          return valuesBucket[i];
        }
      }
      return null;
    } else if (keys1[hash] == key1
               && keys2[hash] == key2
               && keys3[hash] == key3) {
      return values[hash];
    } else {
      return null;
    }
  }
  
  public static int hash3 (Object key1, Object key2, Object key3) {
    return key1.hashCode () + 5 * key2.hashCode () + 13 * key3.hashCode ();
  }
  
}
