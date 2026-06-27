package util;

import model.Putusan;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

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
        return null; // Placeholder sementara
    }
}