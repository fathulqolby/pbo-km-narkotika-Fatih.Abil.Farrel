package model;

import java.util.ArrayList;

public class KnowledgeRepository {
    private ArrayList<Putusan> daftarPutusan;

    public KnowledgeRepository() {
        daftarPutusan = new ArrayList<>();
    }

    // 1. Menyimpan data baru
    public void simpan(Putusan p) {
        daftarPutusan.add(p);
    }

    // 2. Mendapatkan semua data
    public ArrayList<Putusan> getDaftarSemua() {
        return daftarPutusan;
    }

    // 3. Mendapatkan total data saat ini
    public int getTotalData() {
        return daftarPutusan.size();
    }
}


