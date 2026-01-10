package bg.fitness_club.systems.software.integration.design.model.equipment;

import java.sql.ResultSet;
import java.sql.SQLException;

import bg.fitness_club.systems.software.integration.design.model.difficulty.Difficulty;

public record Equipment(String name, String type, String difficulty) {
    public Equipment {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null, empty or blank!");
        }

        if (type == null) {
            throw new IllegalArgumentException("The type cannot be null!");
        }

        if (difficulty == null) {
            throw new IllegalArgumentException("The difficulty cannot be null!");
        }
    }

    public static Equipment of(ResultSet resultSet) throws SQLException {
        return new Equipment(resultSet.getString("name"),
            EquipmentType.getEquipmentType(resultSet.getString("type")).getEquipmentTypeString(),
            Difficulty.getType(resultSet.getString("difficulty")).getTypeString());
    }
}