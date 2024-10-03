package net.sjhub.simplebikes.registry.items;

import net.minecraft.item.Item;
import net.sjhub.simplebikes.registry.creativetabs.SimpleTabs;

public class BasicItems extends Item {

    private final String name;

    public BasicItems(String name) {
        this.name = name;
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(SimpleTabs.BIKE_TAB);
    }

    public String getName() {
        return name;
    }
}
