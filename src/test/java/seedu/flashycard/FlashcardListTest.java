package seedu.flashycard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FlashcardListTest {

    @Test
    public void add_flashcard_success() {
        FlashcardList list = new FlashcardList();
        Flashcard card = new Flashcard("Q", "A");

        list.add(card);

        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void get_flashcard_correctFlashcard() {
        FlashcardList list = new FlashcardList();
        Flashcard card = new Flashcard("Q", "A");

        list.add(card);

        Flashcard result = list.get(0);

        Assertions.assertEquals(card, result);
    }
}
