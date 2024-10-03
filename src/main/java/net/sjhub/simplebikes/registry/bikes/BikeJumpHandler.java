package net.sjhub.simplebikes.registry.bikes;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.sjhub.simplebikes.registry.entities.BikeEntity;

public class BikeJumpHandler {

    @SubscribeEvent
    public void handle(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;

        if (player.world.isRemote) {
            if (Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()) {
                if (player.isRiding() && player.getRidingEntity() instanceof BikeEntity) {
                    BikeEntity bikeEntity = (BikeEntity) player.getRidingEntity();
                    bikeEntity.jump();
                }
            }
        }
    }
}
