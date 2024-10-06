package net.sjhub.simplebikes.registry.entities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.sjhub.simplebikes.registry.factory.PlayerJumpTracker;
import net.sjhub.simplebikes.registry.sounds.SimpleSounds;

public class EntityBike extends EntityLiving {

    protected float defaultSpeed;
    protected float maxForwardSpeed;
    protected float maxReverseSpeed;
    protected float acceleration;
    protected float deceleration;
    protected double offsetX;
    protected double offsetY;
    protected double offsetZ;

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public EntityBike(World worldIn) {
        super(worldIn);
        this.defaultSpeed = 0;
        this.maxForwardSpeed = 0;
        this.maxReverseSpeed = 0;
        this.acceleration = 0;
        this.deceleration = 0;
        this.isImmuneToFire = true;
        this.isAirBorne = false;
        this.stepHeight = 1.0F;
        this.offsetX = 0;
        this.offsetY = 0;
        this.offsetZ = 0;
        this.setSize(1.0F, 1.0F);
        this.enablePersistence();
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

    private float currentSpeed = 0.0F;
    private final float rotationSpeed = 12.0F;
    private boolean isJumping = false;

    private int soundTimer = 0;
    private final int soundInterval = 70;

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!(this.getControllingPassenger() instanceof EntityPlayer)) {
            if (currentSpeed > 0) {
                currentSpeed = 0;
            }
            return;
        };
        EntityPlayer player = (EntityPlayer) this.getControllingPassenger();

        // 엔티티의 움직임 계산
        SoundEvent soundEvent = SimpleSounds.BIKE_STEP;
        if (this.motionX != 0 || this.motionZ != 0) {
            // 첫 회 사운드를 즉시 재생하고 이후로 타이머에 따라 재생
            if (soundTimer == 0) {
                this.playSound(soundEvent, 10.0F, 1.0F); // 예시로 말을 달리는 소리 사용
                soundTimer = 1; // 타이머 초기화 후 증가
            } else if (soundTimer >= soundInterval) {
                this.playSound(soundEvent, 10.0F, 1.0F); // 사운드 재생
                soundTimer = 1; // 타이머 초기화
            }
            soundTimer++; // 타이머 증가
        } else {
            // 엔티티가 멈추면 타이머 초기화
            soundTimer = 0;
        }

        boolean moveForward = player.moveForward > 0;
        boolean moveBackward = player.moveForward < 0;
        boolean turnLeft = player.moveStrafing > 0;
        boolean turnRight = player.moveStrafing < 0;
        boolean jump = PlayerJumpTracker.getInstance().hasPlayerJumped(player.getUniqueID());

        float maxForwardSpeed = this.maxForwardSpeed;
        float maxReverseSpeed = this.maxReverseSpeed;
        float acceleration = this.acceleration;
        float deceleration = this.deceleration;

        // 점프 처리
        if (jump && !isJumping) {
            this.motionY = this.motionY + 0.5;
            isJumping = true;
        }

        BlockPos blockPos = new BlockPos(this.getPosition().getX(), this.getPosition().getY() - 0.1, this.getPosition().getZ());
        IBlockState blockState = world.getBlockState(blockPos);

        if (this.motionY <= 0 && blockState.getBlock() != Blocks.AIR) {
            // 착지시
            if (isJumping) {
                this.playSound(SimpleSounds.BIKE_LANDING, 1.0F, 1.0F);
            }
            isJumping = false;
        }

        // 속도 조정 로직
        if (moveForward) {
            // 전진 중: 가속
            if (currentSpeed < maxForwardSpeed) {
                currentSpeed += acceleration;
                if (currentSpeed > maxForwardSpeed) {
                    currentSpeed = maxForwardSpeed; // 최대 속도 제한
                }
            }
        } else if (moveBackward) {
            // 후진 중: 전진 중일 경우 감속, 후진으로 전환
            if (currentSpeed > -maxReverseSpeed) {
                if (currentSpeed > 0) {
                    // 전진 중이면 감속
                    currentSpeed -= deceleration;
                } else {
                    // 후진으로 가속
                    currentSpeed -= acceleration;
                    if (currentSpeed < -maxReverseSpeed) {
                        currentSpeed = -maxReverseSpeed; // 후진 최대 속도 제한
                    }
                }
            }
        } else {
            // 입력이 없을 때 감속
            if (currentSpeed > 0) {
                currentSpeed -= deceleration;
                if (currentSpeed < 0) {
                    currentSpeed = 0; // 멈춤
                }
            } else if (currentSpeed < 0) {
                currentSpeed += deceleration;
                if (currentSpeed > 0) {
                    currentSpeed = 0; // 멈춤
                }
            }
        }

        // 엔티티의 yaw와 플레이어의 yaw를 부드럽게 동기화
        float playerYaw = player.rotationYaw;
        float yawDifference = MathHelper.wrapDegrees(playerYaw - this.rotationYaw); // 플레이어와 엔티티 간의 yaw 차이 계산

        // A, D로 빠르게 방향 전환 가능
        if (turnLeft) {
            this.rotationYaw -= rotationSpeed; // 좌회전
        } else if (turnRight) {
            this.rotationYaw += rotationSpeed; // 우회전
        } else {
            // A, D 입력이 없을 때 플레이어의 yaw에 자연스럽게 동기화
            this.rotationYaw += yawDifference * 0.1f; // 플레이어와 엔티티 yaw 동기화 비율 조정
        }

        this.rotationYawHead = this.rotationYaw; // 머리 회전과 몸통 회전을 동일하게 설정
        this.renderYawOffset = this.rotationYaw; // 모델의 회전 오프셋 설정

        if (!moveForward && !moveBackward && (turnLeft || turnRight)) {
            this.velocityChanged = true; // 렌더링 업데이트를 위해 위치가 변경되었음을 알림
        }

        // 엔티티의 yaw 값에 따라 이동 처리
        float yaw = this.rotationYaw;
        double radians = Math.toRadians(yaw);

        // motionX와 motionZ를 엔티티의 yaw 값으로 계산하여 이동
        this.motionX = -Math.sin(radians) * currentSpeed;
        this.motionZ = Math.cos(radians) * currentSpeed;

        // 실제 이동
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 1;
    }

    // 낙하 대미지 새로 계산하기
    @Override
    public void fall(float distance, float damageMultiplier) {
        float defaultDistance = 20.0F;
        if (distance > defaultDistance) {
            super.fall(distance, damageMultiplier);
        }
        return;
    }

    // 사망시 자전거 아이템 드롭
    @Override
    public void onDeath(DamageSource cause) {
        super.setDead();
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1F, 2.0F);
    }

    @Override
    public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
        super.knockBack(entityIn, strength * (float) 0.1, xRatio, zRatio);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        boolean result = super.attackEntityFrom(source, amount);
        this.hurtTime = 0;
        return result;
    }
}
