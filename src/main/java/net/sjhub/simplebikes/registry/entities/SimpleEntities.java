package net.sjhub.simplebikes.registry.entities;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.sjhub.simplebikes.SimpleBike;
import net.sjhub.simplebikes.simplebikes.Tags;

public class SimpleEntities {

    private static int entityId = 0;

    public static void registerEntities() {
        EntityRegistry.registerModEntity(
                new ResourceLocation(Tags.MOD_ID, "bike_entity"),
                BikeEntity.class,
                "bike_entity",
                entityId++,
                SimpleBike.instance,
                64,
                1,
                true
        );
    }
}
