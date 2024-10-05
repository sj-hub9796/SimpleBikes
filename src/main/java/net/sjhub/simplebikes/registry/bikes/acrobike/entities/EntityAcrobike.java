package net.sjhub.simplebikes.registry.bikes.acrobike.entities;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.entities.EntityBike;

public class EntityAcrobike extends EntityBike {

    public float wheelRotation = 0;
    public float pedalRotation = 0;

    public EntityAcrobike(World worldIn) {
        super(worldIn);
        this.setSize(1.6F, 1.3F);
        this.defaultSpeed = 0.01F;
        this.maxForwardSpeed = 0.2F;
        this.maxReverseSpeed = 0.1F;
        this.acceleration = 0.02F;
        this.deceleration = 0.01F;
        this.offsetY = -0.15;
        this.offsetZ = 0.3;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.setDead();
        Item item = BikeTypes.ACRO_BIKE.getItem();
        this.dropItem(item, 1);
    }
}
