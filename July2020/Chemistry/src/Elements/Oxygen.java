package Elements;

public class Oxygen implements Element {
    private int _protons = 8;
    private int _neutrons = 8;
    private int _electrons = 8;
    private int[] _isotopes;
    private String _orbConfig = "1s2-2s2-2p4";
    public Oxygen() {}

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
        _isotopes = new int[3];
        _isotopes[0] = 8;
        _isotopes[1] = 9;
        _isotopes[2] = 10;
        for (int i = 0; i < _isotopes.length; i++){
            // System.out.print(_isotopes[i]);
        }
        return _isotopes;
    }
    public String elementCategory() {
        String o = null;
        return o; }
    public String orbitalConfig () {
        return _orbConfig;
    }
    public double mass(){
        return 15.9999;
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
