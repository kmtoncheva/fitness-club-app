package bg.fitness_club.systems.software.integration.design.command;

import bg.fitness_club.systems.software.integration.design.command.handlers.difficulty.GetDifficultyByTrainingNameCommand;
import bg.fitness_club.systems.software.integration.design.command.handlers.equipment.EquipmentCommandExecutor;
import bg.fitness_club.systems.software.integration.design.command.handlers.duration.GetDurationForTrainingByNameCommand;
import bg.fitness_club.systems.software.integration.design.command.handlers.exercise.GetExercisesByTrainingNameCommand;
import bg.fitness_club.systems.software.integration.design.command.handlers.muscle_group.GetMuscleGroupsByTrainingNameCommand;
import bg.fitness_club.systems.software.integration.design.command.handlers.training.GetTrainingByNameCommand;
import bg.fitness_club.systems.software.integration.design.command.handlers.training.TrainingCommandExecutor;
import bg.fitness_club.systems.software.integration.design.command.validators.CommandExecutorValidator;
import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;
import bg.fitness_club.systems.software.integration.design.logger.ErrorLogger;

import java.io.IOException;

public class CommandExecutor extends CommandExecutorValidator {
    private final FitnessClubAPI fitnessClub;
    private final ErrorLogger errorLogger;

    public CommandExecutor(FitnessClubAPI fitnessClub, ErrorLogger errorLogger) {
        this.errorLogger = errorLogger;
        validateConstructorParameter(fitnessClub);

        this.fitnessClub = fitnessClub;
    }

    public String execute(Command cmd) {
        try {
            validateCommand(cmd);

            CommandType commandType = CommandType.getCommandType(cmd.command());

            return switch (commandType) {
                case TRAININGS -> new TrainingCommandExecutor(fitnessClub).execute(cmd.arguments()); //new
                case TRAINING -> new GetTrainingByNameCommand(fitnessClub, cmd.arguments()).execute(); //new
                case EQUIPMENT -> new EquipmentCommandExecutor(fitnessClub).execute(cmd.arguments());
                case EXERCISES -> new GetExercisesByTrainingNameCommand(fitnessClub, cmd.arguments()).execute();
                case MUSLE_GROUPS -> new GetMuscleGroupsByTrainingNameCommand(fitnessClub, cmd.arguments()).execute();
                case DURATION ->
                    new GetDurationForTrainingByNameCommand(fitnessClub, cmd.arguments()).execute();
                case DIFFICULTY -> new GetDifficultyByTrainingNameCommand(fitnessClub, cmd.arguments()).execute();
                default -> "Unknown Command";
            };
        } catch (Exception e) {
            try {
                errorLogger.appendLogs(e);
            } catch (IOException ex) {
                return "There was an error in the application, please try again!";
            }
            // logs e.getMessage();
            return "There was an error in the application, please try again!";
        }
    }
}