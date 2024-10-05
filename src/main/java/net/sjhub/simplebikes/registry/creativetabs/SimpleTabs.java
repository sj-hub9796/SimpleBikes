package net.sjhub.simplebikes.registry.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.sjhub.simplebikes.SimpleBike;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;

public class SimpleTabs extends CreativeTabs {

    public static final CreativeTabs BIKE_TAB = new SimpleTabs("bike_tab");

    private static Item ICON;

    public SimpleTabs(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ICON);
    }

    public static void registerCreativeTabs() {
        SimpleBike.LOGGER.info("Registering CreativeTabs...");
        ICON = BikeTypes.ACRO_BIKE.getItem();
        SimpleBike.LOGGER.info("Registering CreativeTas... Done!");
    }
}
