package bg.fitness_club.systems.software.integration.design.commands.handlers.equipment;

import bg.fitness_club.systems.software.integration.design.commands.handlers.CommandHandler;
import bg.fitness_club.systems.software.integration.design.commands.validators.CommandsValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;

import java.sql.SQLException;

public class GetAllEquipmentCommand extends CommandsValidator implements CommandHandler {
    private final FitnessClubAPI fitnessClub;

    public GetAllEquipmentCommand(FitnessClubAPI fitnessClub) {
        this.fitnessClub = fitnessClub;
    }

    @Override
    public String execute() throws SQLException {
        return fitnessClub.getAllEquipment();
    }
}
