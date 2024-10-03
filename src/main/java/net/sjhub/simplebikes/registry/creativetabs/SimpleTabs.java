package net.sjhub.simplebikes.registry.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.sjhub.simplebikes.registry.items.SimpleItems;

public class SimpleTabs extends CreativeTabs {

    public static final CreativeTabs BIKE_TAB = new SimpleTabs("bike_tab");

    public SimpleTabs(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(SimpleItems.ACRO_BIKE);
    }
}
