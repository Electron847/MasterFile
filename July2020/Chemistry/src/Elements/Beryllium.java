package Elements;

final public class Beryllium implements Element {
    private int _protons = 4;
    private int _neutrons = 5;
    private int _electrons = 4;
    private int[] _isotopes;
    private String _orbConfig = "1s2-2s2";


    public Beryllium() {}

    public int protons () {
        return _protons;
    }
    public int neutrons () {
        return _neutrons;
    }
    public int electrons () {
        return _electrons;
    }
    public int[] electronIsotopes () {
        return _isotopes;
    }
    public String orbitalConfig () { return _orbConfig; }
    public String elementCategory() {
        String o = null;
        return o; }
    public double mass(){
        return 9.0122;
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
