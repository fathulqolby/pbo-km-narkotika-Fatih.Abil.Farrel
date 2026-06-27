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
                case 1: tampilkanSemua(); break;
                case 2: cariPutusan(); break;
                case 3: filterPutusan(); break;
                case 4: tambahPutusan(); break;
                case 5: jalan = false; System.out.println("Program selesai. Terima kasih!"); break;
                default: System.out.println("Pilihan tidak ada di menu!");
            }
        }
    }
}
