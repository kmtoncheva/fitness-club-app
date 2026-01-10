package bg.fitness_club.systems.software.integration.design.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTypeTest {

    @Test
    void testGetCommandTypeForGet() {
        CommandType type = CommandType.getCommandType("get");
        assertEquals(CommandType.GET, type);
    }

    @Test
    void testGetCommandTypeForTrainings() {
        CommandType type = CommandType.getCommandType("trainings");
        assertEquals(CommandType.TRAININGS, type);
    }

    @Test
    void testGetCommandTypeForTraining() {
        CommandType type = CommandType.getCommandType("training");
        assertEquals(CommandType.TRAINING, type);
    }

    @Test
    void testGetCommandTypeForEquipment() {
        CommandType type = CommandType.getCommandType("equipment");
        assertEquals(CommandType.EQUIPMENT, type);
    }

    @Test
    void testGetCommandTypeForExercises() {
        CommandType type = CommandType.getCommandType("exercises");
        assertEquals(CommandType.EXERCISES, type);
    }

    @Test
    void testGetCommandTypeForMuscleGroups() {
        CommandType type = CommandType.getCommandType("muscle_groups");
        assertEquals(CommandType.MUSLE_GROUPS, type);
    }

    @Test
    void testGetCommandTypeForDuration() {
        CommandType type = CommandType.getCommandType("duration");
        assertEquals(CommandType.DURATION, type);
    }

    @Test
    void testGetCommandTypeForDifficulty() {
        CommandType type = CommandType.getCommandType("difficulty");
        assertEquals(CommandType.DIFFICULTY, type);
    }

    @Test
    void testGetCommandTypeForAll() {
        CommandType type = CommandType.getCommandType("--all");
        assertEquals(CommandType.ALL, type);
    }

    @Test
    void testGetCommandTypeForType() {
        CommandType type = CommandType.getCommandType("--type");
        assertEquals(CommandType.TYPE, type);
    }

    @Test
    void testGetCommandTypeForUnknown() {
        CommandType type = CommandType.getCommandType("invalid");
        assertEquals(CommandType.UNKNOWN, type);
    }

    @Test
    void testGetCommandTypeIsCaseInsensitive() {
        CommandType type1 = CommandType.getCommandType("TRAININGS");
        CommandType type2 = CommandType.getCommandType("Trainings");
        CommandType type3 = CommandType.getCommandType("trainings");

        assertEquals(CommandType.TRAININGS, type1);
        assertEquals(CommandType.TRAININGS, type2);
        assertEquals(CommandType.TRAININGS, type3);
    }

    @Test
    void testGetCommandTypeStringReturnsCorrectValue() {
        assertEquals("trainings", CommandType.TRAININGS.getCommandTypeString());
        assertEquals("equipment", CommandType.EQUIPMENT.getCommandTypeString());
        assertEquals("--all", CommandType.ALL.getCommandTypeString());
    }
}
