package bg.fitness_club.systems.software.integration.design.data.training;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTypeTest {

    @Test
    void testGetTrainingTypeWithValidStrength() {
        TrainingType type = TrainingType.getTrainingType("strenght");
        assertEquals(TrainingType.STRENGHT, type);
        assertEquals("strenght", type.getTrainingTypeString());
    }

    @Test
    void testGetTrainingTypeWithValidFullBody() {
        TrainingType type = TrainingType.getTrainingType("full_body");
        assertEquals(TrainingType.FULL_BODY, type);
        assertEquals("full_body", type.getTrainingTypeString());
    }

    @Test
    void testGetTrainingTypeWithValidCardio() {
        TrainingType type = TrainingType.getTrainingType("cardio");
        assertEquals(TrainingType.CARDIO, type);
        assertEquals("cardio", type.getTrainingTypeString());
    }

    @Test
    void testGetTrainingTypeWithValidYoga() {
        TrainingType type = TrainingType.getTrainingType("yoga");
        assertEquals(TrainingType.YOGA, type);
        assertEquals("yoga", type.getTrainingTypeString());
    }

    @Test
    void testGetTrainingTypeWithValidPilates() {
        TrainingType type = TrainingType.getTrainingType("pilates");
        assertEquals(TrainingType.PILATES, type);
        assertEquals("pilates", type.getTrainingTypeString());
    }

    @Test
    void testGetTrainingTypeWithInvalidTypeReturnsUnknown() {
        TrainingType type = TrainingType.getTrainingType("invalid_type");
        assertEquals(TrainingType.UNKNOWN, type);
        assertEquals("unknown", type.getTrainingTypeString());
    }

    @Test
    void testGetTrainingTypeIsCaseInsensitive() {
        TrainingType type1 = TrainingType.getTrainingType("CARDIO");
        TrainingType type2 = TrainingType.getTrainingType("Cardio");
        TrainingType type3 = TrainingType.getTrainingType("cardio");

        assertEquals(TrainingType.CARDIO, type1);
        assertEquals(TrainingType.CARDIO, type2);
        assertEquals(TrainingType.CARDIO, type3);
    }
}
