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
        // Membaca semua file dengan ekstensi .pdf di dalam folder
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (listOfFiles == null) {
            System.out.println("Folder dataset tidak ditemukan.");
            return;
        }
        System.out.println("Ditemukan " + listOfFiles.length + " file PDF siap diekstrak.");
    }
}