package flashycard.parser;

import java.util.regex.Matcher;
import flashycard.command.Command;
import flashycard.command.TestCommand;
import flashycard.exceptions.InvalidArgumentException;

/**
 * Parses user input specifically for the "test" command. It identifies the name
 * of the test set that the user wishes to practice with.
 */
public class TestCommandParser extends CommandParser {

    /**
     * Initializes the parser with the "test" keyword and a regex to capture the
     * mandatory test set name.
     */
    public TestCommandParser() {
        super("test", "(?<setName>.+)");
    }

    /**
     * Extracts the set name from the command string and creates a TestCommand.
     *
     * @param fullCommand The raw input string from the user.
     * @return A new TestCommand instance for the specified set.
     * @throws InvalidArgumentException If the set name is missing or the format is
     *                                  invalid.
     */
    @Override
    public Command parse(String fullCommand) throws InvalidArgumentException {
        Matcher matches;
        String setName;

        try {
            matches = super.match(fullCommand);
            setName = matches.group("setName").trim();
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException("Invalid test command format. Usage: test <setName>");
        }

        if (setName.isEmpty()) {
            throw new InvalidArgumentException("Missing test set name. Usage: test <setName>");
        }

        return new TestCommand(setName);
    }
}
