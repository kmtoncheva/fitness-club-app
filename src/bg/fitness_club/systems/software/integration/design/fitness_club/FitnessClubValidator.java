package bg.fitness_club.systems.software.integration.design.fitness_club;

import bg.fitness_club.systems.software.integration.design.data.training.Training;
import bg.fitness_club.systems.software.integration.design.exceptions.TrainingAlreadyExistsException;

import java.util.Set;

public abstract class FitnessClubValidator {
    void validateForExistingTraining(Training training, Set<Training> trainingSet) throws
           TrainingAlreadyExistsException {
        if (trainingSet.contains(training)) {
            throw new TrainingAlreadyExistsException("The training you want to add is already existing!");
        }
    }

    void validateTrainingForNull(Training training) {
        if (training == null) {
            throw new IllegalArgumentException("The training cannot be null!");
        }
    }

    void validateExerciseString(String exerciseName) {
        if (exerciseName == null || exerciseName.isEmpty() || exerciseName.isBlank()) {
            throw new IllegalArgumentException("The exercise name cannot be null, empty or blank!");
        }
    }
}