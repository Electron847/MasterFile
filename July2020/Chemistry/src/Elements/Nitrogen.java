package Elements;

public class Nitrogen implements Element {
    private int _protons = 7;
    private int _neutrons = 7;
    private int _electrons = 7;
    private int[] _isotopes;
    private String _orbConfig = "1s2-2s2-2p3";
    public Nitrogen() {}

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
        _isotopes[0] = 6;
        _isotopes[1] = 7;
        _isotopes[1] = 8;
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
        return 14.00674;
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
