package bg.fitness_club.systems.software.integration.design.data.training;

import bg.fitness_club.systems.software.integration.design.data.equipment.Equipment;
import bg.fitness_club.systems.software.integration.design.data.exercise.Exercise;
import bg.fitness_club.systems.software.integration.design.data.muscleGroup.MuscleGroup;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTest {

    @Test
    void testTrainingCreationWithValidData() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"), new Exercise("Squats"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"), new MuscleGroup("Legs"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        Training training = new Training("Full Body Workout", "full_body", "medium", exercises, 45, muscleGroups, equipment);

        assertEquals("Full Body Workout", training.name());
        assertEquals("full_body", training.type());
        assertEquals("medium", training.difficulty());
        assertEquals(45, training.duration());
        assertEquals(2, training.exercises().size());
        assertEquals(2, training.muscleGroup().size());
        assertEquals(1, training.equipment().size());
    }

    @Test
    void testTrainingWithNullNameThrowsException() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training(null, "full_body", "medium", exercises, 45, muscleGroups, equipment)
        );
    }

    @Test
    void testTrainingWithEmptyNameThrowsException() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training("", "full_body", "medium", exercises, 45, muscleGroups, equipment)
        );
    }

    @Test
    void testTrainingWithBlankNameThrowsException() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training("   ", "full_body", "medium", exercises, 45, muscleGroups, equipment)
        );
    }

    @Test
    void testTrainingWithNullTypeThrowsException() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training("Full Body Workout", null, "medium", exercises, 45, muscleGroups, equipment)
        );
    }

    @Test
    void testTrainingWithNullDifficultyThrowsException() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training("Full Body Workout", "full_body", null, exercises, 45, muscleGroups, equipment)
        );
    }

    @Test
    void testTrainingWithNullExercisesThrowsException() {
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training("Full Body Workout", "full_body", "medium", null, 45, muscleGroups, equipment)
        );
    }

    @Test
    void testTrainingWithEmptyExercisesThrowsException() {
        Set<Exercise> exercises = Set.of();
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training("Full Body Workout", "full_body", "medium", exercises, 45, muscleGroups, equipment)
        );
    }

    @Test
    void testTrainingWithZeroDurationThrowsException() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training("Full Body Workout", "full_body", "medium", exercises, 0, muscleGroups, equipment)
        );
    }

    @Test
    void testTrainingWithNegativeDurationThrowsException() {
        Set<Exercise> exercises = Set.of(new Exercise("Push-ups"));
        Set<MuscleGroup> muscleGroups = Set.of(new MuscleGroup("Chest"));
        Set<Equipment> equipment = Set.of(new Equipment("Dumbbells", "free_weights", "medium"));

        assertThrows(IllegalArgumentException.class, () ->
            new Training("Full Body Workout", "full_body", "medium", exercises, -10, muscleGroups, equipment)
        );
    }
}
