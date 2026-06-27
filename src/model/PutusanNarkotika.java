package model;

public class PutusanNarkotika extends Putusan {
    private String jenisNarkotika;
    private double beratBarangBukti;

    public PutusanNarkotika() {
        super();
    }

    public String getJenisNarkotika() { return jenisNarkotika; }
    public void setJenisNarkotika(String jenisNarkotika) { this.jenisNarkotika = jenisNarkotika; }

    public double getBeratBarangBukti() { return beratBarangBukti; }
    public void setBeratBarangBukti(double beratBarangBukti) { this.beratBarangBukti = beratBarangBukti; }

    @Override
    public String getDetailPutusan() {
        return super.getDetailPutusan() + " | Jenis: " + jenisNarkotika + " (" + beratBarangBukti + " gr)";
    }
}