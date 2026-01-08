package bg.fitness_club.systems.software.integration.design.data.exercise;

public record Exercise(String name) {
    public Exercise {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }
    }
}