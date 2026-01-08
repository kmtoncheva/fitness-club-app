package bg.fitness_club.systems.software.integration.design.exceptions;

public class TrainingNotFoundException extends Exception {
    public TrainingNotFoundException(String message) {
        super(message);
    }

    public TrainingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}