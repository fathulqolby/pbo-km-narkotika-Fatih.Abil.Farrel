package controller;

import model.KnowledgeRepository;
import model.Putusan;
import util.PdfParserUtil;
import java.util.ArrayList;

public class KnowledgeController {
    // Controller memegang akses ke repository (database)
    private KnowledgeRepository repository;

    public KnowledgeController() {
        repository = new KnowledgeRepository();
        inisialisasiData(); // Panggil fungsi otomatis
    }

    // Fungsi untuk memuat 50 PDF otomatis menggunakan utilitas Fatih
    private void inisialisasiData() {
        System.out.println("=== Memulai Knowledge Management System ===");
        PdfParserUtil.muatDataDariFolder("dataset", repository);
        System.out.println("Sistem siap digunakan!\n");
    }

    // 1. Mengambil semua data untuk ditampilkan di tabel
    public ArrayList<Putusan> getSemuaData() {
        return repository.getDaftarSemua();
    }

    // 2. Mencari data berdasarkan Nomor Perkara
    public Putusan cariData(String nomor) {
        return repository.cariByNomor(nomor);
    }
}