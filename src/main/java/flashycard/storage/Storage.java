package flashycard.storage;

import flashycard.exceptions.CorruptedDataException;
import flashycard.model.Card;
import flashycard.model.KnowledgeBase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(KnowledgeBase knowledgeBase) {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Card card : knowledgeBase.getAllCards()) {
                String line = card.getId() + "|" + card.getQuestion() + "|" + card.getAnswer();
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public KnowledgeBase load() throws CorruptedDataException {
        KnowledgeBase kb = new KnowledgeBase();
        File file = new File(filePath);
        if (!file.exists()) {
            return kb;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length != 3) {
                    throw new CorruptedDataException("Invalid data format");
                }

                int id = Integer.parseInt(parts[0]);
                String question = parts[1];
                String answer = parts[2];

                Card card = new Card(id, question, answer);
                kb.addCard(card);
            }

            scanner.close();

        } catch (Exception e) {
            throw new CorruptedDataException("Failed to load data");
        }

        return kb;
    }
}
