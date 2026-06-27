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

    // 6. Filter berdasarkan Jenis Narkotika
    public ArrayList<Putusan> filterByJenis(String jenis) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {
            // Mengecek apakah objek p adalah benar PutusanNarkotika
            if (p instanceof PutusanNarkotika) {
                // Downcasting (Mengubah bentuk parent ke child)
                PutusanNarkotika pn = (PutusanNarkotika) p;
                if (pn.getJenisNarkotika() != null && pn.getJenisNarkotika().equalsIgnoreCase(jenis)) {
                    hasil.add(p);
                }
            }
        }
        return hasil;
    }

    // 7. Filter berdasarkan Pengadilan
    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {
            if (p.getPengadilan() != null && p.getPengadilan().toLowerCase().contains(pengadilan.toLowerCase())) {
                hasil.add(p);
            }
        }
        return hasil;
    }
}


