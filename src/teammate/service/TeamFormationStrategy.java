package teammate.service;

import teammate.model.Participant;
import teammate.model.Team;

import java.util.List;

public interface TeamFormationStrategy {

    List<Team> formTeams(List<Participant> participants, int teamSize);
}
