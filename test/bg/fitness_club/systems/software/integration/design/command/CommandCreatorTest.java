package bg.fitness_club.systems.software.integration.design.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandCreatorTest {

    @Test
    void testCreateCommandWithGetTrainingsAll() {
        Command command = CommandCreator.newCommand("get trainings --all");

        assertEquals("trainings", command.command());
        assertEquals(1, command.arguments().length);
        assertEquals("--all", command.arguments()[0]);
    }

    @Test
    void testCreateCommandWithGetTrainingByName() {
        Command command = CommandCreator.newCommand("get training --training_name \"Full Body Beginner\"");

        assertEquals("training", command.command());
        assertEquals(2, command.arguments().length);
        assertEquals("--training_name", command.arguments()[0]);
        assertEquals("Full Body Beginner", command.arguments()[1]);
    }

    @Test
    void testCreateCommandWithGetTrainingsByType() {
        Command command = CommandCreator.newCommand("get trainings --type [\"cardio\", \"strength\"]");

        assertEquals("trainings", command.command());
        assertTrue(command.arguments().length >= 2);
        assertEquals("--type", command.arguments()[0]);
    }

    @Test
    void testCreateCommandWithGetEquipmentAll() {
        Command command = CommandCreator.newCommand("get equipment --all");

        assertEquals("equipment", command.command());
        assertEquals(1, command.arguments().length);
        assertEquals("--all", command.arguments()[0]);
    }

    @Test
    void testCreateCommandWithGetExercises() {
        Command command = CommandCreator.newCommand("get exercises --training_name \"Cardio Burn\"");

        assertEquals("exercises", command.command());
        assertEquals(2, command.arguments().length);
        assertEquals("--training_name", command.arguments()[0]);
        assertEquals("Cardio Burn", command.arguments()[1]);
    }

    @Test
    void testCreateCommandWithGetDuration() {
        Command command = CommandCreator.newCommand("get duration --training_name \"Morning Yoga Flow\"");

        assertEquals("duration", command.command());
        assertEquals(2, command.arguments().length);
        assertEquals("--training_name", command.arguments()[0]);
        assertEquals("Morning Yoga Flow", command.arguments()[1]);
    }

    @Test
    void testCreateCommandWithInvalidInput() {
        Command command = CommandCreator.newCommand("invalid command");

        assertEquals("Unknown", command.command());
    }

    @Test
    void testCreateCommandHandlesExtraWhitespace() {
        Command command = CommandCreator.newCommand("  get   trainings   --all  ");

        assertEquals("trainings", command.command());
        assertEquals(1, command.arguments().length);
        assertEquals("--all", command.arguments()[0]);
    }
}
