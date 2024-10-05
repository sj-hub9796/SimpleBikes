package net.sjhub.simplebikes.registry.bikes.machbike.entities;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.entities.EntityBike;

public class EntityMachbike extends EntityBike {

    public float wheelRotation = 0;
    public float pedalRotation = 0;

    public EntityMachbike(World worldIn) {
        super(worldIn);
        this.setSize(1.6F, 1.5F);
        this.defaultSpeed = 0.05F;
        this.maxForwardSpeed = 0.4F;
        this.maxReverseSpeed = 0.2F;
        this.acceleration = 0.05F;
        this.deceleration = 0.01F;
        this.offsetY = -0.1;
        this.offsetZ = 0.3;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.setDead();
        Item item = BikeTypes.MACH_BIKE.getItem();
        this.dropItem(item, 1);
    }
}
