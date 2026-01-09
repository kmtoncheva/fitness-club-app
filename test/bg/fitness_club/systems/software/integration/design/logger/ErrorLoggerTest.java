package bg.fitness_club.systems.software.integration.design.logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ErrorLoggerTest {

    private static final String TEST_LOG_FILE = "test_logs.txt";
    private ErrorLogger errorLogger;
    private Path logPath;

    @BeforeEach
    void setUp() throws IOException {
        logPath = Path.of(TEST_LOG_FILE);
        if (Files.exists(logPath)) {
            Files.delete(logPath);
        }
        Files.createFile(logPath);
        errorLogger = new ErrorLogger(TEST_LOG_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(logPath)) {
            Files.delete(logPath);
        }
    }

    @Test
    void testAppendLogsCreatesLogEntry() throws IOException {
        Exception testException = new RuntimeException("Test error");
        
        errorLogger.appendLogs(testException);
        
        String content = Files.readString(logPath);
        assertTrue(content.contains("Timestamp:"));
        assertTrue(content.contains("Caused by:"));
        assertTrue(content.contains("Exception message:"));
        assertTrue(content.contains("Test error"));
    }

    @Test
    void testAppendLogsWithMultipleExceptions() throws IOException {
        Exception exception1 = new RuntimeException("First error");
        Exception exception2 = new IllegalArgumentException("Second error");
        
        errorLogger.appendLogs(exception1);
        errorLogger.appendLogs(exception2);
        
        String content = Files.readString(logPath);
        assertTrue(content.contains("First error"));
        assertTrue(content.contains("Second error"));
    }

    @Test
    void testConstructorWithNullPathThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            new ErrorLogger(null)
        );
    }

    @Test
    void testConstructorWithEmptyPathThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            new ErrorLogger("")
        );
    }

    @Test
    void testAppendLogsWithNullExceptionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            errorLogger.appendLogs(null)
        );
    }
}
