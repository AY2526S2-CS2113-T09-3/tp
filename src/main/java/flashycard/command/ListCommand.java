package flashycard.command;

import flashycard.model.KnowledgeBase;
import flashycard.storage.Storage;
import flashycard.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(KnowledgeBase knowledgeBase, Ui ui, Storage storage) {
        ui.showList(knowledgeBase);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
