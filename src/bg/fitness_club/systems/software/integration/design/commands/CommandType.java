package bg.fitness_club.systems.software.integration.design.commands;

import java.util.Arrays;

public enum CommandType {
    GET("get"),
    TRAININGS("trainings"),
    TRAINING("training"),
    EQUIPMENT("equipment"),
    EXERCISES("exercises"),
    MUSLE_GROUPS("muscle_groups"),
    DURATION("duration"),
    DIFFICULTY("difficulty"),
    FILE("file"),
    ALL("--all"),
    TYPE("--type"),
    LIST_EXERCISES("--exercises"),
    TRAINING_NAME("--training_name"),
    LIST_MUSCLE_GROUPS("--muscle_groups"),
    UNKNOWN("unknown");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandTypeString() {
        return commandType;
    }

    public static CommandType getCommandType(String commandType) {
        return Arrays.stream(values())
            .filter(c -> c.commandType.equalsIgnoreCase(commandType))
            .findFirst()
            .orElse(UNKNOWN);
    }
}