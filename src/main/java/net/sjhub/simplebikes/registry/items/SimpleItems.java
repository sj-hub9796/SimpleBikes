package net.sjhub.simplebikes.registry.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.sjhub.simplebikes.SimpleBike;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;

import java.util.ArrayList;
import java.util.List;

public class SimpleItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static void registerItems() {
        SimpleBike.LOGGER.info("Registering Items...");
        BikeTypes.initItems();
        for (BikeTypes bikeTypes : BikeTypes.values()) {
            ITEMS.add(bikeTypes.getItem());
        }
        SimpleBike.LOGGER.info("Registering Items... Done!");
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ITEMS.toArray(new Item[]{}));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
