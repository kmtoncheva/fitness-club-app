package bg.fitness_club.systems.software.integration.design.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void testDatabaseConnectionCreation() {
        assertDoesNotThrow(() -> {
            DatabaseConnection connection = new DatabaseConnection(
                "jdbc:mysql://localhost:3306/fitness_club",
                "root",
                ""
            );
            assertNotNull(connection);
        });
    }

    @Test
    void testDatabaseConnectionWithInvalidUrl() {
        assertDoesNotThrow(() -> {
            DatabaseConnection connection = new DatabaseConnection(
                "invalid_url",
                "root",
                ""
            );
            assertNotNull(connection);
        });
    }

    @Test
    void testExecuteQueryWithValidConnection() {
        DatabaseConnection connection = new DatabaseConnection(
            "jdbc:mysql://localhost:3306/fitness_club",
            "root",
            ""
        );

        assertDoesNotThrow(() -> {
            connection.executeQuery("SELECT * FROM trainings LIMIT 1");
        });
    }
}
