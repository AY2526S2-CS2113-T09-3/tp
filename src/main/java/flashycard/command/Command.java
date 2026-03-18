package flashycard.command;

import flashycard.exceptions.CardNotFoundException;
import flashycard.model.KnowledgeBase;
import flashycard.storage.Storage;
import flashycard.ui.Ui;

public abstract class Command {

    public abstract void execute(KnowledgeBase cards, Ui ui, Storage storage) throws CardNotFoundException;

    public boolean isExit() {
        return false;
    }
}
