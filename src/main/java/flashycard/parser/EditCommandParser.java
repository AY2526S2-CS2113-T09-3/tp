package flashycard.parser;

import java.util.regex.Matcher;

import flashycard.command.Command;
import flashycard.command.EditCommand;
import flashycard.exceptions.InvalidArgumentException;

/**
 * Parses user input specifically for the "edit" command. It allows users to
 * update the question, the answer, or both for an existing card.
 */
public class EditCommandParser extends CommandParser {
    /**
     * Initializes the parser with the "edit" keyword and a regex that captures the
     * ID and optional question/answer updates.
     */
    public EditCommandParser() {
        super("edit", "(?<id>\\d+)(?:\\s+q/(?<question>.*?)(?=\\s+[qa]/|$))?(?:\\s+a/(?<answer>.*?)(?=\\s+[qa]/|$))?");
    }

    /**
     * Extracts the ID and the new content from the command string. Validates that
     * at least one field (question or answer) is being changed.
     *
     * @param fullCommand The raw input string from the user.
     * @return A new EditCommand instance with the updated details.
     * @throws InvalidArgumentException If the ID is invalid or no updates are
     *                                  provided.
     */
    @Override
    public Command parse(String fullCommand) throws InvalidArgumentException {
        checkDuplicateFlags(fullCommand, "q/");
        checkDuplicateFlags(fullCommand, "a/");
        Matcher matcher;
        int id;

        try {
            matcher = super.match(fullCommand);
            id = Integer.parseInt(matcher.group("id"));
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException("Invalid format. Please use: edit ID [q/QUESTION] [a/ANSWER]");
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Invalid Card ID: ID entered is not a valid integer");
        }

        String question = matcher.group("question");
        String answer = matcher.group("answer");

        if (question != null) {
            question = question.trim();
        } else {
            question = null;
        }
        if (answer != null) {
            answer = answer.trim();
        } else {
            answer = null;
        }

        if ((question == null || question.isEmpty()) && (answer == null || answer.isEmpty())) {
            throw new InvalidArgumentException("Edit command requires at least q/QUESTION or a/ANSWER. "
                    + "Usage: edit ID [q/QUESTION] [a/ANSWER]");
        }

        return new EditCommand(id, question, answer);
    }

    private void checkDuplicateFlags(String input, String flag) throws InvalidArgumentException {
        int firstIndex = input.indexOf(flag);
        if (firstIndex != -1) {
            int secondIndex = input.indexOf(flag, firstIndex + flag.length());
            if (secondIndex != -1) {
                throw new InvalidArgumentException("Duplicate flag detected: " + flag);
            }
        }
    }

}
