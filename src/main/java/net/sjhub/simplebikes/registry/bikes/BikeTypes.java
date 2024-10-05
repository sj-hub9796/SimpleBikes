package net.sjhub.simplebikes.registry.bikes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.sjhub.simplebikes.registry.bikes.acrobike.entities.EntityAcrobike;
import net.sjhub.simplebikes.registry.bikes.machbike.entities.EntityMachbike;
import net.sjhub.simplebikes.registry.entities.EntityBike;
import net.sjhub.simplebikes.registry.items.BikeItem;
import net.sjhub.simplebikes.simplebikes.Tags;

import javax.annotation.Nullable;

public enum BikeTypes {

    ACRO_BIKE
            (
                    "acro_bike",
                    null,
                    new ResourceLocation(Tags.MOD_ID, "textures/entity/acro_bike.png"),
                    EntityAcrobike.class
            ),
    MACH_BIKE
            (
                    "mach_bike",
                    null,
                    new ResourceLocation(Tags.MOD_ID, "textures/entity/mach_bike.png"),
                    EntityMachbike.class

            );


    private final String name;
    private Item item;
    private ResourceLocation texture;
    private Class<? extends EntityBike> entity;

    BikeTypes
    (
        String name,
        @Nullable Item item,
        ResourceLocation texture,
        Class<? extends EntityBike> entity
    )
    {
        this.name = name;
        this.item = item;
        this.texture = texture;
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public Item getItem() {
        return item;
    }

    public Class<? extends EntityBike> getEntity() {
        return entity;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public static void initItems() {
        for (BikeTypes bikeTypes : BikeTypes.values()) {
            bikeTypes.setItem(new BikeItem(bikeTypes.getName(), bikeTypes));
        }
    }
}
