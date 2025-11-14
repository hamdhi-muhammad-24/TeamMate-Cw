package teammate.model;

public class Participant extends ClubMember {

    private String preferredGame;
    private int skillLevel; // e.g. 1–5 or 1–10
    private Role preferredRole;
    private int personalityScore; // 0–100
    private PersonalityType personalityType;

    public Participant() {
    }

    public Participant(String id,
                       String name,
                       String email,
                       String preferredGame,
                       int skillLevel,
                       Role preferredRole,
                       int personalityScore,
                       PersonalityType personalityType) {
        super(id, name, email);
        this.preferredGame = preferredGame;
        this.skillLevel = skillLevel;
        this.preferredRole = preferredRole;
        this.personalityScore = personalityScore;
        this.personalityType = personalityType;
    }

    public String getPreferredGame() {
        return preferredGame;
    }

    public void setPreferredGame(String preferredGame) {
        this.preferredGame = preferredGame;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Role getPreferredRole() {
        return preferredRole;
    }

    public void setPreferredRole(Role preferredRole) {
        this.preferredRole = preferredRole;
    }

    public int getPersonalityScore() {
        return personalityScore;
    }

    public void setPersonalityScore(int personalityScore) {
        this.personalityScore = personalityScore;
    }

    public PersonalityType getPersonalityType() {
        return personalityType;
    }

    public void setPersonalityType(PersonalityType personalityType) {
        this.personalityType = personalityType;
    }

    public String toCsvRow(String teamId) {
        return String.join(",",
                teamId,
                getId(),
                escape(getName()),
                escape(getEmail()),
                escape(preferredGame),
                String.valueOf(skillLevel),
                preferredRole != null ? preferredRole.name() : "",
                String.valueOf(personalityScore),
                personalityType != null ? personalityType.name() : ""
        );
    }

    private String escape(String value) {
        if (value == null) return "";
        String v = value.trim();
        if (v.contains(",") || v.contains("\"")) {
            v = v.replace("\"", "\"\"");
            return "\"" + v + "\"";
        }
        return v;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", preferredGame='" + preferredGame + '\'' +
                ", skillLevel=" + skillLevel +
                ", preferredRole=" + preferredRole +
                ", personalityScore=" + personalityScore +
                ", personalityType=" + personalityType +
                '}';
    }
}
