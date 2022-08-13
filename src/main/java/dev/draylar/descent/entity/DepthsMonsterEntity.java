package dev.draylar.descent.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class DepthsMonsterEntity extends PathAwareEntity {

    public DepthsMonsterEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void initGoals() {
        goalSelector.add(0, new MeleeAttackGoal(this, 1.0f, false));
        goalSelector.add(2, new AvoidSunlightGoal(this));
        goalSelector.add(3, new EscapeSunlightGoal(this, 1.0));
        goalSelector.add(3, new FleeEntityGoal<>(this, WardenEntity.class, 6.0f, 1.0, 1.2));
        goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        goalSelector.add(6, new LookAroundGoal(this));
        targetSelector.add(0, new ActiveTargetGoal<>(this, HostileEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createDepthsMonsterAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }

    @Override
    public void tick() {
        super.tick();

        if(!world.isClient) {
            if(age >= 20 * 30) {
                discard();
                ((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_SOUL, getX(), getY(), getZ(), 50,0.5f, 0.5f, 2, 0);
            }
        }
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WARDEN_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.PARTICLE_SOUL_ESCAPE;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.PARTICLE_SOUL_ESCAPE;
    }
}
