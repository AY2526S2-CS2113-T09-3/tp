package flashycard.command;

import flashycard.context.SessionContainer;
import flashycard.model.KnowledgeBase;
import flashycard.storage.Storage;
import flashycard.ui.Ui;

/**
 * Displays a help menu listing all available commands and their formats.
 */
public class HelpCommand extends Command {

    @Override
    public void execute(KnowledgeBase hb, Ui ui, Storage storage, SessionContainer session) {
        ui.showMessage("--- FlashyCard Help Menu ---");
        ui.showMessage("Command Summary:");
        ui.showMessage("  add q/Q a/A       - Add a new card");
        ui.showMessage("  list [s/SET]      - List all cards or cards in a set");
        ui.showMessage("  view ID           - View a card's question");
        ui.showMessage("  flip ID           - View a card's answer");
        ui.showMessage("  edit ID [q/][a/]  - Edit an existing card");
        ui.showMessage("  tag ID t/TAG      - Assign a tag to a card");
        ui.showMessage("  tags              - List all unique tags");
        ui.showMessage("  find [q/ or a/]KW    - Search cards by keyword");
        ui.showMessage("  save all s/SET    - Save last list results to a set");
        ui.showMessage("  save ID s/SET     - Save a specific card to a set");
        ui.showMessage("  test SET          - Start a study session");
        ui.showMessage("  remove ID s/SET   - Remove card(s) from a set");
        ui.showMessage("  remove all s/SET   - Remove all cards from a set");
        ui.showMessage("  delete ID         - Permanently delete a card");
        ui.showMessage("  exit              - Exit the application");
        ui.showMessage("----------------------------");
    }
}
