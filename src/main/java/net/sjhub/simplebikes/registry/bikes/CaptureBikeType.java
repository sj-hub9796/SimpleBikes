package net.sjhub.simplebikes.registry.bikes;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CaptureBikeType {

    private static final Map<UUID, BikeTypes> UUID_BIKE_TYPES_MAP = new HashMap<>();

    public static void setBikeTypes(UUID uuid, BikeTypes bikeTypes) {
        UUID_BIKE_TYPES_MAP.put(uuid, bikeTypes);
    }

    @Nullable
    public static BikeTypes getBikeTypes(UUID uuid) {
        return UUID_BIKE_TYPES_MAP.get(uuid);
    }

    public static BikeTypes getBikeTypesFallback(UUID uuid) {
        return UUID_BIKE_TYPES_MAP.getOrDefault(uuid, BikeTypes.ACRO_BIKE);
    }

    public static void removeBikeTypes(UUID uuid) {
        UUID_BIKE_TYPES_MAP.remove(uuid);
    }

    public static boolean containsKey(UUID uuid) {
        return UUID_BIKE_TYPES_MAP.containsKey(uuid);
    }
}
