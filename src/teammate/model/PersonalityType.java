package teammate.model;

public enum PersonalityType {
    LEADER,
    BALANCED,
    THINKER,
    UNKNOWN;

    public static PersonalityType fromString(String value) {
        if (value == null) {
            return UNKNOWN;
        }
        String normalized = value.trim().toUpperCase();
        try {
            return PersonalityType.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
