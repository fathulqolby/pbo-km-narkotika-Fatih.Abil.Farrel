package view;

import controller.InputHandler;
import controller.KnowledgeController;
import model.Putusan;
import java.util.ArrayList;

public class ConsoleView {
    private KnowledgeController controller;
    private InputHandler input;

    public ConsoleView() {
        this.controller = new KnowledgeController();
        this.input = new InputHandler();
    }
}