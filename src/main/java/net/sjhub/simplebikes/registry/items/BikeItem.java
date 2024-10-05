package net.sjhub.simplebikes.registry.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sjhub.simplebikes.registry.bikes.BikeTypes;
import net.sjhub.simplebikes.registry.bikes.acrobike.entities.EntityAcrobike;
import net.sjhub.simplebikes.registry.creativetabs.SimpleTabs;

import java.lang.reflect.Constructor;

public class BikeItem extends BasicItem {

    private final BikeTypes bikeTypes;

    public BikeItem(String name, BikeTypes bikeTypes) {
        super(name, SimpleTabs.BIKE_TAB);
        this.setMaxStackSize(1);
        this.bikeTypes = bikeTypes;
    }

    public BikeTypes getBikeTypes() {
        return bikeTypes;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            double spawnX = pos.getX() + 0.5;
            double spawnY = pos.getY() + 1;
            double spawnZ = pos.getZ() + 0.5;

            try {
                Class<?> clazz = bikeTypes.getEntity();
                Constructor<?> constructor = clazz.getDeclaredConstructor(World.class);
                Object instance = constructor.newInstance(world);
                EntityLiving bikeEntity = (EntityLiving) instance;
                bikeEntity.setPosition(spawnX, spawnY, spawnZ);
                world.spawnEntity(bikeEntity);

                if (!player.isCreative()) {
                    player.getHeldItem(hand).shrink(1);
                }

                return EnumActionResult.SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return super.getCreativeTab();
    }
}
