package flashycard.command;

import flashycard.context.SessionContainer;
import flashycard.exceptions.CardNotFoundException;
import flashycard.model.Card;
import flashycard.model.KnowledgeBase;
import flashycard.storage.Storage;
import flashycard.ui.Ui;

import java.util.ArrayList;
import java.util.List;


public class TestCommand extends Command {
    private final String setName;

    public TestCommand(String setName) {
        this.setName = setName.trim();
    }

    public String getSetName() {
        return this.setName;
    }

    @Override
    public void execute(KnowledgeBase hb, Ui ui, Storage storage, SessionContainer session) {
        List<Integer> ids = hb.getAllTestSets().get(setName);

        if (ids == null) {
            ui.showError("Test set '" + setName + "' does not exist. Create it first using 'save'.");
            return;
        }

        if (ids.isEmpty()) {
            ui.showError("Test set '" + setName + "' is empty.");
            return;
        }

        List<Card> testCards = new ArrayList<>();
        for (int id : ids) {
            if (hb.hasCard(id)) {
                try {
                    testCards.add(hb.getCardById(id));
                } catch (CardNotFoundException e) {
                    System.err.println("Warning: Card ID " + id + " disappeared during set retrieval.");
                }
            }
        }

        session.setLastSearchResults(testCards);

        if (ui != null) {
            ui.showMessage("Starting test session for set: [" + setName + "]");
            ui.startStudySession(testCards);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
