package net.sjhub.simplebikes.registry.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.bikes.CaptureBikeType;
import net.sjhub.simplebikes.registry.sounds.SimpleSounds;

public class BikeEntity extends EntityLiving {

    private BikeTypes bikeTypes;

    public BikeEntity(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.0F);
        this.noClip = false;
        this.isAirBorne = false;
    }

    public BikeEntity(World worldIn, BikeTypes bikeTypes) {
        this(worldIn);
        this.bikeTypes = bikeTypes;
        this.init();
    }

    public void init() {
        CaptureBikeType.setBikeTypes(this.getUniqueID(), bikeTypes);
        writeEntityToNBT(new NBTTagCompound());
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (CaptureBikeType.getBikeTypes(this.getUniqueID()) != null) {
            BikeTypes bikeType = CaptureBikeType.getBikeTypes(this.getUniqueID());
            this.setSize(bikeType.getWidth(), bikeType.getHeight());
        }

        if (this.isBeingRidden() && this.getControllingPassenger() != null) {
            if (this.getControllingPassenger() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) this.getControllingPassenger();
            }
        }
    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        if (this.isBeingRidden() && this.canBeSteered()) {
            EntityLivingBase rider = (EntityLivingBase) this.getControllingPassenger();
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!this.world.isRemote) {
            World world = this.getEntityWorld();
            double posX = this.getPosition().getX();
            double posY = this.getPosition().getY();
            double posZ = this.getPosition().getZ();
            world.playSound(null, posX, posY, posZ, SimpleSounds.BIKE_RINGING, SoundCategory.HOSTILE, 1.0F, 1.0F);
            player.startRiding(this);
            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 1;
    }

    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        if (this.bikeTypes != null) {
            compound.setString("BikeType", this.bikeTypes.toString());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("BikeType")) {
            String bikeTypeString = compound.getString("BikeType");
            BikeTypes bikeType = BikeTypes.valueOf(bikeTypeString);
            this.bikeTypes = bikeType;
            CaptureBikeType.setBikeTypes(this.getUniqueID(), bikeType);
        }
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        CaptureBikeType.removeBikeTypes(this.getUniqueID());
    }

    @Override
    public void jump() {
        this.motionY = 1.5D;
        this.isAirBorne = true;
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        super.applyEntityCollision(entityIn);
    }

    @Override
    public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
        super.knockBack(entityIn, strength, xRatio, zRatio);
    }
}
