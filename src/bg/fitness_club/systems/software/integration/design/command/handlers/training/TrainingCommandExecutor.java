package bg.fitness_club.systems.software.integration.design.command.handlers.training;

import bg.fitness_club.systems.software.integration.design.command.CommandType;
import bg.fitness_club.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

import java.sql.SQLException;
import java.util.Arrays;

public class TrainingCommandExecutor extends CommandExecutorValidator {
    private final FitnessClubAPI fitnessClub;

    public TrainingCommandExecutor(FitnessClubAPI fitnessClub) {
        this.fitnessClub = fitnessClub;
    }

    public String execute(String[] arguments) throws SQLException {
        validateArgumentsLength(arguments);
        CommandType commandType = CommandType.getCommandType(arguments[0]);

        return switch (commandType) {
            case ALL -> new GetAllTrainingsCommand(fitnessClub).execute();
            case TYPE -> new GetAllTrainingsByTypeCommand(fitnessClub,
                Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            case LIST_EXERCISES -> new GetAllTrainingsByExercisesCommand(fitnessClub,
                Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            case LIST_MUSCLE_GROUPS -> new GetAllTrainingsByMuscleGroupCommand(fitnessClub,
                Arrays.copyOfRange(arguments, 1, arguments.length)).execute();
            default -> "Unknown command";
        };
    }
}
