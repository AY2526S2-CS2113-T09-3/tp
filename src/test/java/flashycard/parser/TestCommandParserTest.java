package flashycard.parser;

import flashycard.command.Command;
import flashycard.command.TestCommand;
import flashycard.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCommandParserTest {

    private TestCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new TestCommandParser();
    }

    @Test
    void parse_validSetName_returnsTestCommand() throws InvalidArgumentException {
        String input = "test midterm_prep";

        Command command = parser.parse(input);

        assertNotNull(command, "Parser should return a command object");
        assertTrue(command instanceof TestCommand, "Command should be an instance of TestCommand");

        TestCommand testCmd = (TestCommand) command;
        assertEquals("midterm_prep", testCmd.getSetName(), "The captured set name should match the input");
    }

    @Test
    void parse_setNameWithSpaces_returnsTestCommand() throws InvalidArgumentException {
        String input = "test Final Exam 2024";
        Command command = parser.parse(input);

        assertNotNull(command);
        assertTrue(command instanceof TestCommand);
    }

    @Test
    void parse_emptySetName_throwsException() {
        String input = "test   ";

        assertThrows(InvalidArgumentException.class, () -> {
            parser.parse(input);
        }, "Should throw InvalidArgumentException if set name is just whitespace");
    }

    @Test
    void parse_noSetName_throwsException() {
        String input = "test";

        assertThrows(Exception.class, () -> {
            parser.parse(input);
        });
    }
}
