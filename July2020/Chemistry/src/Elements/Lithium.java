package Elements;

final public class Lithium implements Element {
    private int _protons = 3;
    private int _neutrons = 3;
    private int _electrons = 3;
    private String _orbConfig = "1s1";
    public Lithium() {}

    public int protons () {
        return _protons;
    }
    public int neutrons () {
        return _neutrons;
    }
    public int electrons () {
        return _electrons;
    }
    public String orbitalConfig () {
        return _orbConfig;
    }
    public double mass(){
        return 6.94;
    }
}
