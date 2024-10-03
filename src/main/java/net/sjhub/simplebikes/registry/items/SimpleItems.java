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

    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item ACRO_BIKE = new BikeItems("acro_bike", BikeTypes.ACRO_BIKE);
    public static final Item MACH_BIKE = new BikeItems("mach_bike", BikeTypes.MACH_BIKE);


    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        SimpleBike.LOGGER.info("Items: " + ITEMS);
        event.getRegistry().registerAll(ITEMS.toArray(new Item[]{}));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }

    public static void registerItems() {
        SimpleBike.LOGGER.info("Register Items...");
        ITEMS.add(ACRO_BIKE);
        ITEMS.add(MACH_BIKE);
        SimpleBike.LOGGER.info("Registration Done!");
    }
}
