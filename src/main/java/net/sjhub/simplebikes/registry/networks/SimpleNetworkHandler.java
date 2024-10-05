package net.sjhub.simplebikes.registry.networks;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.sjhub.simplebikes.registry.packets.PacketPlayerJump;
import net.sjhub.simplebikes.simplebikes.Tags;

public class SimpleNetworkHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MOD_ID);

    public static void registerMessages() {
        // 0은 패킷 ID, Side.CLIENT는 클라이언트로 패킷을 보냄을 의미합니다.
        INSTANCE.registerMessage(PacketPlayerJump.Handler.class, PacketPlayerJump.class, 0, Side.SERVER);
    }
}
