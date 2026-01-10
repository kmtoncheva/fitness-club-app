package bg.fitness_club.systems.software.integration.design.commands.handlers.equipment;

import bg.fitness_club.systems.software.integration.design.commands.handlers.CommandHandler;
import bg.fitness_club.systems.software.integration.design.commands.validators.CommandsValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

import java.sql.SQLException;

public class GetAllEquipmentByTrainingName extends CommandsValidator implements CommandHandler {
    private final FitnessClubAPI fitnessClub;
    private final String[] args;

    public GetAllEquipmentByTrainingName(FitnessClubAPI fitnessClub, String[] args) {
        this.fitnessClub = fitnessClub;
        this.args = args;
    }

    @Override
    public String execute() throws SQLException {
        validateArgumentsLength(args);

        String trainingName = args[0];
        return fitnessClub.getAllEquipmentByTrainingName(trainingName);
    }
}
