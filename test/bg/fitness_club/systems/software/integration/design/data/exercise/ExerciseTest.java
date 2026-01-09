package bg.fitness_club.systems.software.integration.design.data.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {

    @Test
    void testExerciseCreationWithValidData() {
        Exercise exercise = new Exercise("Push-ups");

        assertEquals("Push-ups", exercise.name());
    }

    @Test
    void testExerciseWithNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Exercise(null)
        );
    }

    @Test
    void testExerciseWithEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Exercise("")
        );
    }

    @Test
    void testExerciseWithBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Exercise("   ")
        );
    }

    @Test
    void testExerciseEquality() {
        Exercise exercise1 = new Exercise("Squats");
        Exercise exercise2 = new Exercise("Squats");

        assertEquals(exercise1, exercise2);
    }
}
