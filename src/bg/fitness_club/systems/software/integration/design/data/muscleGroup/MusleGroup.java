package bg.fitness_club.systems.software.integration.design.data.muscleGroup;

public record MusleGroup(String name) {
    public MusleGroup {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }
    }
}