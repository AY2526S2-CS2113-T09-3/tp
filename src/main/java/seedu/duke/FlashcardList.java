package seedu.duke;

import java.util.ArrayList;

public class FlashcardList {

    private ArrayList<Flashcard> flashcards;

    public FlashcardList() {
        flashcards = new ArrayList<>();
    }

    public void add(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public Flashcard get(int index) {
        if (index >= 0 && index < flashcards.size()) {
            return flashcards.get(index);
        }
        return null;
    }

    public int size() {
        return flashcards.size();
    }
}
