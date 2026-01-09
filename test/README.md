# Fitness Club App - Test Suite

This directory contains comprehensive unit tests for the Fitness Club application.

## Test Structure

The test suite is organized to mirror the source code structure:

### Data Model Tests
- **`data/training/TrainingTest.java`** - Tests for Training record validation
- **`data/training/TrainingTypeTest.java`** - Tests for TrainingType enum
- **`data/equipment/EquipmentTest.java`** - Tests for Equipment record validation
- **`data/equipment/EquipmentTypeTest.java`** - Tests for EquipmentType enum
- **`data/exercise/ExerciseTest.java`** - Tests for Exercise record validation
- **`data/muscleGroup/MuscleGroupTest.java`** - Tests for MuscleGroup record validation
- **`data/difficulty/DifficultyTest.java`** - Tests for Difficulty enum

### Command Pattern Tests
- **`command/CommandTest.java`** - Tests for Command record
- **`command/CommandCreatorTest.java`** - Tests for command parsing and creation
- **`command/CommandExecutorTest.java`** - Tests for command execution and routing
- **`command/CommandTypeTest.java`** - Tests for CommandType enum

### Business Logic Tests
- **`fitness_club/FitnessClubTest.java`** - Tests for FitnessClubAPI implementation

### Infrastructure Tests
- **`logger/ErrorLoggerTest.java`** - Tests for error logging functionality
- **`storage/DatabaseConnectionTest.java`** - Tests for database connectivity

## Running the Tests

### Prerequisites

You need to add JUnit 5 and Mockito to your project dependencies:

1. Download the following JAR files and place them in the `lib/` directory:
   - `junit-jupiter-api-5.10.1.jar`
   - `junit-jupiter-engine-5.10.1.jar`
   - `junit-platform-launcher-1.10.1.jar`
   - `mockito-core-5.8.0.jar` (already present)
   - `mockito-junit-jupiter-5.8.0.jar`

2. Or add them to your IDE's test classpath.

### Running Tests from Command Line

```bash
# Compile tests
javac -cp "lib/*;src;test" test/bg/fitness_club/systems/software/integration/design/**/*.java

# Run all tests
java -cp "lib/*;src;test" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

### Running Tests from IDE

Most IDEs (IntelliJ IDEA, Eclipse, VS Code) have built-in JUnit support:
- Right-click on the `test` directory
- Select "Run All Tests" or "Run Tests in..."

## Test Coverage

The test suite covers:
- ✅ Data model validation (null checks, empty values, business rules)
- ✅ Enum type conversions and case-insensitivity
- ✅ Command parsing and creation
- ✅ Command execution routing
- ✅ Business logic (using mocks for database)
- ✅ Error logging functionality
- ✅ Database connection handling

## Notes

- Tests use **Mockito** for mocking database interactions
- Database tests require a running MySQL instance with the `fitness_club` database
- Error logger tests create temporary log files that are cleaned up after execution
- All tests follow JUnit 5 conventions with `@Test`, `@BeforeEach`, and `@AfterEach` annotations
