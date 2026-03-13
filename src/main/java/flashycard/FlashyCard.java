package flashycard;

import flashycard.model.KnowledgeBase;
import flashycard.storage.Storage;
import flashycard.ui.Ui;

public class FlashyCard {

    private Ui ui;
    private Storage storage;
    private KnowledgeBase knowledgeBase;

    public FlashyCard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            knowledgeBase = storage.load();
        } catch (Exception e) {
            System.out.println("Starting with empty knowledge base.");
            knowledgeBase = new KnowledgeBase();
        }
    }

    public void run() {
        System.out.println("Welcome to FlashyCards!");

        while (true) {
            String input = ui.readCommand();
            if (input == null) {
                continue;
            }
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("Command received: " + input);
            storage.save(knowledgeBase);
        }

        System.out.println("Goodbye!");
    }

    public static void main(String[] args) {
        FlashyCard app = new FlashyCard("data/flashcards.txt");
        app.run();
    }
}
