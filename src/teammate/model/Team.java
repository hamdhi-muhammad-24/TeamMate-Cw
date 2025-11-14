package teammate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Team {

    private String teamId;
    private List<Participant> members = new ArrayList<>();

    public Team(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamId() {
        return teamId;
    }

    public List<Participant> getMembers() {
        return members;
    }

    public void addMember(Participant participant) {
        if (participant != null) {
            members.add(participant);
        }
    }

    public double getAverageSkill() {
        if (members.isEmpty()) return 0.0;
        double sum = 0;
        for (Participant p : members) {
            sum += p.getSkillLevel();
        }
        return sum / members.size();
    }

    public Map<String, Long> getGameCounts() {
        return members.stream()
                .map(Participant::getPreferredGame)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    public Map<Role, Long> getRoleCounts() {
        return members.stream()
                .map(Participant::getPreferredRole)
                .filter(r -> r != null)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<PersonalityType, Long> getPersonalityCounts() {
        return members.stream()
                .map(Participant::getPersonalityType)
                .filter(pt -> pt != null)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Team ")
                .append(teamId)
                .append(" (avgSkill=")
                .append(String.format("%.2f", getAverageSkill()))
                .append(")\n");
        for (Participant p : members) {
            sb.append("  - ")
                    .append(p.getName())
                    .append(" [Game: ").append(p.getPreferredGame())
                    .append(", Role: ").append(p.getPreferredRole())
                    .append(", Skill: ").append(p.getSkillLevel())
                    .append(", Type: ").append(p.getPersonalityType())
                    .append("]\n");
        }
        return sb.toString();
    }
}
