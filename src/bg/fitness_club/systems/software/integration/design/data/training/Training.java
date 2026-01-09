package bg.fitness_club.systems.software.integration.design.data.training;

import bg.fitness_club.systems.software.integration.design.data.equipment.Equipment;
import bg.fitness_club.systems.software.integration.design.data.exercise.Exercise;
import bg.fitness_club.systems.software.integration.design.data.muscleGroup.MuscleGroup;

import java.util.Set;

public record Training(String name, String type, String difficulty,
                     Set<Exercise> exercises, int duration,
                     Set<MuscleGroup> muscleGroup, Set<Equipment> equipment) {

    public Training {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }

        if (type == null) {
            throw new IllegalArgumentException("The type cannot be null!");
        }

        if (difficulty == null) {
            throw new IllegalArgumentException("The difficulty cannot be null!");
        }

        if (exercises == null || exercises.isEmpty()) {
            throw new IllegalArgumentException("The exercises cannot be null or empty!");
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("The duration cannot be zero or less than zero!");
        }
    }
}
