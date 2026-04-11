package flashycard.parser;

import java.util.regex.Matcher;

import flashycard.command.Command;
import flashycard.command.DeleteCommand;
import flashycard.exceptions.InvalidArgumentException;

/**
 * Parses user input specifically for the "delete" command. It identifies the
 * numeric ID of the flashcard that the user wants to remove.
 */
public class DeleteCommandParser extends CommandParser {

    /**
     * Initializes the parser with the "delete" keyword and a pattern to capture the
     * card ID.
     */
    public DeleteCommandParser() {
        super("delete", "(?<id>.+?)");
    }

    /**
     * Extracts the ID from the command string and converts it to an integer.
     *
     * @param fullCommand The raw input string from the user.
     * @return A new DeleteCommand instance targeting the specified ID.
     * @throws InvalidArgumentException If the ID is not a valid number or the
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
            throw new InvalidArgumentException("Invalid format. Please use: delete <id>");
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Invalid ID: ID entered is not a valid number");
        }

        return new DeleteCommand(id);
    }

}
