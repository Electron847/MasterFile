package project.model.swingvisualizer;

/**
 * Pair of a model element <code>x</code> and a translator <code>t</code>.
 */
class Element<T> {
  T x;
  Translator t;
  Element(T x, Translator t) {
    this.x = x;
    this.t = t;
  }
}

