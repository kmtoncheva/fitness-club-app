package bg.fitness_club.systems.software.integration.design.commands.validators;

import bg.fitness_club.systems.software.integration.design.commands.Command;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

public abstract class CommandExecutorValidator {
    protected void validateArgumentsLength(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("There are not enough arguments to fulfil the request!");
        }
    }

    protected void validateConstructorParameter(FitnessClubAPI fitness) {
        if (fitness == null) {
            throw new IllegalArgumentException("The fitness cannot be null!");
        }
    }

    protected void validateCommand(Command cmd) {
        if (cmd == null) {
            throw new IllegalArgumentException("The command cannot be null!");
        }
    }
}
