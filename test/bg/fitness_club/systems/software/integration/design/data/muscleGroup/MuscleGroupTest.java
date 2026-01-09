package bg.fitness_club.systems.software.integration.design.data.muscleGroup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MuscleGroupTest {

    @Test
    void testMuscleGroupCreationWithValidData() {
        MuscleGroup muscleGroup = new MuscleGroup("Chest");

        assertEquals("Chest", muscleGroup.name());
    }

    @Test
    void testMuscleGroupWithNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new MuscleGroup(null)
        );
    }

    @Test
    void testMuscleGroupWithEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new MuscleGroup("")
        );
    }

    @Test
    void testMuscleGroupWithBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new MuscleGroup("   ")
        );
    }

    @Test
    void testMuscleGroupEquality() {
        MuscleGroup muscleGroup1 = new MuscleGroup("Legs");
        MuscleGroup muscleGroup2 = new MuscleGroup("Legs");

        assertEquals(muscleGroup1, muscleGroup2);
    }
}
