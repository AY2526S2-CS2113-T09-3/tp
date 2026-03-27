package flashycard.parser;

import flashycard.command.Command;
import flashycard.command.DeleteCommand;
import flashycard.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandParserTest {

    @Test
    void testParse_validId_returnsCommand() throws InvalidArgumentException {
        DeleteCommandParser parser = new DeleteCommandParser();
        Command command = parser.parse("delete 42");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void testParse_validIdWithSpaces_returnsCommand() throws InvalidArgumentException {
        DeleteCommandParser parser = new DeleteCommandParser();
        Command command = parser.parse("delete    7   ");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void testParse_invalidId_throwsException() {
        DeleteCommandParser parser = new DeleteCommandParser();
        assertThrows(InvalidArgumentException.class, () -> parser.parse("delete abc"));
    }

    @Test
    void testParse_missingId_throwsException() {
        DeleteCommandParser parser = new DeleteCommandParser();
        assertThrows(InvalidArgumentException.class, () -> parser.parse("delete "));
    }

    @Test
    void testParse_emptyString_throwsInvalidArgumentException() {
        DeleteCommandParser parser = new DeleteCommandParser();
        assertThrows(InvalidArgumentException.class, () -> parser.parse(""));
    }
}
