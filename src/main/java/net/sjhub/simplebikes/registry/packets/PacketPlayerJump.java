package net.sjhub.simplebikes.registry.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.sjhub.simplebikes.registry.factory.PlayerJumpTracker;

import java.util.UUID;

public class PacketPlayerJump implements IMessage {

    private boolean jumped;

    // 기본 생성자 (필수)
    public PacketPlayerJump() {}

    // 데이터를 담은 생성자
    public PacketPlayerJump(boolean jumped) {
        this.jumped = jumped;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        jumped = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(jumped);
    }

    // 서버에서 패킷을 처리하는 핸들러
    public static class Handler implements IMessageHandler<PacketPlayerJump, IMessage> {

        @Override
        public IMessage onMessage(PacketPlayerJump message, MessageContext ctx) {
            // 서버측에서 처리: 플레이어의 점프 상태 업데이트
            EntityPlayerMP player = ctx.getServerHandler().player;
            UUID playerUUID = player.getUniqueID();

            // PlayerJumpTracker에 점프 상태 저장
            PlayerJumpTracker.getInstance().setPlayerJumped(playerUUID, message.jumped);

            return null; // 응답 패킷이 필요 없는 경우 null 반환
        }
    }
}
