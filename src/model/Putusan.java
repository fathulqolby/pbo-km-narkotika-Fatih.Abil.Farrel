package model;

public class Putusan {
    // 1. Encapsulation
    private String nomorPerkara;
    private String pengadilan;
    private String tanggalPutusan;
    private String namaTerdakwa;
    private int umurTerdakwa;
    private String jenisNarkotika;
    private double beratBarangBukti; // dalam gram
    private String pasalDilanggar;
    private String peranTerdakwa;
    private int vonisHukuman; // dalam bulan
    private double vonisDenda;
    private String namaHakim;

    // Static field untuk menghitung jumlah objek yang dibuat
    private static int jumlahDibuat = 0;

    // 2. Constructor 1: No-argument constructor
    public Putusan() {
        jumlahDibuat++;
    }

    // 3. Constructor 2: Parameterized constructor
    public Putusan(String nomorPerkara, String namaTerdakwa, String jenisNarkotika, int vonisHukuman) {
        this.nomorPerkara = nomorPerkara;
        this.namaTerdakwa = namaTerdakwa;
        this.jenisNarkotika = jenisNarkotika;
        this.vonisHukuman = vonisHukuman;
        jumlahDibuat++;
    }

    // 4. Method Overloading 1: Menampilkan ringkasan
    public void tampilkan() {
        System.out.println("No: " + nomorPerkara + " | Terdakwa: " + namaTerdakwa + " | Vonis: " + vonisHukuman + " bulan");
    }

    // 5. Method Overloading 2: Menampilkan detail (nama method sama, parameter berbeda)
    public void tampilkan(boolean detail) {
        if (detail) {
            System.out.println("=== Detail Putusan ===");
            System.out.println("Nomor Perkara    : " + nomorPerkara);
            System.out.println("Nama Terdakwa    : " + namaTerdakwa);
            System.out.println("Jenis Narkotika  : " + jenisNarkotika);
            System.out.println("Berat Bukti      : " + beratBarangBukti + " gram");
            System.out.println("Vonis Hukuman    : " + vonisHukuman + " bulan");
            System.out.println("Vonis Denda      : Rp " + vonisDenda);
        } else {
            tampilkan(); // panggil method overloading pertama
        }
    }

    // 6. Static Method
    public static int getJumlahDibuat() {
        return jumlahDibuat;
    }

    // 7. Kategori Hukuman (Fitur tambahan untuk logika bisnis)
    public String getKategoriHukuman() {
        if (vonisHukuman < 12) return "Ringan";
        else if (vonisHukuman <= 60) return "Sedang";
        else return "Berat";
    }

    // --- GETTER & SETTER
    public String getPengadilan() {
        return pengadilan;
    }

    public void setPengadilan(String pengadilan) {
        this.pengadilan = pengadilan;
    }

    public String getTanggalPutusan() {
        return tanggalPutusan;
    }

    public void setTanggalPutusan(String tanggalPutusan) {
        this.tanggalPutusan = tanggalPutusan;
    }

    public int getUmurTerdakwa() {
        return umurTerdakwa;
    }

    public void setUmurTerdakwa(int umurTerdakwa) {
        this.umurTerdakwa = umurTerdakwa;
    }

    public String getPasalDilanggar() {
        return pasalDilanggar;
    }

    public void setPasalDilanggar(String pasalDilanggar) {
        this.pasalDilanggar = pasalDilanggar;
    }

    public String getPeranTerdakwa() {
        return peranTerdakwa;
    }

    public void setPeranTerdakwa(String peranTerdakwa) {
        this.peranTerdakwa = peranTerdakwa;
    }

    public String getNamaHakim() {
        return namaHakim;
    }

    public void setNamaHakim(String namaHakim) {
        this.namaHakim = namaHakim;
    }

    public static void setJumlahDibuat(int jumlahDibuat) {
        Putusan.jumlahDibuat = jumlahDibuat;
    }

    public String getNomorPerkara() { return nomorPerkara; }
    public void setNomorPerkara(String nomorPerkara) { this.nomorPerkara = nomorPerkara; }

    public String getNamaTerdakwa() { return namaTerdakwa; }
    public void setNamaTerdakwa(String namaTerdakwa) { this.namaTerdakwa = namaTerdakwa; }

    public String getJenisNarkotika() { return jenisNarkotika; }
    public void setJenisNarkotika(String jenisNarkotika) { this.jenisNarkotika = jenisNarkotika; }

    public int getVonisHukuman() { return vonisHukuman; }
    public void setVonisHukuman(int vonisHukuman) { this.vonisHukuman = vonisHukuman; }

    public double getBeratBarangBukti() { return beratBarangBukti; }
    public void setBeratBarangBukti(double beratBarangBukti) { this.beratBarangBukti = beratBarangBukti; }

    public double getVonisDenda() { return vonisDenda; }
    public void setVonisDenda(double vonisDenda) { this.vonisDenda = vonisDenda; }

}