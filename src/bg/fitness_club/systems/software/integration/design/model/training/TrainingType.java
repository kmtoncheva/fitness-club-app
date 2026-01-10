package bg.fitness_club.systems.software.integration.design.model.training;

import java.util.Arrays;

public enum TrainingType {
    STRENGHT("strenght"),
    FULL_BODY("full_body"),
    CARDIO("cardio"),
    PILATES("pilates"),
    YOGA("yoga"),
    UNKNOWN("unknown");

    private final String trainingType;

    TrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getTrainingTypeString() {
        return trainingType;
    }

    public static TrainingType getTrainingType(String trainingType) {
        return Arrays.stream(values())
            .filter(c -> c.trainingType.equalsIgnoreCase(trainingType))
            .findFirst()
            .orElse(UNKNOWN);
    }
}