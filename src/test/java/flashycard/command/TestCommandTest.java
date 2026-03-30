package flashycard.command;

import flashycard.context.SessionContainer;
import flashycard.model.Card;
import flashycard.model.KnowledgeBase;
import flashycard.storage.Storage;
import flashycard.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCommandTest {
    private KnowledgeBase hb;
    private Ui ui;
    private Storage storage;
    private SessionContainer session;

    @BeforeEach
    void setUp() {
        hb = new KnowledgeBase();
        ui = new Ui();
        storage = new Storage("data/test.txt");
        session = new SessionContainer();
        ui = new TestUi();

        hb.addCard(new Card(1, "Java?", "Language", "tech"));
        hb.addCard(new Card(2, "Maven?", "Build tool", "tech"));
    }

    @Test
    void execute_validSet_updatesSessionAndTriggersUi() {
        hb.addTestSet("java_set", List.of(1, 2));

        TestCommand command = new TestCommand("java_set");
        command.execute(hb, ui, storage, session);

        List<Card> sessionCards = session.getLastSearchResults();
        assertEquals(2, sessionCards.size(), "Session should contain 2 cards from the set");
        assertEquals(1, sessionCards.get(0).getId());
        assertEquals(2, sessionCards.get(1).getId());
    }

    @Test
    void execute_nonExistentSet_showsError() {
        TestCommand command = new TestCommand("ghost_set");
        command.execute(hb, ui, storage, session);

        assertTrue(session.getLastSearchResults().isEmpty(), "Session should not be updated for invalid sets");
    }

    @Test
    void execute_emptySet_showsError() {
        hb.addTestSet("empty_set", new java.util.ArrayList<>());

        TestCommand command = new TestCommand("empty_set");
        command.execute(hb, ui, storage, session);

        assertTrue(session.getLastSearchResults().isEmpty(), "Session should not be updated for empty sets");
    }

    @Test
    void execute_setWithDeletedCard_skipsMissingCard() {
        hb.addTestSet("mixed_set", List.of(1, 99));

        TestCommand command = new TestCommand("mixed_set");
        command.execute(hb, ui, storage, session);

        List<Card> sessionCards = session.getLastSearchResults();
        assertEquals(1, sessionCards.size());
        assertEquals(1, sessionCards.get(0).getId());
    }
}

class TestUi extends Ui {
    @Override
    public void startStudySession(List<Card> cards) {
    }
}

