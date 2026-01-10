package bg.fitness_club.systems.software.integration.design.commands.handlers.muscle_group;

import bg.fitness_club.systems.software.integration.design.commands.CommandType;
import bg.fitness_club.systems.software.integration.design.commands.handlers.CommandHandler;
import bg.fitness_club.systems.software.integration.design.commands.validators.CommandsValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

import java.sql.SQLException;

public class GetMuscleGroupsByTrainingNameCommand extends CommandsValidator implements CommandHandler {
    private static final int NUMBER_OF_COMMAND_ARGS = 2;
    private final FitnessClubAPI fitnessClub;
    private final String[] args;

    public GetMuscleGroupsByTrainingNameCommand(FitnessClubAPI fitnessClub, String[] args) {
        this.fitnessClub = fitnessClub;
        this.args = args;
    }

    @Override
    public String execute() throws SQLException {
        validateForSufficientNumberOfArguments(NUMBER_OF_COMMAND_ARGS, args);

        String commandExtension = args[0];
        if (!areCommandTypeEqual(commandExtension, CommandType.TRAINING_NAME)) {
            return "Unknown Command";
        }

        String trainingName = args[1];
        return fitnessClub.getMusleGroupsByTrainingName(trainingName);
    }
}
