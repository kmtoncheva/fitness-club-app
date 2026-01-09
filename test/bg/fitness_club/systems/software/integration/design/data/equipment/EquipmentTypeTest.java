package bg.fitness_club.systems.software.integration.design.data.equipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTypeTest {

    @Test
    void testGetEquipmentTypeWithValidFreeWeights() {
        EquipmentType type = EquipmentType.getEquipmentType("free_weights");
        assertEquals(EquipmentType.FREE_WEIGHTS, type);
        assertEquals("free_weights", type.getEquipmentTypeString());
    }

    @Test
    void testGetEquipmentTypeWithValidMat() {
        EquipmentType type = EquipmentType.getEquipmentType("mat");
        assertEquals(EquipmentType.MAT, type);
        assertEquals("mat", type.getEquipmentTypeString());
    }

    @Test
    void testGetEquipmentTypeWithValidBodyweight() {
        EquipmentType type = EquipmentType.getEquipmentType("bodyweight");
        assertEquals(EquipmentType.BODYWEIGHT, type);
        assertEquals("bodyweight", type.getEquipmentTypeString());
    }

    @Test
    void testGetEquipmentTypeWithValidNone() {
        EquipmentType type = EquipmentType.getEquipmentType("none");
        assertEquals(EquipmentType.NONE, type);
        assertEquals("none", type.getEquipmentTypeString());
    }

    @Test
    void testGetEquipmentTypeWithInvalidTypeReturnsUnknown() {
        EquipmentType type = EquipmentType.getEquipmentType("invalid_type");
        assertEquals(EquipmentType.UNKNOWN, type);
        assertEquals("unknown", type.getEquipmentTypeString());
    }

    @Test
    void testGetEquipmentTypeIsCaseInsensitive() {
        EquipmentType type1 = EquipmentType.getEquipmentType("FREE_WEIGHTS");
        EquipmentType type2 = EquipmentType.getEquipmentType("Free_Weights");
        EquipmentType type3 = EquipmentType.getEquipmentType("free_weights");

        assertEquals(EquipmentType.FREE_WEIGHTS, type1);
        assertEquals(EquipmentType.FREE_WEIGHTS, type2);
        assertEquals(EquipmentType.FREE_WEIGHTS, type3);
    }
}
