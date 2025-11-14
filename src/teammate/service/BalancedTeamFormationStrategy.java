package teammate.service;

import teammate.model.Participant;
import teammate.model.PersonalityType;
import teammate.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BalancedTeamFormationStrategy implements TeamFormationStrategy {

    @Override
    public List<Team> formTeams(List<Participant> participants, int teamSize) {
        if (participants == null || participants.isEmpty() || teamSize <= 0) {
            return Collections.emptyList();
        }

        // Shuffle to add randomness.
        List<Participant> shuffled = new ArrayList<>(participants);
        Collections.shuffle(shuffled);

        // Sort by skill descending so we can distribute strong players across teams
        shuffled.sort(Comparator.comparingInt(Participant::getSkillLevel).reversed());

        int numberOfTeams = (int) Math.ceil(shuffled.size() / (double) teamSize);
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < numberOfTeams; i++) {
            teams.add(new Team("Team-" + (i + 1)));
        }

        // Simple round-robin distribution of skill levels
        int index = 0;
        for (Participant p : shuffled) {
            Team team = teams.get(index % numberOfTeams);
            team.addMember(p);
            index++;
        }

        // NOTE:
        // For coursework, this already shows balancing by skill + randomness.
        // You can further refine to enforce:
        // - at least 3 roles per team
        // - mixture of personality types
        // - game diversity
        // if you want extra marks.

        return teams;
    }
}
