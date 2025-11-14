package teammate.service;

import teammate.model.PersonalityType;

public class PersonalityCalculator {

    /**
     * q1..q5 each between 1 and 5.
     * Total range: 5–25, then scaled to 0–100.
     */
    public int calculateScoreOn100(int q1, int q2, int q3, int q4, int q5) {
        int total = q1 + q2 + q3 + q4 + q5; // 5–25
        double scaled = (total / 25.0) * 100.0;
        return (int) Math.round(scaled);
    }

    public PersonalityType classify(int scoreOn100) {
        if (scoreOn100 >= 90) {
            return PersonalityType.LEADER;
        } else if (scoreOn100 >= 70) {
            return PersonalityType.BALANCED;
        } else if (scoreOn100 >= 50) {
            return PersonalityType.THINKER;
        } else {
            return PersonalityType.UNKNOWN;
        }
    }
}
