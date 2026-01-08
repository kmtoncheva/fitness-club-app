package bg.fitness_club.systems.software.integration.design.command.handlers.training;

import bg.fitness_club.systems.software.integration.design.command.handlers.CommandHandler;
import bg.fitness_club.systems.software.integration.design.command.validators.CommandsValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

import java.sql.SQLException;

public class GetAllTrainingsCommand extends CommandsValidator implements CommandHandler {
    private final FitnessClubAPI fitnessClub;

    public GetAllTrainingsCommand(FitnessClubAPI fitnessClub) {
        this.fitnessClub = fitnessClub;
    }

    @Override
    public String execute() throws SQLException {
        return fitnessClub.getAllTrainings();
    }
}
