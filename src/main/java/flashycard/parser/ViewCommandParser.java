package flashycard.parser;

import java.util.regex.Matcher;

import flashycard.command.Command;
import flashycard.command.ViewCommand;
import flashycard.exceptions.InvalidArgumentException;

/**
 * Parses user input specifically for the "view" command. It identifies the
 * numeric ID of the flashcard the user wishes to inspect.
 */
public class ViewCommandParser extends CommandParser {

    /**
     * Initializes the parser with the "view" keyword and a regex pattern to capture
     * the card ID.
     */
    public ViewCommandParser() {
        super("view", "(?<id>.+?)");
    }

    /**
     * Extracts the ID from the command string and converts it to an integer.
     *
     * @param fullCommand The raw input string from the user.
     * @return A new ViewCommand instance for the specified card.
     * @throws InvalidArgumentException If the ID is not a valid integer or the
     *                                  format is incorrect.
     */
    @Override
    public Command parse(String fullCommand) throws InvalidArgumentException {
        Matcher matches;
        int id;

        try {
            matches = super.match(fullCommand);
            id = Integer.parseInt(matches.group("id").trim());

        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException("Invalid format. Please use: view <id>");
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Invalid ID: ID entered is not a valid integer");
        }

        return new ViewCommand(id);
    }
}
