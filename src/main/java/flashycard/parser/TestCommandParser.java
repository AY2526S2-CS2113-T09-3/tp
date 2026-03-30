package flashycard.parser;

import java.util.regex.Matcher;
import flashycard.command.Command;
import flashycard.command.TestCommand;
import flashycard.exceptions.InvalidArgumentException;


public class TestCommandParser extends CommandParser {

    public TestCommandParser() {
        super("test", "(?<setName>.+)");
    }

    @Override
    public Command parse(String fullCommand) throws InvalidArgumentException {
        Matcher matches = this.match(fullCommand);

        String setName = matches.group("setName").trim();

        if (setName.isEmpty()) {
            throw new InvalidArgumentException("Please provide a set name. Usage: test <setName>");
        }

        return new TestCommand(setName);
    }
}
