package teammate.concurrent;

import teammate.model.Participant;
import teammate.model.Team;
import teammate.service.BalancedTeamFormationStrategy;
import teammate.service.TeamFormationStrategy;

import java.util.Collections;
import java.util.List;

public class TeamFormationTask implements Runnable {

    private final List<Participant> participants;
    private final int teamSize;
    private List<Team> result;

    public TeamFormationTask(List<Participant> participants, int teamSize) {
        this.participants = participants;
        this.teamSize = teamSize;
    }

    @Override
    public void run() {
        TeamFormationStrategy strategy = new BalancedTeamFormationStrategy();
        result = strategy.formTeams(participants, teamSize);
    }

    public List<Team> getResult() {
        return result == null ? Collections.emptyList() : result;
    }
}
