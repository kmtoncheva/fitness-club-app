package bg.fitness_club.systems.software.integration.design.fitness_club;

import java.sql.SQLException;
import java.util.Collection;

public interface FitnessClubAPI {
    String getMusleGroupsByTrainingName(String trainingName) throws SQLException;

    String getTrainingByName(String trainingName) throws SQLException;

    String getAllTrainings() throws SQLException;

    String getAllTrainingsByMuscleGroups(Collection<String> muscleGroups) throws SQLException;

    String getAllTrainingsByExercises(Collection<String> exercises) throws SQLException;

    String getAllTrainingsByType(Collection<String> types) throws SQLException;

    String getDifficultyByTrainingName(String trainingName) throws SQLException;

    String getDurationForTrainingByName(String trainingName) throws SQLException;

    String getAllEquipment() throws SQLException;

    String getAllEquipmentByTrainingName(String trainingName) throws SQLException;

    String getExercisesByTrainingName(String trainingName) throws SQLException;
}