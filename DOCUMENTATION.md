# Fitness Club Management System - Technical Documentation

## Project Overview

**Project Name:** Fitness Club Management System  
**Course:** Software Systems Design and Integration (Проектиране и интегриране на софтуерни системи)  
**Technology Stack:** Java, MySQL, JDBC, Gson, Java NIO  
**Architecture:** Client-Server Application

### Purpose
This system provides a comprehensive fitness club management solution that allows users to query and retrieve information about training programs, exercises, equipment, muscle groups, and difficulty levels. The application follows a client-server architecture with a MySQL database backend.

---

## Table of Contents
1. [System Architecture](#system-architecture)
2. [Database Design](#database-design)
3. [Core Components](#core-components)
4. [API Reference](#api-reference)
5. [Command System](#command-system)
6. [Data Models](#data-models)
7. [Installation & Setup](#installation--setup)
8. [Usage Examples](#usage-examples)
9. [Testing](#testing)
10. [Error Handling](#error-handling)

---

## System Architecture

### Overview
The system implements a **three-tier architecture**:

```
┌─────────────────┐
│     Client      │  ← User Interface Layer
│   (Java NIO)    │  (Enhanced with colors & emojis)
└────────┬────────┘
         │ TCP/IP (Port 8080)
         │
┌────────▼────────┐
│     Server      │  ← Business Logic Layer
│ (ServerLauncher)│  (Colorful admin console)
└────────┬────────┘
         │ JDBC
         │
┌────────▼────────┐
│  MySQL Database │  ← Data Layer
│ (fitness_club)  │
└─────────────────┘
```

### Key Technologies

- **Java NIO (Non-blocking I/O):** Enables efficient handling of multiple client connections
- **JDBC (Java Database Connectivity):** Provides database access and query execution
- **Gson:** JSON serialization/deserialization for data transfer
- **Command Pattern:** Structured command processing and execution
- **MySQL:** Relational database for persistent storage
- **ANSI Color Codes:** Terminal styling for enhanced user experience
- **Unicode Characters:** Box-drawing characters and emojis for modern UI

---

## Database Design

### Entity-Relationship Diagram

The database consists of 7 main tables with the following relationships:

```
trainings (1) ──< (N) exercises_for_training (N) >── (1) exercises
    │
    ├──< (N) muscles_for_training (N) >── (1) muscles
    │
    └──< (N) equipment_for_training (N) >── (1) equipment
```

### Database Schema

#### Table: `trainings`
Primary table storing training program information.

| Column     | Type            | Constraints    | Description                          |
|------------|-----------------|----------------|--------------------------------------|
| id         | BIGINT UNSIGNED | PRIMARY KEY    | Unique training identifier           |
| name       | VARCHAR(50)     | UNIQUE, NOT NULL | Training program name              |
| type       | VARCHAR(50)     | NOT NULL       | Training type (strength, cardio, etc.) |
| difficulty | VARCHAR(50)     | NOT NULL       | Difficulty level (easy, medium, hard) |
| duration   | BIGINT          | NOT NULL       | Duration in minutes                  |

**Sample Data:**
- Full Body Beginner (full_body, easy, 45 min)
- Cardio Burn (cardio, medium, 30 min)
- Upper Body Strength (strength, hard, 60 min)
- Morning Yoga Flow (yoga, easy, 40 min)

#### Table: `exercises`
Stores individual exercise definitions.

| Column | Type            | Constraints    | Description              |
|--------|-----------------|----------------|--------------------------|
| id     | BIGINT UNSIGNED | PRIMARY KEY    | Unique exercise identifier |
| name   | VARCHAR(50)     | UNIQUE, NOT NULL | Exercise name          |

**Sample Data:** Push-ups, Squats, Plank, Burpees, Pull-ups, Bench Press, Sun Salutation, Deadlift

#### Table: `muscles`
Defines muscle groups targeted by trainings.

| Column | Type            | Constraints    | Description                |
|--------|-----------------|----------------|----------------------------|
| id     | BIGINT UNSIGNED | PRIMARY KEY    | Unique muscle group identifier |
| name   | VARCHAR(50)     | UNIQUE, NOT NULL | Muscle group name        |

**Sample Data:** Chest, Back, Legs, Shoulders, Arms, Core

#### Table: `equipment`
Stores equipment information with difficulty ratings.

| Column     | Type            | Constraints    | Description                    |
|------------|-----------------|----------------|--------------------------------|
| id         | BIGINT UNSIGNED | PRIMARY KEY    | Unique equipment identifier    |
| name       | VARCHAR(50)     | UNIQUE, NOT NULL | Equipment name               |
| type       | VARCHAR(50)     | NOT NULL       | Equipment type                 |
| difficulty | VARCHAR(50)     | NOT NULL       | Usage difficulty level         |

**Sample Data:** Dumbbells, Barbell, Kettlebell, Yoga Mat, Pull-up Bar, No Equipment

#### Junction Tables

**`exercises_for_training`** - Links exercises to training programs
- `id` (PRIMARY KEY)
- `exercise_id` (FOREIGN KEY → exercises.id)
- `training_id` (FOREIGN KEY → trainings.id)

**`muscles_for_training`** - Links muscle groups to training programs
- `id` (PRIMARY KEY)
- `muscle_id` (FOREIGN KEY → muscles.id)
- `training_id` (FOREIGN KEY → trainings.id)

**`equipment_for_training`** - Links equipment to training programs
- `id` (PRIMARY KEY)
- `equipment_id` (FOREIGN KEY → equipment.id)
- `training_id` (FOREIGN KEY → trainings.id)

---

## Core Components

### 1. Client (`Client.java`)

**Location:** `src/bg/fitness_club/systems/software/integration/design/client/Client.java`

**Responsibilities:**
- Establishes connection to the server (localhost:8080)
- Sends user commands to the server
- Receives and displays colorful, emoji-rich responses
- Handles file output for command results
- Provides interactive user experience with visual feedback

**Key Methods:**
- `main(String[] args)` - Entry point, manages connection lifecycle
- `printFitnessInfo()` - Displays beautiful menu with colors and emojis
- `getStrings(String input, List<String> tokens)` - Parses command input
- `createFile(String reply, List<String> messageParts)` - Exports data to files

**Connection Details:**
```java
SERVER_HOST = "localhost"
SERVER_PORT = 8080
```

**UI Features:**
- 💪 Colorful welcome banner with Unicode box borders
- 🎨 Color-coded sections (Green, Blue, Yellow, Magenta, Cyan)
- 📨 Formatted response boxes with separators
- 👋 Friendly goodbye messages on disconnect
- ✅ Success/warning/error indicators with emojis

### 2. Server (`Server.java`)

**Location:** `src/bg/fitness_club/systems/software/integration/design/server/Server.java`

**Responsibilities:**
- Listens for client connections using Java NIO
- Processes incoming commands
- Executes commands via CommandExecutor
- Returns results to clients

**Architecture:**
- Uses `Selector` for non-blocking I/O operations
- Handles multiple concurrent client connections
- Implements thread-safe command execution

**Key Methods:**
- `run()` - Main server loop
- `accept(SelectionKey key)` - Accepts new client connections
- `read(SelectionKey key)` - Reads client commands
- `write(SelectionKey key)` - Sends responses to clients

### 3. Server Launcher (`ServerLauncher.java`)

**Location:** `src/bg/fitness_club/systems/software/integration/design/server/ServerLauncher.java`

**Responsibilities:**
- Initializes server components
- Manages server lifecycle (start/stop) with colorful UI
- Configures database connection
- Sets up error logging
- Provides admin console with emoji-rich interface

**Initialization Sequence:**
```java
1. Log file creation with success indicator (✅)
2. ErrorLogger initialization
3. DatabaseConnection setup
4. Queries object creation
5. Gson configuration with pretty printing
6. FitnessClub API instantiation
7. Server thread startup
8. Colorful admin console display (🖥️)
```

**Admin Console Features:**
- 🖥️ Professional header with Unicode borders
- 🟢 Color-coded commands (green for start, red for stop)
- 💡 Helpful tips about port configuration
- 🚀 Animated status messages
- ✅ Graceful shutdown messages

### 4. FitnessClub API (`FitnessClub.java`)

**Location:** `src/bg/fitness_club/systems/software/integration/design/fitness_club/FitnessClub.java`

**Responsibilities:**
- Implements business logic
- Coordinates database queries
- Serializes results to JSON
- Handles "No result!" cases

**Key Methods:**
```java
String getAllTrainings() throws SQLException
String getTrainingByName(String trainingName) throws SQLException
String getAllTrainingsByType(Collection<String> types) throws SQLException
String getAllTrainingsByExercises(Collection<String> exercises) throws SQLException
String getAllTrainingsByMuscleGroups(Collection<String> muscleGroups) throws SQLException
String getDifficultyByTrainingName(String trainingName) throws SQLException
String getDurationForTrainingByName(String trainingName) throws SQLException
String getAllEquipment() throws SQLException
String getAllEquipmentByTrainingName(String trainingName) throws SQLException
String getExercisesByTrainingName(String trainingName) throws SQLException
String getMusleGroupsByTrainingName(String trainingName) throws SQLException
```

### 5. Database Layer (`Queries.java`)

**Location:** `src/bg/fitness_club/systems/software/integration/design/database/Queries.java`

**Responsibilities:**
- Executes SQL queries
- Constructs complex JOIN operations
- Maps ResultSets to domain objects
- Handles parameterized queries

**Query Examples:**
```sql
-- Get all trainings with related data
SELECT DISTINCT t.* FROM trainings t
LEFT JOIN exercises_for_training eft ON t.id = eft.training_id
LEFT JOIN exercises e ON eft.exercise_id = e.id
LEFT JOIN muscles_for_training mft ON t.id = mft.training_id
LEFT JOIN muscles m ON mft.muscle_id = m.id
LEFT JOIN equipment_for_training eqt ON t.id = eqt.training_id
LEFT JOIN equipment eq ON eqt.equipment_id = eq.id
```

### 6. Command System

**CommandCreator** - Parses raw input into Command objects  
**CommandExecutor** - Routes commands to appropriate handlers  
**Command Handlers** - Execute specific command logic

---

## API Reference

### Command Format

All commands follow this structure:
```
get <entity> [by <attribute> "<value>"]
```

### Available Commands

#### 1. Get All Trainings
```
get trainings
```
**Returns:** JSON array of all training programs with complete details

#### 2. Get Training by Name
```
get training by training_name "Full Body Beginner"
```
**Returns:** JSON object with training details

#### 3. Get Trainings by Type
```
get trainings by type "cardio" "strength"
```
**Returns:** JSON array of trainings matching specified types

#### 4. Get Trainings by Exercises
```
get trainings by exercises "Push-ups" "Squats"
```
**Returns:** JSON array of trainings containing specified exercises

#### 5. Get Trainings by Muscle Groups
```
get trainings by musle_groups "Chest" "Legs"
```
**Returns:** JSON array of trainings targeting specified muscle groups

#### 6. Get Difficulty by Training Name
```
get difficulty by training_name "Cardio Burn"
```
**Returns:** JSON object with difficulty level

#### 7. Get Duration by Training Name
```
get duration by training_name "Morning Yoga Flow"
```
**Returns:** JSON object with duration in minutes

#### 8. Get All Equipment
```
get equipment
```
**Returns:** JSON array of all equipment

#### 9. Get Equipment by Training Name
```
get equipment by training_name "Upper Body Strength"
```
**Returns:** JSON array of equipment needed for specified training

#### 10. Get Exercises by Training Name
```
get exercises by training_name "Full Body Beginner"
```
**Returns:** JSON array of exercises in specified training

#### 11. Get Muscle Groups by Training Name
```
get musle_groups by training_name "Upper Body Strength"
```
**Returns:** JSON array of muscle groups targeted by training

---

## Command System

### Command Pattern Implementation

The system uses the **Command Pattern** for flexible command processing:

```
User Input → CommandCreator → Command → CommandExecutor → Handler → FitnessClubAPI → Database
```

### CommandType Enum

Defines all supported command types:

```java
GET("get")
TRAININGS("trainings")
TRAINING("training")
EQUIPMENT("equipment")
EXERCISES("exercises")
MUSLE_GROUPS("musle_groups")
DURATION("duration")
DIFFICULTY("difficulty")
TYPE("type")
TRAINING_NAME("training_name")
BY("by")
```

### Command Validation

**CommandsValidator** ensures:
- Correct argument count
- Valid command types
- Proper command structure

**CommandExecutorValidator** validates:
- Non-null FitnessClubAPI instance
- Non-null Command objects
- Argument length requirements

---

## Data Models

All data models are implemented as **Java Records** for immutability and conciseness.

### Training Record

```java
public record Training(
    String name,
    String type,
    String difficulty,
    Set<Exercise> exercises,
    int duration,
    Set<MuscleGroup> muscleGroups,
    Set<Equipment> equipment
)
```

**Validation Rules:**
- `name` cannot be null, empty, or blank
- `type` cannot be null
- `difficulty` cannot be null
- `exercises` cannot be null or empty
- `duration` must be > 0
- `muscleGroups` cannot be null
- `equipment` cannot be null

### TrainingType Enum

```java
STRENGTH("strength")
FULL_BODY("full_body")
CARDIO("cardio")
PILATES("pilates")
YOGA("yoga")
```

### Equipment Record

```java
public record Equipment(
    String name,
    String type,
    String difficulty
)
```

**Validation Rules:**
- `name` cannot be null, empty, or blank
- `type` cannot be null
- `difficulty` cannot be null

### EquipmentType Enum

```java
FREE_WEIGHTS("free_weights")
MACHINE("machine")
BODYWEIGHT("bodyweight")
MAT("mat")
NONE("none")
```

### Exercise Record

```java
public record Exercise(String name)
```

**Validation:** `name` cannot be null, empty, or blank

### MuscleGroup Record

```java
public record MuscleGroup(String name)
```

**Validation:** `name` cannot be null, empty, or blank

### Difficulty Enum

```java
EASY("easy")
MEDIUM("medium")
HARD("hard")
```

---

## Installation & Setup

### Prerequisites

- **Java Development Kit (JDK):** Version 17 or higher
- **MySQL Server:** Version 5.7 or higher
- **Terminal with ANSI support:** For color display (Windows Terminal, PowerShell, or modern terminals)
- **IDE:** IntelliJ IDEA, Eclipse, or VS Code (optional)

### Database Setup

1. **Start MySQL Server**
   ```bash
   # Windows
   net start MySQL
   
   # Linux/Mac
   sudo systemctl start mysql
   ```

2. **Create Database**
   ```bash
   mysql -u root -p
   ```
   ```sql
   CREATE DATABASE fitness_club;
   ```

3. **Import Schema**
   ```bash
   mysql -u root -p fitness_club < fitness_club.sql
   ```

4. **Verify Installation**
   ```sql
   USE fitness_club;
   SHOW TABLES;
   SELECT * FROM trainings;
   ```

### Application Setup

1. **Clone/Download Project**
   ```bash
   cd fitness-club-app
   ```

2. **Configure Database Connection**
   
   Edit `DatabaseConnection.java` if needed (default settings):
   ```java
   URL: "jdbc:mysql://localhost:3306/fitness_club"
   USER: "root"
   PASSWORD: ""
   ```

3. **Add Required Libraries**
   
   Ensure the following JARs are in the `lib/` directory:
   - `gson-2.10.1.jar`
   - `mysql-connector-j-8.2.0.jar`
   - `mockito-core-4.9.0.jar` (for testing)

4. **Compile Project**
   ```bash
   javac -cp "lib/*" -d bin src/bg/fitness_club/systems/software/integration/design/**/*.java
   ```

### Running the Application

#### Start Server (ServerLauncher)

```bash
java -cp "bin;lib/*" bg.fitness_club.systems.software.integration.design.server.ServerLauncher
```

**Admin Commands:**
- 🟢 `start` - Start the Fitness Club server
- 🔴 `stop` - Stop the server and exit

**Expected Output:**
```
╔════════════════════════════════════════════════════════════════════════════╗
║                   🖥️  FITNESS CLUB SERVER  🖥️                             ║
║                    Server Management Console                               ║
╚════════════════════════════════════════════════════════════════════════════╝

📋 Available Commands:
  🟢 start - Start the Fitness Club server
  🔴 stop  - Stop the server and exit

💡 Tip: Server will listen on port 8080 after starting
```

#### Start Client

```bash
java -cp "bin;lib/*" bg.fitness_club.systems.software.integration.design.client.Client
```

**Expected Output:**
```
✅ Connected to Fitness Club Server!

╔════════════════════════════════════════════════════════════════════════════╗
║                    💪 WELCOME TO FITNESS CLUB 2.0 💪                       ║
║                     Your Personal Training Assistant                       ║
╚════════════════════════════════════════════════════════════════════════════╝

📝 NOTE: Use quotes (" ") around values for proper command execution!

[Colorful menu sections with emojis...]
```

---

## Usage Examples

### Example 1: Get All Trainings

**Input:**
```
get trainings
```

**Output:**
```json
[
  {
    "name": "Full Body Beginner",
    "type": "full_body",
    "difficulty": "easy",
    "exercises": [
      {"name": "Push-ups"},
      {"name": "Squats"},
      {"name": "Plank"}
    ],
    "duration": 45,
    "muscleGroups": [
      {"name": "Chest"},
      {"name": "Legs"},
      {"name": "Core"}
    ],
    "equipment": [
      {"name": "Dumbbells", "type": "free_weights", "difficulty": "medium"}
    ]
  }
]
```

### Example 2: Get Training by Name

**Input:**
```
get training by training_name "Cardio Burn"
```

**Output:**
```json
{
  "name": "Cardio Burn",
  "type": "cardio",
  "difficulty": "medium",
  "exercises": [{"name": "Burpees"}],
  "duration": 30,
  "muscleGroups": [{"name": "Legs"}, {"name": "Core"}],
  "equipment": [{"name": "No Equipment", "type": "none", "difficulty": "easy"}]
}
```

### Example 3: Get Trainings by Type

**Input:**
```
get trainings by type "strength" "yoga"
```

**Output:**
```json
[
  {
    "name": "Upper Body Strength",
    "type": "strength",
    "difficulty": "hard",
    "duration": 60,
    ...
  },
  {
    "name": "Morning Yoga Flow",
    "type": "yoga",
    "difficulty": "easy",
    "duration": 40,
    ...
  }
]
```

### Example 4: Get Duration

**Input:**
```
get duration by training_name "Full Body Beginner"
```

**Output:**
```json
{
  "duration": "45 minutes"
}
```

### Example 5: File Output

**Input:**
```
get trainings to file "C:\output\trainings.txt"
```

**Result:** Creates file with JSON content at specified path

---

## Testing

### Test Suite Overview

The project includes **98 unit tests** covering all major components.

**Test Location:** `test/bg/fitness_club/systems/software/integration/design/`

### Test Categories

#### 1. Data Model Tests (39 tests)
- `TrainingTest.java` - Training validation
- `TrainingTypeTest.java` - Enum parsing
- `EquipmentTest.java` - Equipment validation
- `EquipmentTypeTest.java` - Enum parsing
- `ExerciseTest.java` - Exercise validation
- `MuscleGroupTest.java` - MuscleGroup validation
- `DifficultyTest.java` - Difficulty enum

#### 2. Command Tests (33 tests)
- `CommandTest.java` - Command object creation
- `CommandCreatorTest.java` - Command parsing
- `CommandExecutorTest.java` - Command execution
- `CommandTypeTest.java` - CommandType enum

#### 3. Business Logic Tests (13 tests)
- `FitnessClubTest.java` - API methods with mocked database

#### 4. Infrastructure Tests (8 tests)
- `ErrorLoggerTest.java` - Error logging
- `DatabaseConnectionTest.java` - Database connectivity

### Running Tests

#### Prerequisites
Add JUnit 5 dependencies to `lib/`:
- `junit-jupiter-api-5.10.1.jar`
- `junit-jupiter-engine-5.10.1.jar`
- `junit-platform-launcher-1.10.1.jar`
- `mockito-junit-jupiter-5.8.0.jar`

#### Run All Tests
```bash
java -cp "lib/*;bin;test" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

#### Run Specific Test Class
```bash
java -cp "lib/*;bin;test" org.junit.platform.console.ConsoleLauncher --select-class bg.fitness_club.systems.software.integration.design.model.training.TrainingTest
```

### Test Coverage

- ✅ **Data Validation:** Null checks, empty values, business rules
- ✅ **Enum Conversions:** Case-insensitivity, unknown values
- ✅ **Command Processing:** Parsing, routing, execution
- ✅ **Business Logic:** Mocked database interactions
- ✅ **Error Handling:** Exception scenarios, logging

---

## Error Handling

### ErrorLogger

**Location:** `src/bg/fitness_club/systems/software/integration/design/logger/ErrorLogger.java`

**Functionality:**
- Logs exceptions to file with timestamps
- Includes stack traces for debugging
- Validates log file path and exceptions

**Log Format:**
```
Timestamp: 2026-01-09 21:45:32
Caused by: java.sql.SQLException
Exception message: Connection refused
Stack trace:
  at DatabaseConnection.connect(DatabaseConnection.java:25)
  at Queries.getAllTrainings(Queries.java:45)
  ...
----------------------------------------
```

**Log File Location:** `src/bg/fitness_club/systems/software/integration/design/logger/logs.txt`

### Exception Handling Strategy

1. **Database Exceptions:** Caught in FitnessClub, logged, return error message
2. **Validation Exceptions:** Thrown immediately with descriptive messages
3. **Command Exceptions:** Caught in CommandExecutor, logged, return user-friendly message
4. **I/O Exceptions:** Handled in Client/Server with appropriate cleanup

### Common Error Messages

- `"Unknown Command"` - Invalid command type
- `"No result!"` - Query returned empty set
- `"There was an error in the application, please try again!"` - General error
- `"The [field] cannot be null!"` - Validation error
- 🔌 `"Network communication error!"` - Connection issues
- ❌ `"Cannot create file - incorrect command format!"` - File export error

---

## Project Structure

```
fitness-club-app/
├── src/
│   └── bg/fitness_club/systems/software/integration/design/
│       ├── client/
│       │   ├── Client.java
│       │   └── ClientConstants.java
│       ├── server/
│       │   ├── Server.java
│       │   ├── ServerLauncher.java
│       │   └── ServerConstants.java
│       ├── fitness_club/
│       │   ├── FitnessClubAPI.java
│       │   └── FitnessClub.java
│       ├── commands/
│       │   ├── Command.java
│       │   ├── CommandCreator.java
│       │   ├── CommandExecutor.java
│       │   ├── CommandType.java
│       │   ├── handlers/
│       │   │   ├── training/
│       │   │   ├── equipment/
│       │   │   ├── exercise/
│       │   │   ├── muscleGroup/
│       │   │   ├── difficulty/
│       │   │   └── duration/
│       │   └── validators/
│       ├── model/
│       │   ├── training/
│       │   ├── equipment/
│       │   ├── exercise/
│       │   ├── muscleGroup/
│       │   └── difficulty/
│       ├── database/
│       │   ├── DatabaseConnection.java
│       │   └── Queries.java
│       └── logger/
│           └── ErrorLogger.java
├── test/
│   └── bg/fitness_club/systems/software/integration/design/
│       ├── data/
│       ├── command/
│       ├── fitness_club/
│       ├── storage/
│       └── logger/
├── lib/
│   ├── gson-2.10.1.jar
│   ├── mysql-connector-j-8.2.0.jar
│   └── mockito-core-4.9.0.jar
├── fitness_club.sql
├── README.md
├── DOCUMENTATION.md
├── COMMAND_EXAMPLES.md
└── FILE_EXPORT_FEATURE.md
```

---

## Design Patterns Used

### 1. Command Pattern
Encapsulates requests as objects, allowing parameterization and queuing.

**Implementation:**
- `Command` - Request object
- `CommandCreator` - Factory for commands
- `CommandExecutor` - Invoker
- Handler classes - Concrete commands

### 2. Factory Pattern
`CommandCreator` creates appropriate Command objects based on input.

### 3. Strategy Pattern
Different command handlers implement different execution strategies.

### 4. Singleton Pattern
`DatabaseConnection` ensures single database connection instance.

### 5. Repository Pattern
`Queries` class abstracts database access logic.

---

## Performance Considerations

### Database Optimization
- **Indexed Columns:** All foreign keys and unique constraints indexed
- **Connection Pooling:** Single connection reused across queries
- **Prepared Statements:** Prevents SQL injection, improves performance

### Network Optimization
- **Non-blocking I/O:** Java NIO enables efficient multi-client handling
- **JSON Serialization:** Compact data format reduces bandwidth

### Memory Management
- **Records:** Immutable, efficient memory usage
- **Sets:** Prevent duplicate data
- **Connection Cleanup:** Proper resource disposal

---

## Security Considerations

### Database Security
- ⚠️ **Current:** Empty root password (development only)
- ✅ **Production:** Use strong passwords, create dedicated user
- ✅ **SQL Injection:** Parameterized queries prevent injection

### Network Security
- ⚠️ **Current:** No encryption (localhost only)
- ✅ **Production:** Implement SSL/TLS for client-server communication
- ✅ **Port Configuration:** Changed from default 7777 to 8080

### Input Validation
- ✅ All user inputs validated before processing
- ✅ Command structure enforced
- ✅ Data model validation in constructors

---

## Future Enhancements

### Planned Features
1. **User Authentication:** Login system with roles
2. **Training Scheduling:** Book training sessions
3. **Progress Tracking:** User workout history
4. **Nutrition Integration:** Meal plans and calorie tracking
5. **Web Interface:** REST API and web frontend
6. **Mobile App:** iOS/Android clients
7. **Enhanced Filtering:** More complex query combinations
8. **Export Formats:** CSV, XML, PDF export options

### Technical Improvements
1. **Connection Pooling:** HikariCP for better performance
2. **Caching:** Redis for frequently accessed data
3. **Logging Framework:** Log4j2 for structured logging
4. **Configuration Management:** External config files
5. **Containerization:** Docker deployment
6. **UI Themes:** Customizable color schemes
7. **Internationalization:** Multi-language support

---

## Troubleshooting

### Common Issues

#### 1. "Connection refused" Error
**Cause:** MySQL server not running  
**Solution:**
```bash
# Windows
net start MySQL

# Linux/Mac
sudo systemctl start mysql
```

#### 2. "Unknown database 'fitness_club'"
**Cause:** Database not created  
**Solution:**
```bash
mysql -u root -p < fitness_club.sql
```

#### 3. "Port 8080 already in use"
**Cause:** Server already running or port occupied  
**Solution:**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

#### 4. ClassNotFoundException
**Cause:** Missing JAR files  
**Solution:** Verify all JARs in `lib/` directory and classpath

#### 5. "No result!" for valid queries
**Cause:** Empty database or incorrect query  
**Solution:** Verify data exists in database

---

## Contributing

### Code Style Guidelines
- **Naming:** camelCase for variables, PascalCase for classes
- **Indentation:** 4 spaces
- **Line Length:** Maximum 120 characters
- **Comments:** Javadoc for public methods

### Testing Requirements
- All new features must include unit tests
- Minimum 80% code coverage
- Tests must pass before merging

---

## License

This project is developed for educational purposes as part of the Software Systems Design and Integration course.

---

## Contact & Support

For questions or issues related to this project, please refer to the course materials or contact your instructor.

---

## Appendix

### A. Database Connection Configuration

```java
// Default Configuration
String DB_URL = "jdbc:mysql://localhost:3306/fitness_club";
String DB_USER = "root";
String DB_PASSWORD = "";

// Production Configuration (example)
String DB_URL = "jdbc:mysql://production-server:3306/fitness_club";
String DB_USER = "fitness_app_user";
String DB_PASSWORD = System.getenv("DB_PASSWORD");
```

### B. Command Reference Quick Guide

| Command | Syntax | Example |
|---------|--------|---------|
| All Trainings | `get trainings` | `get trainings` |
| Training by Name | `get training by training_name "<name>"` | `get training by training_name "Cardio Burn"` |
| By Type | `get trainings by type "<type1>" "<type2>"` | `get trainings by type "cardio"` |
| By Exercises | `get trainings by exercises "<ex1>" "<ex2>"` | `get trainings by exercises "Push-ups"` |
| By Muscles | `get trainings by musle_groups "<m1>" "<m2>"` | `get trainings by musle_groups "Chest"` |
| Difficulty | `get difficulty by training_name "<name>"` | `get difficulty by training_name "Yoga Flow"` |
| Duration | `get duration by training_name "<name>"` | `get duration by training_name "Full Body"` |
| All Equipment | `get equipment` | `get equipment` |
| Equipment by Training | `get equipment by training_name "<name>"` | `get equipment by training_name "Strength"` |
| Exercises by Training | `get exercises by training_name "<name>"` | `get exercises by training_name "Cardio"` |
| Muscles by Training | `get musle_groups by training_name "<name>"` | `get musle_groups by training_name "Yoga"` |

### C. JSON Response Schemas

**Training Object:**
```json
{
  "name": "string",
  "type": "string",
  "difficulty": "string",
  "exercises": [{"name": "string"}],
  "duration": number,
  "muscleGroups": [{"name": "string"}],
  "equipment": [{"name": "string", "type": "string", "difficulty": "string"}]
}
```

**Equipment Object:**
```json
{
  "name": "string",
  "type": "string",
  "difficulty": "string"
}
```

**Exercise Object:**
```json
{
  "name": "string"
}
```

**MuscleGroup Object:**
```json
{
  "name": "string"
}
```

---

**Document Version:** 1.0  
**Last Updated:** January 2026  
**Authors:** Student Project Team
