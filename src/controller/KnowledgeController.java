package controller;

import model.KnowledgeRepository;
import model.Putusan;
import model.PutusanNarkotika; // Harus di-import karena kita menggunakan class anak
import util.PdfParserUtil;

import java.util.ArrayList;

public class KnowledgeController {
    // Controller memegang akses ke repository (database)
    private KnowledgeRepository repository;

    // Konstruktor akan otomatis dijalankan saat aplikasi dibuka
    public KnowledgeController() {
        repository = new KnowledgeRepository();
        inisialisasiData();
    }

    // Fungsi untuk memuat 50 PDF otomatis menggunakan utilitas PDFBox
    private void inisialisasiData() {
        System.out.println("=== Memulai Knowledge Management System ===");
        // Pastikan folder "dataset" sudah ada di dalam project
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

    // 3. Memfilter data berdasarkan jenis narkotika
    public ArrayList<Putusan> filterDataJenis(String jenis) {
        return repository.filterByJenis(jenis);
    }

    // 4. Menambah putusan baru secara manual dari terminal
    public void tambahDataManual(String nomor, String nama, String jenis, int vonis, double berat) {
        // PERBAIKAN OOP: Menggunakan class anak (PutusanNarkotika) agar bisa menyimpan atribut spesifik
        PutusanNarkotika p = new PutusanNarkotika();

        // Atribut dari class induk (Putusan)
        p.setNomorPerkara(nomor);
        p.setNamaTerdakwa(nama);
        p.setVonisHukuman(vonis);

        // Atribut spesifik dari class anak (PutusanNarkotika)
        p.setJenisNarkotika(jenis);
        p.setBeratBarangBukti(berat);

        // Disimpan ke repository (Polymorphism)
        repository.simpan(p);
        System.out.println("Data putusan berhasil ditambahkan ke repository!");
    }

    // 5. Mengedit data putusan yang sudah ada
    public boolean editDataManual(String nomorLama, String nomorBaru, String nama, String jenis, int vonis, double berat) {
        Putusan p = repository.cariByNomor(nomorLama);
        if (p != null) {
            p.setNomorPerkara(nomorBaru);
            p.setNamaTerdakwa(nama);
            p.setVonisHukuman(vonis);

            // Memastikan data di-cast ke class anak untuk mengubah atribut spesifik
            if (p instanceof PutusanNarkotika) {
                ((PutusanNarkotika) p).setJenisNarkotika(jenis);
                ((PutusanNarkotika) p).setBeratBarangBukti(berat);
            }
            return true; // Berhasil diedit
        }
        return false; // Data tidak ditemukan
    }
}