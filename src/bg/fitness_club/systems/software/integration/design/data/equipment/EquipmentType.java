package bg.fitness_club.systems.software.integration.design.data.equipment;

import java.util.Arrays;

public enum EquipmentType {
    NONE("none"),
    BODYWEIGHT("bodyweight"),
    DUMBBELLS("dumbells"),
    UNKNOWN("unknown");

    private final String equipmentType;

    EquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentTypeString() {
        return equipmentType;
    }

    public static EquipmentType getEquipmentType(String equipmentType) {
        return Arrays.stream(values())
            .filter(c -> c.equipmentType.equalsIgnoreCase(equipmentType))
            .findFirst()
            .orElse(UNKNOWN);
    }
}