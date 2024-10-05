package net.sjhub.simplebikes.registry.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.sjhub.simplebikes.registry.networks.SimpleNetworkHandler;
import net.sjhub.simplebikes.registry.packets.PacketPlayerJump;

public class PlayerTickEvent {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        // 점프 키 바인드를 감지합니다.
        KeyBinding jumpKey = Minecraft.getMinecraft().gameSettings.keyBindJump;
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player == null) return;
        SimpleNetworkHandler.INSTANCE.sendToServer(new PacketPlayerJump(jumpKey.isKeyDown()));
    }
}
