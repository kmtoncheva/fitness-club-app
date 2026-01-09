package bg.fitness_club.systems.software.integration.design.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import bg.fitness_club.systems.software.integration.design.data.difficulty.Difficulty;
import bg.fitness_club.systems.software.integration.design.data.equipment.Equipment;
import bg.fitness_club.systems.software.integration.design.data.exercise.Exercise;
import bg.fitness_club.systems.software.integration.design.data.muscleGroup.MuscleGroup;
import bg.fitness_club.systems.software.integration.design.data.training.Training;
import bg.fitness_club.systems.software.integration.design.data.training.TrainingType;

public class Queries {
    private final DatabaseConnection databaseConnection;
    private static final String TYPE = "type";
    private static final String DIFFICULTY = "difficulty";
    private static final String DURATION = "duration";

    public Queries(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private StringBuilder makeCollectionStringForQuery(Collection<String> elements) {
        StringBuilder elementsForQuery = new StringBuilder();

        int counter = 0;
        for (String element : elements) {
            elementsForQuery.append("\"").append(element).append("\"");
            if (counter != elements.size() - 1) {
                elementsForQuery.append(",");
            }
            counter++;
        }

        return elementsForQuery;
    }

    private Set<Training> getTrainingsForQueries(ResultSet resultSet) throws SQLException {
        Set<String> trainingNames = new HashSet<>();
        while (resultSet.next()) {
            trainingNames.add(resultSet.getString("name"));
        }
        resultSet.close();

        Set<Training> trainings = new HashSet<>();
        for (String trainingName : trainingNames) {
            resultSet = databaseConnection.executeQuery("""
                    SELECT * FROM trainings
                    WHERE name = \"""" +
                    trainingName + "\"");

            resultSet.next();

            TrainingType type = TrainingType.getTrainingType(resultSet.getString(TYPE));
            Difficulty difficulty = Difficulty.getType(resultSet.getString(DIFFICULTY));
            int duration = resultSet.getInt(DURATION);

            trainings.add(new Training(trainingName,
                    type.getTrainingTypeString(),
                    difficulty.getTypeString(),
                    getExercisesByTrainingName(trainingName),
                    duration,
                    getMuscleGroupsByTrainingName(trainingName),
                    getAllEquipmentByTrainingName(trainingName)));
        }

        resultSet.close();
        return trainings;
    }

    public Set<Training> getTrainingsByName(String trainingName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""
                SELECT * FROM trainings
                WHERE name = \"""" +
                trainingName + "\"");

        return getTrainingsForQueries(resultSet);
    }

    public Set<Training> getAllTrainings() throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery(
                "SELECT * FROM trainings");

        return getTrainingsForQueries(resultSet);
    }

    public Set<Training> getAllTrainingsByMusleGroups(Collection<String> musleGroups) throws SQLException {
        String query = """
                SELECT * FROM trainings
                WHERE id IN (
                        SELECT training_id FROM muscles_for_training
                        WHERE muscle_id IN (
                                SELECT id FROM muscles
                                WHERE name IN (
                                                """ + makeCollectionStringForQuery(musleGroups) +
                ")))";

        ResultSet resultSet = databaseConnection.executeQuery(query);

        return getTrainingsForQueries(resultSet);
    }

    public Set<Training> getAllTrainingsByExercise(Collection<String> exercises) throws SQLException {
        String query = """
                SELECT * FROM trainings
                WHERE id IN (
                        SELECT training_id FROM exercises_for_training
                        WHERE exercise_id IN (
                                SELECT id FROM exercises
                                WHERE name IN (
                                                """ + makeCollectionStringForQuery(exercises) +
                ")))";

        ResultSet resultSet = databaseConnection.executeQuery(query);

        return getTrainingsForQueries(resultSet);
    }

    public Set<Training> getAllTrainingsByType(Collection<String> types) throws SQLException {
        String query = """
                SELECT * FROM trainings
                WHERE type IN (
                                """ + makeCollectionStringForQuery(types) +
                ")";

        ResultSet resultSet = databaseConnection.executeQuery(query);

        return getTrainingsForQueries(resultSet);
    }

    public Difficulty getDifficultyByTrainingName(String trainingName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""
                SELECT difficulty FROM trainings
                WHERE name = \"""" +
                trainingName + "\"");

        Difficulty difficulty = null;
        while (resultSet.next()) {
            difficulty = Difficulty.getType(resultSet.getString(DIFFICULTY));
        }

        resultSet.close();
        return difficulty;
    }

    public int getDurationForTrainingByName(String trainingName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""
                SELECT duration FROM trainings
                WHERE name = \"""" +
                trainingName + "\"");

        int duration = 0;
        while (resultSet.next()) {
            duration = resultSet.getInt(DURATION);
        }

        resultSet.close();
        return duration;
    }

    public Set<Equipment> getAllEquipment() throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("select * from equipment");

        Set<Equipment> equipment = new HashSet<>();
        while (resultSet.next()) {
            equipment.add(Equipment.of(resultSet));
        }

        resultSet.close();
        return equipment;
    }

    public Set<Equipment> getAllEquipmentByTrainingName(String trainingName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM equipment
                WHERE id IN (
                        SELECT equipment_id FROM equipment_for_training
                        WHERE training_id = (
                            SELECT id FROM trainings
                            WHERE name = \"""" +
                trainingName + "\"))");

        Set<Equipment> equipment = new HashSet<>();
        while (resultSet.next()) {
            equipment.add(Equipment.of(resultSet));
        }

        resultSet.close();
        return equipment;
    }

    public Set<MuscleGroup> getMuscleGroupsByTrainingName(String trainingName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM muscles
                WHERE id IN (
                        SELECT muscle_id FROM muscles_for_training
                        WHERE training_id IN (
                                SELECT id FROM trainings
                                WHERE name = \"""" +
                trainingName + "\"))");

        Set<MuscleGroup> muscles = new HashSet<>();
        while (resultSet.next()) {
            muscles.add(new MuscleGroup(resultSet.getString("name")));
        }

        resultSet.close();
        return muscles;
    }

    public Set<Exercise> getExercisesByTrainingName(String trainingName) throws SQLException {
        ResultSet resultSet = databaseConnection.executeQuery("""             
                SELECT * FROM exercises
                WHERE id IN (
                        SELECT exercise_id FROM exercises_for_training
                        WHERE training_id IN (
                                SELECT id FROM trainings
                                WHERE name = \"""" +
                trainingName + "\"))");

        Set<Exercise> exercises = new HashSet<>();
        while (resultSet.next()) {
            exercises.add(new Exercise(resultSet.getString("name")));
        }

        resultSet.close();
        return exercises;
    }
}
