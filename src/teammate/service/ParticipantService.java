package teammate.service;

import teammate.exception.InvalidDataException;
import teammate.model.Participant;
import teammate.model.PersonalityType;
import teammate.model.Role;

public class ParticipantService {

    public Participant buildParticipant(String id,
                                        String name,
                                        String email,
                                        String preferredGame,
                                        String skillStr,
                                        String roleStr,
                                        String personalityScoreStr,
                                        String personalityTypeStr) throws InvalidDataException {

        if (id == null || id.isBlank()) {
            throw new InvalidDataException("Missing ID");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidDataException("Missing name for ID " + id);
        }

        int skill;
        try {
            skill = Integer.parseInt(skillStr.trim());
        } catch (Exception e) {
            throw new InvalidDataException("Invalid skill level for ID " + id + ": " + skillStr);
        }

        Role role = Role.fromString(roleStr);
        int score = 0;
        if (personalityScoreStr != null && !personalityScoreStr.isBlank()) {
            try {
                score = Integer.parseInt(personalityScoreStr.trim());
            } catch (NumberFormatException e) {
                score = 0;
            }
        }

        PersonalityType personalityType = PersonalityType.fromString(personalityTypeStr);

        return new Participant(
                id.trim(),
                name.trim(),
                email != null ? email.trim() : "",
                preferredGame != null ? preferredGame.trim() : "",
                skill,
                role,
                score,
                personalityType
        );
    }
}
