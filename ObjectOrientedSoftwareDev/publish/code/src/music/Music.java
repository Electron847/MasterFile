package music;
class Music {
  private static double _pitch = 440;
  /** play note at the current pitch for the given duration
      in milliseconds (the initial pitch is A = 440 Hz) */
  public static void play(int duration) {
    System.out.println("play for " + duration/1000.0 + ": " + _pitch );
  }
  /** rest for given duration */
  public static void rest(int duration) {
    System.out.println("rest for " + duration/1000.0);
  }
  /** multiply the pitch frequency by the given factor
      (a factor less than one will lower the pitch) */
  public static void scalePitch(double factor) {
    _pitch *= factor;
  }
  /** reset the pitch to note A = 440 Hz */
  public static void reset() { 
    _pitch = 440;
  }
}
