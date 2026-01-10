package bg.fitness_club.systems.software.integration.design.commands.handlers.training;

import bg.fitness_club.systems.software.integration.design.commands.handlers.CommandHandler;
import bg.fitness_club.systems.software.integration.design.commands.validators.CommandsValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

import java.sql.SQLException;
import java.util.List;

public class GetAllTrainingsByExercisesCommand extends CommandsValidator implements CommandHandler {
    private final FitnessClubAPI fitnessClub;
    private final String[] args;

    public GetAllTrainingsByExercisesCommand(FitnessClubAPI fitnessClub, String[] args) {
        this.fitnessClub = fitnessClub;
        this.args = args;
    }

    @Override
    public String execute() throws SQLException {
        validateArgumentsLength(args);

        return fitnessClub.getAllTrainingsByExercises(List.of(args));
    }
}
