package Elements;

public class Boron implements Element {
    private int _protons = 5;
    private int _neutrons = 6;
    private int _electrons = 5;
    private int[] _isotopes;
    private String _orbConfig = "1s2-2s2-2p1";
    public Boron() {}

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
    public String elementCategory() {
        String o = null;
        return o; }
    public String orbitalConfig () {
        return _orbConfig;
    }
    public double mass(){
        return 10.821;
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
