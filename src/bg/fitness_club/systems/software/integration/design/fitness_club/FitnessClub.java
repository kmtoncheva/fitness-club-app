package bg.fitness_club.systems.software.integration.design.fitness_club;

import bg.fitness_club.systems.software.integration.design.data.training.Training;
import bg.fitness_club.systems.software.integration.design.storage.Queries;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class FitnessClub extends FitnessClubValidator implements FitnessClubAPI {
    private final Queries queries;
    private final Gson gson;
    private static final String NO_RESULT = "No result!";

    public FitnessClub(Queries queries, Gson gson) {
        this.queries = queries;
        this.gson = gson;
    }

    @Override
    public String getTrainingByName(String trainingName) throws SQLException {
        var result = queries.getTrainingsByName(trainingName);

        if (result == null || result.isEmpty()) {
            return NO_RESULT;
        }

        Iterator<Training> iterator = result.iterator();

        return gson.toJson(iterator.next());
    }

    @Override
    public String getAllTrainings() throws SQLException {
        var result = queries.getAllTrainings();

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllTrainingsByMuscleGroups(Collection<String> muscleGroups) throws SQLException {
        var result = queries.getAllTrainingsByMusleGroups(muscleGroups);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllTrainingsByExercises(Collection<String> exercises) throws SQLException {
        var result = queries.getAllTrainingsByExercise(exercises);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllTrainingsByType(Collection<String> types) throws SQLException {
        var result = queries.getAllTrainingsByType(types);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getDifficultyByTrainingName(String trainingName) throws SQLException {
        var result = queries.getDifficultyByTrainingName(trainingName);

        return result == null ? NO_RESULT : gson.toJson(result.getTypeString());
    }

    @Override
    public String getDurationForTrainingByName(String trainingName) throws SQLException {
        var result = queries.getDurationForTrainingByName(trainingName);

        return result == 0 ? NO_RESULT : gson.toJson(result + " minutes");
    }

    @Override
    public String getAllEquipment() throws SQLException {
        var result = queries.getAllEquipment();

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getAllEquipmentByTrainingName(String trainingName) throws SQLException {
        var result = queries.getAllEquipmentByTrainingName(trainingName);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getExercisesByTrainingName(String trainingName) throws SQLException {
        var result = queries.getExercisesByTrainingName(trainingName);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }

    @Override
    public String getMusleGroupsByTrainingName(String trainingName) throws SQLException {
        var result = queries.getMuscleGroupsByTrainingName(trainingName);

        return result == null || result.isEmpty() ? NO_RESULT : gson.toJson(result);
    }
}