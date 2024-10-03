package net.sjhub.simplebikes.registry.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.entities.BikeEntity;

public class BikeItems extends BasicItems {

    private final BikeTypes bikeType;

    public BikeItems(String name, BikeTypes bikeType) {
        super(name);
        this.setMaxStackSize(1);
        this.bikeType = bikeType;
    }

    public BikeTypes getBikeType() {
        return bikeType;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            double spawnX = pos.getX() + 0.5;
            double spawnY = pos.getY() + 1;
            double spawnZ = pos.getZ() + 0.5;

            BikeEntity bikeEntity = new BikeEntity(world, bikeType);
            bikeEntity.setPosition(spawnX, spawnY, spawnZ);
            world.spawnEntity(bikeEntity);

            if (!player.isCreative()) {
                player.getHeldItem(hand).shrink(1);
            }
        }
        return EnumActionResult.SUCCESS;
    }
}
