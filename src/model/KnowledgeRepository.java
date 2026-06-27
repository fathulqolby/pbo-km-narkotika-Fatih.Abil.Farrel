package model;

import java.util.ArrayList;

public class KnowledgeRepository {

    private ArrayList<Putusan> daftarPutusan;

    public KnowledgeRepository() {
        this.daftarPutusan = new ArrayList<>();
    }

    public void simpan(Putusan putusan) {
        daftarPutusan.add(putusan);
    }

    public ArrayList<Putusan> getDaftarSemua() {
        return daftarPutusan;
    }

    public Putusan cariByNomor(String nomor) {
        for (Putusan p : daftarPutusan) {
            if (p.getNomorPerkara() != null && p.getNomorPerkara().equalsIgnoreCase(nomor)) {
                return p;
            }
        }
        return null;
    }

    // Memfilter data berdasarkan jenis narkotika (Menerapkan instanceof)
    public ArrayList<Putusan> filterByJenis(String jenis) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {

            if (p instanceof PutusanNarkotika) {

                PutusanNarkotika pn = (PutusanNarkotika) p;
                if (pn.getJenisNarkotika() != null && pn.getJenisNarkotika().equalsIgnoreCase(jenis)) {
                    hasil.add(p);
                }
            }
        }
        return hasil;
    }
}