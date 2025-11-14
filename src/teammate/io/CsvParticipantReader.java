package teammate.io;

import teammate.exception.FileProcessingException;
import teammate.exception.InvalidDataException;
import teammate.model.Participant;
import teammate.service.ParticipantService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParticipantReader {

    private final ParticipantService participantService = new ParticipantService();

    /**
     * Expected CSV header:
     * ID,Name,Email,PreferredGame,SkillLevel,PreferredRole,PersonalityScore,PersonalityType
     */
    public List<Participant> readParticipants(String filePath) throws FileProcessingException {
        List<Participant> participants = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // header
            if (line == null) {
                throw new FileProcessingException("Empty CSV file: " + filePath);
            }

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] parts = line.split(",", -1);
                if (parts.length < 8) {
                    // skip or log invalid row
                    continue;
                }

                try {
                    Participant participant = participantService.buildParticipant(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4],
                            parts[5],
                            parts[6],
                            parts[7]
                    );
                    participants.add(participant);
                } catch (InvalidDataException e) {
                    // For now, just print and skip that row
                    System.err.println("Skipping invalid row: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new FileProcessingException("Error reading participants CSV: " + filePath, e);
        }

        return participants;
    }
}
