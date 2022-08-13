package dev.draylar.descent.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SeekingSoulEntity extends Entity {

    private Entity target;
    private int invalidAttempts = 0;

    public SeekingSoulEntity(EntityType<?> type, World world) {
        super(type, world);
        noClip = true;
    }

    @Override
    public void tick() {
        super.tick();

        if(!world.isClient) {

            if(age >= 20 * 20) {
                discard();
            }

            if(target != null && !target.isAlive()) {
                target = null;
            }

            // find target
            if(target == null && age % 5 == 0) {
                @Nullable HostileEntity found = world.getClosestEntity(HostileEntity.class, TargetPredicate.DEFAULT, null, getX(), getY(), getZ(), new Box(getBlockPos()).expand(16));
                if(found != null) {
                    target = found;
                } else {
                    invalidAttempts++;
                }

                // If invalid attempts goes too high, remove this entity early.
                if(invalidAttempts >= 50) {
                    discard();
                }
            }

            // move towards target
            if(target != null) {
                double distanceToTarget = getPos().distanceTo(target.getEyePos());
                if(distanceToTarget >= 1.0) {
                    Vec3d towardsTarget = getPos().subtract(target.getEyePos()).normalize().multiply(-0.5);
                    setVelocity(towardsTarget);
                    move(MovementType.SELF, towardsTarget);
                } else {
                    // tick damage
                    target.damage(DamageSource.GENERIC, 1.0f);
                }
            }
        } else {
            double particleX = world.random.nextDouble() - 0.5f;
            double particleY = world.random.nextDouble() - 0.5f;
            double particleZ = world.random.nextDouble() - 0.5f;
            world.addParticle(ParticleTypes.SCULK_SOUL, true, getX() + particleX, getY() + particleY, getZ() + particleZ, 0, 0, 0);
        }
    }

    @Override
    public void initDataTracker() {

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}
