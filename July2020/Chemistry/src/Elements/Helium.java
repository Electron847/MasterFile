package Elements;

final public class Helium implements Element{
    private int _protons = 2;
    private int _neutrons = 2;
    private int _electrons = 2;
    private int[] _isotopes;
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
    public String elementCategory() {
        String o = null;
        return o; }
    public int[] electronIsotopes () { return _isotopes; }
    public String orbitalConfig () {
        return _orbConfig;
    }
    public double mass(){
        return 4.0026;
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
