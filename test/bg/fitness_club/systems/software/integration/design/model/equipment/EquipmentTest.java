package bg.fitness_club.systems.software.integration.design.model.equipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {

    @Test
    void testEquipmentCreationWithValidData() {
        Equipment equipment = new Equipment("Dumbbells", "free_weights", "medium");

        assertEquals("Dumbbells", equipment.name());
        assertEquals("free_weights", equipment.type());
        assertEquals("medium", equipment.difficulty());
    }

    @Test
    void testEquipmentWithNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Equipment(null, "free_weights", "medium")
        );
    }

    @Test
    void testEquipmentWithEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Equipment("", "free_weights", "medium")
        );
    }

    @Test
    void testEquipmentWithBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Equipment("   ", "free_weights", "medium")
        );
    }

    @Test
    void testEquipmentWithNullTypeThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Equipment("Dumbbells", null, "medium")
        );
    }

    @Test
    void testEquipmentWithNullDifficultyThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            new Equipment("Dumbbells", "free_weights", null)
        );
    }
}
