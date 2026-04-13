
package flashycard.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

import flashycard.model.Card;

public class UiTest {
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ui = new Ui();
    }

    @Test
    public void testShowWelcome() {
        ui.showWelcome();
    }

    @Test
    public void testShowAddedMessage() {
        Card card = new Card(1, "What is abc?", "123", "alphabet");
        ui.showAddedMessage(card);
        String output = outputStream.toString();
        assertTrue(output.contains("[1]"), "Output should contain card ID in brackets" );
        assertTrue(output.contains("[alphabet]"),"Output should contain the tag");
    }

    @Test
    public void testShowQuestion() {
        Card card = new Card(2, "What is abc?", "123", "alphabet");
        ui.showQuestion(card);
        String output = outputStream.toString();
        assertTrue(output.contains("2"));
        assertTrue(output.contains("What is abc?"));
    }

    @Test
    public void testShowAnswer() {
        Card card = new Card(2, "What is abc?", "123", "alphabet");
        ui.showAnswer(card);
        String output = outputStream.toString();
        assertTrue(output.contains("123"));
    }

    @Test
    public void testShowDeletedMessage() {
        Card card = new Card(2, "What is abc?", "123", "alphabet");
        ui.showDeletedMessage(card);
        String output = outputStream.toString();
        assertTrue(output.contains("removed"), "Should confirm the removal action");
        assertTrue(output.contains("#2"), "Should specify the correct card ID");
    }

    @Test
    public void testShowTaggedMessage() {
        Card card = new Card(1, "Question", "Answer", "Science");
        ui.showTaggedMessage(card);

        String output = outputStream.toString();
        assertTrue(output.contains("#1"), "Output should contain the card ID");
        assertTrue(output.contains("Science"), "Output should contain the new tag name");
    }

    @Test
    public void testShowList_showsTag() {
        flashycard.model.KnowledgeBase kb = new flashycard.model.KnowledgeBase();
        kb.addCard(new Card(1, "Java?", "Yes", "Coding"));

        ui.showList(kb);
        String output = outputStream.toString();
        assertTrue(output.contains("Coding"), "The list view should display the card's tag");
    }


    @Test

    public void testShowTagsList_withMultipleTags_showsNumberedList2() {
        Set<String> tags = new LinkedHashSet<>();
        tags.add("Android");
        tags.add("Java");
        tags.add("none");

        ui.showTagsList(tags);

        String output = outputStream.toString();

        assertTrue(output.contains("1. Android"), "First tag should be numbered 1");
        assertTrue(output.contains("2. Java"), "Second tag should be numbered 2");
        assertTrue(output.contains("Uncategorised cards (No tags assigned)"),
                "Status 'none' should be explained clearly");
        assertTrue(output.contains("Total: 2 categories"), "Count should exclude 'none'");
    }


    @Test
    public void testShowTagsList_alphabeticalOrder() {
        Set<String> tags = new java.util.TreeSet<>();
        tags.add("Zebra");
        tags.add("Apple");

        ui.showTagsList(tags);
        String output = outputStream.toString();

        assertTrue(output.indexOf("Apple") < output.indexOf("Zebra"), "Apple should come first");
    }

    @Test
    public void testShowSearchResults() {
        List<Card> results = new ArrayList<>();
        results.add(new Card(1, "What is Java?", "A language", "Coding"));

        ui.showSearchResults(results, "find/Java");
        String output = outputStream.toString();

        assertTrue(output.contains("Found 1 card(s)"), "Should show count of matches");
        assertTrue(output.contains("matching 'Java'"), "Should show the keyword searched");
        assertTrue(output.contains("What is Java?"), "Should show the card content");
    }

    @Test
    public void testShowSaveSetSuccess() {
        ui.showSaveSetSuccess("midterm", 5);
        String output = outputStream.toString();
        assertTrue(output.contains("Successfully saved 5 card(s)"));
        assertTrue(output.contains("[midterm]"));
    }

    @Test
    public void testShowTestSets() {
        Map<String, List<Integer>> testSets = new HashMap<>();
        testSets.put("JavaBasics", List.of(1, 2, 3));

        ui.showTestSets(testSets);
        String output = outputStream.toString();
        assertTrue(output.contains("JavaBasics (3 cards)"));
    }

    @Test
    public void testShowSessionHeader() {
        ui.showSessionHeader("Unit Test Session", 10);
        String output = outputStream.toString();
        assertTrue(output.contains("Starting Session: Unit Test Session"));
        assertTrue(output.contains("Total cards to review: 10"));
    }


    @Test
    public void testStartStudySessionInteractiveFlow() {
        String simulatedInput = "\ny\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ui = new Ui();

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(1, "Q1", "A1", "test"));

        ui.startStudySession(cards);

        String output = outputStream.toString();
        assertTrue(output.contains("Question: Q1"));
        assertTrue(output.contains("Answer: A1"));
        assertTrue(output.contains("Score: 1/1"), "Should record the 'y' as a correct answer");

        System.setIn(System.in);
    }

    @Test
    public void testShowError() {
        ui.showError("Invalid command");
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid command"));
    }

    @Test
    public void testShowExitMessage() {
        ui.showExitMessage();
        String output = outputStream.toString();
        assertTrue(output.contains("Bye! Hope to see you again soon!"));
    }
}
