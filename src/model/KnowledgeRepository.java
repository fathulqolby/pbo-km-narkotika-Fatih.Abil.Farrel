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

    // 4. Mencari berdasarkan Nomor Perkara
    public Putusan cariByNomor(String nomor) {
        for (Putusan p : daftarPutusan) {
            if (p.getNomorPerkara() != null && p.getNomorPerkara().equalsIgnoreCase(nomor)) {
                return p;
            }
        }
        return null;
    }

    // 5. Menghapus data berdasarkan Nomor Perkara
    public boolean hapus(String nomor) {
        Putusan p = cariByNomor(nomor);
        if (p != null) {
            daftarPutusan.remove(p);
            return true;
        }
        return false;
    }
}


