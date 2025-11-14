package teammate.model;

public enum Role {
    ATTACKER,
    DEFENDER,
    SUPPORTER,
    STRATEGIST,
    COORDINATOR,
    OTHER;

    public static Role fromString(String value) {
        if (value == null) {
            return OTHER;
        }
        String normalized = value.trim().toUpperCase().replace(" ", "_");
        try {
            return Role.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }
}
