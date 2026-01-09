package bg.fitness_club.systems.software.integration.design.data.difficulty;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifficultyTest {

    @Test
    void testGetDifficultyWithValidEasy() {
        Difficulty difficulty = Difficulty.getType("easy");
        assertEquals(Difficulty.EASY, difficulty);
        assertEquals("easy", difficulty.getTypeString());
    }

    @Test
    void testGetDifficultyWithValidMedium() {
        Difficulty difficulty = Difficulty.getType("medium");
        assertEquals(Difficulty.MEDIUM, difficulty);
        assertEquals("medium", difficulty.getTypeString());
    }

    @Test
    void testGetDifficultyWithValidHard() {
        Difficulty difficulty = Difficulty.getType("hard");
        assertEquals(Difficulty.HARD, difficulty);
        assertEquals("hard", difficulty.getTypeString());
    }

    @Test
    void testGetDifficultyWithInvalidTypeReturnsUnknown() {
        Difficulty difficulty = Difficulty.getType("invalid");
        assertEquals(Difficulty.UNKNOWN, difficulty);
        assertEquals("unknown", difficulty.getTypeString());
    }

    @Test
    void testGetDifficultyIsCaseInsensitive() {
        Difficulty difficulty1 = Difficulty.getType("EASY");
        Difficulty difficulty2 = Difficulty.getType("Easy");
        Difficulty difficulty3 = Difficulty.getType("easy");

        assertEquals(Difficulty.EASY, difficulty1);
        assertEquals(Difficulty.EASY, difficulty2);
        assertEquals(Difficulty.EASY, difficulty3);
    }
}
