package net.sjhub.simplebikes.registry.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJumpTracker {

    private static final PlayerJumpTracker INSTANCE = new PlayerJumpTracker();
    private Map<UUID, Boolean> playerJumpMap = new HashMap<>();

    // 플레이어의 점프 상태 설정
    public void setPlayerJumped(UUID playerUUID, boolean jumped) {
        playerJumpMap.put(playerUUID, jumped);
    }

    // 플레이어의 점프 상태 반환
    public boolean hasPlayerJumped(UUID playerUUID) {
        return playerJumpMap.getOrDefault(playerUUID, false);
    }

    // 플레이어의 점프 상태 초기화
    public void resetPlayerJump(UUID playerUUID) {
        playerJumpMap.put(playerUUID, false);
    }

    public static PlayerJumpTracker getInstance() {
        return INSTANCE;
    }
}
