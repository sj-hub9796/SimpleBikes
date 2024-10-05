package net.sjhub.simplebikes.registry.entities;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.sjhub.simplebikes.SimpleBike;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.bikes.acrobike.entities.EntityAcrobike;
import net.sjhub.simplebikes.registry.bikes.machbike.entities.EntityMachbike;
import net.sjhub.simplebikes.simplebikes.Tags;

public class SimpleEntities {

    public static void registerEntities() {
        //registerEntity("bike_entity", EntityBike.class, 200, 64, 1, true);
        registerEntity(BikeTypes.ACRO_BIKE.getName(), EntityAcrobike.class, 201, 64, 1, true);
        registerEntity(BikeTypes.MACH_BIKE.getName(), EntityMachbike.class, 202, 64, 1, true);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(
                new ResourceLocation(Tags.MOD_ID, name),
                entity,
                name,
                id,
                SimpleBike.instance,
                range,
                updateFrequency,
                sendsVelocityUpdates
        );
    }
}
