package bg.fitness_club.systems.software.integration.design.exceptions;

public class TrainingAlreadyExistsException extends Exception {
    public TrainingAlreadyExistsException(String message) {
        super(message);
    }

    public TrainingAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}