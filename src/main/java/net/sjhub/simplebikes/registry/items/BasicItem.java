package net.sjhub.simplebikes.registry.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BasicItem extends Item {

    private final String name;
    private final CreativeTabs creativeTab;

    public BasicItem(String name, CreativeTabs creativeTab) {
        this.name = name;
        this.creativeTab = creativeTab;
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(creativeTab);
    }

    public String getName() {
        return name;
    }

    public CreativeTabs getCreativeTab() {
        return creativeTab;
    }
}
