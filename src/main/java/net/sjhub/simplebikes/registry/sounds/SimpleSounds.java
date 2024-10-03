package net.sjhub.simplebikes.registry.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.sjhub.simplebikes.SimpleBike;
import net.sjhub.simplebikes.simplebikes.Tags;


public class SimpleSounds {

    public static SoundEvent BIKE_RINGING;

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Tags.MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

    public static void registerSounds() {
        SimpleBike.LOGGER.info("Register Sounds...");
        BIKE_RINGING = registerSound("bike_ringing");
        SimpleBike.LOGGER.info("Registration Done!");
    }
}
