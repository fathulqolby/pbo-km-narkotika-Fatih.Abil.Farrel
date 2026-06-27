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
    }
}