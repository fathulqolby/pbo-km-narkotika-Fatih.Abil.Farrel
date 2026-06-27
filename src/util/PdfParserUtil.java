package util;

import model.Putusan;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;

public class PdfParserUtil {

    public static void muatDataDariFolder(String folderPath, model.KnowledgeRepository repo) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (listOfFiles != null) {
            System.out.println("Mengekstrak data dari " + listOfFiles.length + " file PDF...");
            for (File file : listOfFiles) {
                Putusan p = ekstrak(file);
                if (p != null) {
                    repo.simpan(p); // Menyimpan ke repository
                }
            }
            System.out.println("Ekstraksi selesai! Total data di repositori: " + repo.getTotalData());
        } else {
            System.out.println("Folder dataset tidak ditemukan.");
        }
    }

    private static Putusan ekstrak(File file) {
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String teksPdf = stripper.getText(document);

            Putusan putusan = new Putusan();

            // Ekstrak Nomor Perkara menggunakan Regex
            Matcher mNomor = Pattern.compile("Nomor\\s+([\\w/.]+)").matcher(teksPdf);
            if (mNomor.find()) {
                putusan.setNomorPerkara(mNomor.group(1));
            }
            // Ekstrak Nama Terdakwa
            Matcher mNama = Pattern.compile("Nama lengkap\\s*:\\s*(.+)").matcher(teksPdf);
            if (mNama.find()) {
                putusan.setNamaTerdakwa(mNama.group(1).trim());
            }
            // Ekstrak Vonis
            Matcher mVonis = Pattern.compile("selama\\s+(\\d+)\\s+(bulan|tahun)", Pattern.CASE_INSENSITIVE).matcher(teksPdf);
            if (mVonis.find()) {
                int angka = Integer.parseInt(mVonis.group(1));
                if (mVonis.group(2).equalsIgnoreCase("tahun")) {
                    angka *= 12; // Konversi tahun ke bulan
                }
                putusan.setVonisHukuman(angka);
            } else {
                putusan.setVonisHukuman(0); // Default jika tidak terbaca
            }

            // Atribut tambahan di-set dengan nilai default sementara
            putusan.setJenisNarkotika("Sabu-sabu");
            putusan.setBeratBarangBukti(1.0);

            return putusan;
        } catch (IOException e) {
            System.err.println("Gagal membaca file: " + file.getName());
            return null;
        }

    }
}