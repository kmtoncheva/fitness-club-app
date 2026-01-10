package bg.fitness_club.systems.software.integration.design.commands;

import bg.fitness_club.systems.software.integration.design.fitness_club.FitnessClubAPI;
import bg.fitness_club.systems.software.integration.design.logger.ErrorLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandExecutorTest {

    @Mock
    private FitnessClubAPI fitnessClub;

    @Mock
    private ErrorLogger errorLogger;

    private CommandExecutor commandExecutor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commandExecutor = new CommandExecutor(fitnessClub, errorLogger);
    }

    @Test
    void testExecuteTrainingsCommand() throws SQLException {
        when(fitnessClub.getAllTrainings()).thenReturn("{\"trainings\": []}");
        
        Command command = new Command("trainings", new String[]{"--all"});
        String result = commandExecutor.execute(command);

        assertNotNull(result);
        verify(fitnessClub, times(1)).getAllTrainings();
    }

    @Test
    void testExecuteTrainingByNameCommand() throws SQLException {
        when(fitnessClub.getTrainingByName("Cardio Burn")).thenReturn("{\"name\": \"Cardio Burn\"}");
        
        Command command = new Command("training", new String[]{"--training_name", "Cardio Burn"});
        String result = commandExecutor.execute(command);

        assertNotNull(result);
        verify(fitnessClub, times(1)).getTrainingByName("Cardio Burn");
    }

    @Test
    void testExecuteEquipmentCommand() throws SQLException {
        when(fitnessClub.getAllEquipment()).thenReturn("{\"equipment\": []}");
        
        Command command = new Command("equipment", new String[]{"--all"});
        String result = commandExecutor.execute(command);

        assertNotNull(result);
        verify(fitnessClub, times(1)).getAllEquipment();
    }

    @Test
    void testExecuteExercisesCommand() throws SQLException {
        when(fitnessClub.getExercisesByTrainingName("Full Body Beginner"))
            .thenReturn("{\"exercises\": []}");
        
        Command command = new Command("exercises", new String[]{"--training_name", "Full Body Beginner"});
        String result = commandExecutor.execute(command);

        assertNotNull(result);
        verify(fitnessClub, times(1)).getExercisesByTrainingName("Full Body Beginner");
    }

    @Test
    void testExecuteDurationCommand() throws SQLException {
        when(fitnessClub.getDurationForTrainingByName("Morning Yoga Flow"))
            .thenReturn("40 minutes");
        
        Command command = new Command("duration", new String[]{"--training_name", "Morning Yoga Flow"});
        String result = commandExecutor.execute(command);

        assertNotNull(result);
        verify(fitnessClub, times(1)).getDurationForTrainingByName("Morning Yoga Flow");
    }

    @Test
    void testExecuteDifficultyCommand() throws SQLException {
        when(fitnessClub.getDifficultyByTrainingName("Upper Body Strength"))
            .thenReturn("hard");
        
        Command command = new Command("difficulty", new String[]{"--training_name", "Upper Body Strength"});
        String result = commandExecutor.execute(command);

        assertNotNull(result);
        verify(fitnessClub, times(1)).getDifficultyByTrainingName("Upper Body Strength");
    }

    @Test
    void testExecuteUnknownCommandReturnsUnknownMessage() {
        Command command = new Command("invalid", new String[]{});
        String result = commandExecutor.execute(command);

        assertEquals("Unknown Command", result);
    }

    @Test
    void testExecuteWithNullCommandThrowsException() {
        assertEquals("There was an error in the application, please try again!",
            commandExecutor.execute(null));
    }

    @Test
    void testConstructorWithNullFitnessClubThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new CommandExecutor(null, errorLogger)
        );
    }
}
