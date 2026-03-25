package flashycard.parser;

import flashycard.command.Command;
import flashycard.command.ListCommand;
import flashycard.exceptions.InvalidArgumentException;

public class ListCommandParser extends CommandParser {
    public ListCommandParser() {
        super("list", "");
    }

    @Override
    public Command parse(String fullCommand) throws InvalidArgumentException {
        this.match(fullCommand);

        return new ListCommand();
    }

}
