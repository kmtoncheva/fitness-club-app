package bg.fitness_club.systems.software.integration.design.commands.handlers;

import java.sql.SQLException;

@FunctionalInterface
public interface CommandHandler {
    String execute() throws SQLException;
}
