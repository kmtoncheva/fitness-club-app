package bg.fitness_club.systems.software.integration.design.data.difficulty;

import java.util.Arrays;

public enum Difficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    UNKNOWN("unknown");

    private final String difficulty;

    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTypeString() {
        return difficulty;
    }

    public static Difficulty getType(String difficulty) {
        return Arrays.stream(values())
            .filter(c -> c.difficulty.equalsIgnoreCase(difficulty))
            .findFirst()
            .orElse(UNKNOWN);
    }
}