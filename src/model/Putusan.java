package model;

public class Putusan {
    // Menggunakan protected agar bisa diakses oleh class anak (Inheritance)
    protected String nomorPerkara;
    protected String namaTerdakwa;
    protected int vonisHukuman;

    public Putusan() {}

    public String getNomorPerkara() { return nomorPerkara; }
    public void setNomorPerkara(String nomorPerkara) { this.nomorPerkara = nomorPerkara; }

    public String getNamaTerdakwa() { return namaTerdakwa; }
    public void setNamaTerdakwa(String namaTerdakwa) { this.namaTerdakwa = namaTerdakwa; }

    public int getVonisHukuman() { return vonisHukuman; }
    public void setVonisHukuman(int vonisHukuman) { this.vonisHukuman = vonisHukuman; }

    // Method ini akan ditimpa (di-override) oleh class anak untuk Polymorphism
    public String getDetailPutusan() {
        return "No: " + nomorPerkara + " | Terdakwa: " + namaTerdakwa + " | Vonis: " + vonisHukuman + " bulan";
    }
}