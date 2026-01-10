package bg.fitness_club.systems.software.integration.design.commands.validators;

import bg.fitness_club.systems.software.integration.design.commands.CommandType;

public abstract class CommandsValidator {
    protected void validateArgumentsLength(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The number of arguments must be at least one!");
        }
    }

    protected boolean areCommandTypeEqual(String currentType, CommandType neededType) {
        return CommandType.getCommandType(currentType).equals(neededType);
    }

    protected void validateForSufficientNumberOfArguments(int argsNumber, String[] args) {
        if (args.length != argsNumber) {
            throw new IllegalArgumentException("The number of arguments for this command must be " + argsNumber);
        }
    }
}
