package bg.fitness_club.systems.software.integration.design.command.handlers.training;

import bg.fitness_club.systems.software.integration.design.command.handlers.CommandHandler;
import bg.fitness_club.systems.software.integration.design.command.validators.CommandsValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

import java.sql.SQLException;
import java.util.List;

public class GetAllTrainingsByTypeCommand extends CommandsValidator implements CommandHandler {
    private final FitnessClubAPI fitnessClub;
    private final String[] args;

    public GetAllTrainingsByTypeCommand(FitnessClubAPI fitnessClub, String[] args) {
        this.fitnessClub = fitnessClub;
        this.args = args;
    }

    @Override
    public String execute() throws SQLException {
        validateArgumentsLength(args);

        return fitnessClub.getAllTrainingsByType(List.of(args));
    }
}
