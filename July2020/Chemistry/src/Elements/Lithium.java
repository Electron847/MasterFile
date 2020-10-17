package Elements;

final public class Lithium implements Element {
    private int _protons = 3;
    private int _neutrons = 3;
    private int _electrons = 3;
    private int[] _isotopes;

    private String _orbConfig = "1s2-2s1";
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
    public int[] electronIsotopes () { return _isotopes; }
    public String elementCategory() {
        String o = null;
        return o; }
    public String orbitalConfig () {
        return _orbConfig;
    }
    public double mass(){
        return 6.94;
    }
    public double meltingPointCelsius() {
        return 0;
    }

    public double meltingPointFarenheit() {
        return 0;
    }

    public double boilingPointCelsius() {
        return 0;
    }

    public double boilingPointFarentheit() {
        return 0;
    }
}
