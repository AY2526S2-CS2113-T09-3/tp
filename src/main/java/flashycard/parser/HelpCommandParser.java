package flashycard.parser;

import flashycard.command.Command;
import flashycard.command.HelpCommand;
import flashycard.exceptions.InvalidArgumentException;

/**
 * Parses user input for the "help" command.
 * Ensures that no extra arguments are provided, as per the User Guide's strict
 * command format rules for zero-argument commands.
 */
public class HelpCommandParser extends CommandParser {

    /**
     * Initializes the parser with the "help" keyword.
     * The regex ensures that only the word "help" (case-insensitive) is accepted,
     * with no trailing characters other than whitespace.
     */
    public HelpCommandParser() {
        super("help", "\\s*");
    }

    /**
     * Validates that the help command has no extra arguments and returns a HelpCommand.
     *
     * @param fullCommand The raw input string from the user.
     * @return A new HelpCommand instance.
     * @throws InvalidArgumentException If extra text is provided after the word 'help'.
     */
    @Override
    public Command parse(String fullCommand) throws InvalidArgumentException {
        try {
            super.match(fullCommand);
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException("Invalid format. Use 'help' without any extra arguments.");
        }

        return new HelpCommand();
    }
}
