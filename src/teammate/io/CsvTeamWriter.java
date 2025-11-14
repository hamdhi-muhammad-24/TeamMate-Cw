package teammate.io;

import teammate.exception.FileProcessingException;
import teammate.model.Participant;
import teammate.model.Team;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvTeamWriter {

    /**
     * Output header example:
     * TeamID,ParticipantID,Name,Email,PreferredGame,SkillLevel,PreferredRole,PersonalityScore,PersonalityType
     */
    public void writeTeams(String filePath, List<Team> teams) throws FileProcessingException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("TeamID,ParticipantID,Name,Email,PreferredGame,SkillLevel,PreferredRole,PersonalityScore,PersonalityType");
            writer.newLine();

            for (Team team : teams) {
                for (Participant p : team.getMembers()) {
                    writer.write(p.toCsvRow(team.getTeamId()));
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            throw new FileProcessingException("Error writing teams CSV: " + filePath, e);
        }
    }
}
