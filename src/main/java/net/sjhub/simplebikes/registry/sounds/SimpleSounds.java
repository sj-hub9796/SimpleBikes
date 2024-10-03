package net.sjhub.simplebikes.registry.sounds;

import net.minecraft.client.audio.Sound;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.sjhub.simplebikes.SimpleBike;
import net.sjhub.simplebikes.simplebikes.Tags;

import java.util.ArrayList;
import java.util.List;

public class SimpleSounds {

    public static final List<SoundEvent> SOUNDS = new ArrayList<SoundEvent>();

    public static final SoundEvent BIKE_RINGING = new SoundEvent(new ResourceLocation(Tags.MOD_ID, "bike_ringing")).setRegistryName("bike_ringing");


    @SubscribeEvent
    public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(SOUNDS.toArray(new SoundEvent[]{}));
    }

    public static void registerSounds() {
        SimpleBike.LOGGER.info("Register Sounds...");
        SOUNDS.add(BIKE_RINGING);
        SimpleBike.LOGGER.info("Registration Done!");
    }
}
