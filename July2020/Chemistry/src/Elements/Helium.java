package Elements;

final public class Helium implements Element{
    private int _protons = 2;
    private int _neutrons = 2;
    private int _electrons = 2;
    private String _orbConfig = "1s2";
    public Helium() {}

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
        return 4.0026;
    }
}
