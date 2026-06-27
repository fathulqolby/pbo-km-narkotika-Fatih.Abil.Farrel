package view;

import controller.InputHandler;
import controller.KnowledgeController;
import model.Putusan;
import java.util.ArrayList;

public class ConsoleView {
    private KnowledgeController controller;
    private InputHandler input;

    public ConsoleView() {
        this.controller = new KnowledgeController();
        this.input = new InputHandler();
    }

    public void mulai() {
        boolean jalan = true;
        while (jalan) {
            System.out.println("\n=== KNOWLEDGE MANAGEMENT SYSTEM PUTUSAN ===");
            System.out.println("1. Tampilkan Semua Putusan");
            System.out.println("2. Cari Putusan (Nomor Perkara)");
            System.out.println("3. Filter Putusan (Jenis Narkotika)");
            System.out.println("4. Tambah Putusan Manual");
            System.out.println("5. Keluar");

            int pilihan = input.getIntInput("Pilih menu (1-5): ");

            switch (pilihan) {
                case 1:
                    tampilkanSemua();
                    break;
                case 2:
                    cariPutusan();
                    break;
                case 3:
                    filterPutusan();
                    break;
                case 4:
                    tambahPutusan();
                    break;
                case 5:
                    jalan = false;
                    System.out.println("Program selesai. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak ada di menu!");
            }
        }
    }

    private void tampilkanSemua() {
        System.out.println("\n--- DAFTAR SEMUA PUTUSAN ---");
        ArrayList<Putusan> daftar = controller.getSemuaData();
        if (daftar.isEmpty()) {
            System.out.println("Data masih kosong.");
            return;
        }
        for (Putusan p : daftar) {
            System.out.println("No: " + p.getNomorPerkara() + " | Terdakwa: " + p.getNamaTerdakwa() + " | Vonis: " + p.getVonisHukuman() + " bulan");
        }
    }

    private void cariPutusan() {
        System.out.println("\n--- CARI PUTUSAN ---");
        String nomor = input.getStringInput("Masukkan Nomor Perkara: ");
        Putusan p = controller.cariData(nomor);
        if (p != null) {
            System.out.println("Data Ditemukan: " + p.getNamaTerdakwa() + " - Vonis: " + p.getVonisHukuman() + " bulan");
        } else {
            System.out.println("Data dengan nomor perkara tersebut tidak ditemukan.");
        }
    }

    private void filterPutusan() {
        System.out.println("\n--- FILTER PUTUSAN ---");
        String jenis = input.getStringInput("Masukkan Jenis Narkotika (contoh: Sabu-sabu): ");
        ArrayList<Putusan> hasil = controller.filterDataJenis(jenis);
        if (hasil.isEmpty()) {
            System.out.println("Tidak ditemukan data untuk jenis narkotika tersebut.");
        } else {
            System.out.println("Ditemukan " + hasil.size() + " data:");
            for (Putusan p : hasil) {
                System.out.println("- " + p.getNomorPerkara() + " (" + p.getNamaTerdakwa() + ")");
            }
        }
    }
    private void tambahPutusan() {
        System.out.println("\n--- TAMBAH PUTUSAN MANUAL ---");
        String nomor = input.getStringInput("Nomor Perkara: ");
        String nama = input.getStringInput("Nama Terdakwa: ");
        String jenis = input.getStringInput("Jenis Narkotika: ");
        int vonis = input.getIntInput("Vonis Hukuman (dalam bulan): ");
        double berat = input.getDoubleInput("Berat Barang Bukti (gram): ");

        controller.tambahDataManual(nomor, nama, jenis, vonis, berat);
    }
}
