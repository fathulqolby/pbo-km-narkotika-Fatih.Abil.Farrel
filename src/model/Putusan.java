package model;

// 1. Menerapkan IMPLEMENTS (Interface)
public class Putusan implements IStatistik {
    protected String nomorPerkara;
    protected String namaTerdakwa;
    protected int vonisHukuman;

    // 2. Menerapkan STATIC FIELD (Menyimpan jumlah total objek yang dibuat)
    public static int totalPutusanDibuat = 0;

    // 3A. CONSTRUCTOR 1 (No-argument Constructor)
    public Putusan() {
        totalPutusanDibuat++; // Setiap kali data baru dibuat, angka ini bertambah
    }

    // 3B. CONSTRUCTOR 2 (Parameterized Constructor)
    public Putusan(String nomorPerkara, String namaTerdakwa, int vonisHukuman) {
        this.nomorPerkara = nomorPerkara;
        this.namaTerdakwa = namaTerdakwa;
        this.vonisHukuman = vonisHukuman;
        totalPutusanDibuat++;
    }

    // Getter dan Setter
    public String getNomorPerkara() { return nomorPerkara; }
    public void setNomorPerkara(String nomorPerkara) { this.nomorPerkara = nomorPerkara; }

    public String getNamaTerdakwa() { return namaTerdakwa; }
    public void setNamaTerdakwa(String namaTerdakwa) { this.namaTerdakwa = namaTerdakwa; }

    public int getVonisHukuman() { return vonisHukuman; }
    public void setVonisHukuman(int vonisHukuman) { this.vonisHukuman = vonisHukuman; }

    public String getDetailPutusan() {
        return "No: " + nomorPerkara + " | Terdakwa: " + namaTerdakwa + " | Vonis: " + vonisHukuman + " bulan";
    }

    // 4. Menerapkan OVERRIDE dari Interface
    @Override
    public void tampilkanInformasi() {
        System.out.println("Nomor Perkara: " + nomorPerkara + " - Status: Tercatat");
    }

    // 5. Menerapkan METHOD OVERLOADING
    public void tampilkanInformasi(boolean tampilkanDetail) {
        if (tampilkanDetail) {
            System.out.println(getDetailPutusan());
        } else {
            tampilkanInformasi(); // Memanggil method tanpa parameter
        }
    }
}