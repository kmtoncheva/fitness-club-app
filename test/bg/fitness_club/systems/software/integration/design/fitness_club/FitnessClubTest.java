package bg.fitness_club.systems.software.integration.design.fitness_club;

import bg.fitness_club.systems.software.integration.design.data.difficulty.Difficulty;
import bg.fitness_club.systems.software.integration.design.data.equipment.Equipment;
import bg.fitness_club.systems.software.integration.design.data.exercise.Exercise;
import bg.fitness_club.systems.software.integration.design.data.muscleGroup.MuscleGroup;
import bg.fitness_club.systems.software.integration.design.data.training.Training;
import bg.fitness_club.systems.software.integration.design.storage.Queries;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FitnessClubTest {

    @Mock
    private Queries queries;

    private FitnessClub fitnessClub;
    private Gson gson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gson = new Gson();
        fitnessClub = new FitnessClub(queries, gson);
    }

    @Test
    void testGetAllTrainingsReturnsJsonString() throws SQLException {
        Set<Training> trainings = createSampleTrainings();
        when(queries.getAllTrainings()).thenReturn(trainings);

        String result = fitnessClub.getAllTrainings();

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getAllTrainings();
    }

    @Test
    void testGetAllTrainingsReturnsNoResultWhenEmpty() throws SQLException {
        when(queries.getAllTrainings()).thenReturn(new HashSet<>());

        String result = fitnessClub.getAllTrainings();

        assertEquals("No result!", result);
    }

    @Test
    void testGetTrainingByNameReturnsTraining() throws SQLException {
        Set<Training> trainings = createSampleTrainings();
        when(queries.getTrainingsByName("Cardio Burn")).thenReturn(trainings);

        String result = fitnessClub.getTrainingByName("Cardio Burn");

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getTrainingsByName("Cardio Burn");
    }

    @Test
    void testGetTrainingByNameReturnsNoResultWhenNotFound() throws SQLException {
        when(queries.getTrainingsByName("NonExistent")).thenReturn(new HashSet<>());

        String result = fitnessClub.getTrainingByName("NonExistent");

        assertEquals("No result!", result);
    }

    @Test
    void testGetAllTrainingsByTypeReturnsTrainings() throws SQLException {
        Set<Training> trainings = createSampleTrainings();
        List<String> types = List.of("cardio", "strength");
        when(queries.getAllTrainingsByType(types)).thenReturn(trainings);

        String result = fitnessClub.getAllTrainingsByType(types);

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getAllTrainingsByType(types);
    }

    @Test
    void testGetAllTrainingsByExercisesReturnsTrainings() throws SQLException {
        Set<Training> trainings = createSampleTrainings();
        List<String> exercises = List.of("Push-ups", "Squats");
        when(queries.getAllTrainingsByExercise(exercises)).thenReturn(trainings);

        String result = fitnessClub.getAllTrainingsByExercises(exercises);

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getAllTrainingsByExercise(exercises);
    }

    @Test
    void testGetAllTrainingsByMuscleGroupsReturnsTrainings() throws SQLException {
        Set<Training> trainings = createSampleTrainings();
        List<String> muscleGroups = List.of("Chest", "Legs");
        when(queries.getAllTrainingsByMusleGroups(muscleGroups)).thenReturn(trainings);

        String result = fitnessClub.getAllTrainingsByMuscleGroups(muscleGroups);

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getAllTrainingsByMusleGroups(muscleGroups);
    }

    @Test
    void testGetDifficultyByTrainingNameReturnsDifficulty() throws SQLException {
        when(queries.getDifficultyByTrainingName("Cardio Burn")).thenReturn(Difficulty.MEDIUM);

        String result = fitnessClub.getDifficultyByTrainingName("Cardio Burn");

        assertNotNull(result);
        assertTrue(result.contains("medium"));
        verify(queries, times(1)).getDifficultyByTrainingName("Cardio Burn");
    }

    @Test
    void testGetDurationForTrainingByNameReturnsDuration() throws SQLException {
        when(queries.getDurationForTrainingByName("Morning Yoga Flow")).thenReturn(40);

        String result = fitnessClub.getDurationForTrainingByName("Morning Yoga Flow");

        assertNotNull(result);
        assertTrue(result.contains("40"));
        assertTrue(result.contains("minutes"));
        verify(queries, times(1)).getDurationForTrainingByName("Morning Yoga Flow");
    }

    @Test
    void testGetAllEquipmentReturnsEquipment() throws SQLException {
        Set<Equipment> equipment = Set.of(
            new Equipment("Dumbbells", "free_weights", "medium"),
            new Equipment("Yoga Mat", "mat", "easy")
        );
        when(queries.getAllEquipment()).thenReturn(equipment);

        String result = fitnessClub.getAllEquipment();

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getAllEquipment();
    }

    @Test
    void testGetAllEquipmentByTrainingNameReturnsEquipment() throws SQLException {
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));
        when(queries.getAllEquipmentByTrainingName("Full Body Beginner")).thenReturn(equipment);

        String result = fitnessClub.getAllEquipmentByTrainingName("Full Body Beginner");

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getAllEquipmentByTrainingName("Full Body Beginner");
    }

    @Test
    void testGetExercisesByTrainingNameReturnsExercises() throws SQLException {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"), new Exercise("Squats"));
        when(queries.getExercisesByTrainingName("Full Body Beginner")).thenReturn(exercises);

        String result = fitnessClub.getExercisesByTrainingName("Full Body Beginner");

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getExercisesByTrainingName("Full Body Beginner");
    }

    @Test
    void testGetMuscleGroupsByTrainingNameReturnsMuscleGroups() throws SQLException {
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"), new MuscleGroup("Legs"));
        when(queries.getMuscleGroupsByTrainingName("Full Body Beginner")).thenReturn(muscleGroups);

        String result = fitnessClub.getMusleGroupsByTrainingName("Full Body Beginner");

        assertNotNull(result);
        assertFalse(result.equals("No result!"));
        verify(queries, times(1)).getMuscleGroupsByTrainingName("Full Body Beginner");
    }

    private Set<Training> createSampleTrainings() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"), new Exercise("Squats"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"), new MuscleGroup("Legs"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        Training training = new Training(
            "Full Body Beginner",
            "full_body",
            "easy",
            exercises,
            45,
            muscleGroups,
            equipment
        );

        return Set.of(training);
    }
}
