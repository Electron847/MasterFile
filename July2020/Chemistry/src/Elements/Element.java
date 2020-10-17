package Elements;

public interface Element{
    public int protons();
    public int neutrons();
    public int electrons();
    public int[] electronIsotopes();
    public double meltingPointCelsius();
    public double meltingPointFarenheit();
    public double boilingPointCelsius();
    public double boilingPointFarentheit();
    public double mass();
    public String orbitalConfig();
    //public String stp_phase();
    //public String elementBlock();
    //public String state();
}
