package bg.fitness_club.systems.software.integration.design.command.handlers.equipment;

import bg.fitness_club.systems.software.integration.design.command.CommandType;
import bg.fitness_club.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

import java.sql.SQLException;
import java.util.Arrays;

public class EquipmentCommandExecutor extends CommandExecutorValidator {
    private final FitnessClubAPI fitnessClub;

    public EquipmentCommandExecutor(FitnessClubAPI fitnessClub) {
        this.fitnessClub = fitnessClub;
    }

    public String execute(String[] arguments) throws SQLException {
        validateArgumentsLength(arguments);
        CommandType commandType = CommandType.getCommandType(arguments[0]);

        return switch (commandType) {
            case ALL -> new GetAllEquipmentCommand(fitnessClub).execute();
            case TRAINING_NAME -> new GetAllEquipmentByTrainingName(fitnessClub,
                Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            default -> "Unknown command";
        };
    }
}
