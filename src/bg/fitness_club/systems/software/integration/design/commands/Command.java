package bg.fitness_club.systems.software.integration.design.commands;

import java.util.Arrays;
import java.util.Objects;

public record Command(String command, String[] arguments) {
    public Command {
        if (command == null || command.isEmpty() || command.isBlank()) {
            throw new IllegalArgumentException("The command cannot be null, empty or blank!");
        }

        if (arguments == null) {
            throw new IllegalArgumentException("The arguments cannot be null!");
        }

        for (String argument : arguments) {
            if (argument == null || argument.isEmpty() || argument.isBlank()) {
                throw new IllegalArgumentException("None of the arguments can be null!");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command other)) return false;
        return command.equals(other.command)
            && Arrays.equals(arguments, other.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, Arrays.hashCode(arguments));
    }

    public static Command of(String command, String[] arguments) {
        return new Command(command, arguments);
    }
}