package bg.fitness_club.systems.software.integration.design.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void testCommandCreationWithValidData() {
        String[] args = {"--all"};
        Command command = new Command("trainings", args);

        assertEquals("trainings", command.command());
        assertArrayEquals(args, command.arguments());
    }

    @Test
    void testCommandWithMultipleArguments() {
        String[] args = {"--type", "cardio", "strength"};
        Command command = new Command("trainings", args);

        assertEquals("trainings", command.command());
        assertEquals(3, command.arguments().length);
        assertEquals("--type", command.arguments()[0]);
        assertEquals("cardio", command.arguments()[1]);
        assertEquals("strength", command.arguments()[2]);
    }

    @Test
    void testCommandEquality() {
        String[] args1 = {"--all"};
        String[] args2 = {"--all"};
        
        Command command1 = new Command("trainings", args1);
        Command command2 = new Command("trainings", args2);

        assertEquals(command1, command2);
    }
}
