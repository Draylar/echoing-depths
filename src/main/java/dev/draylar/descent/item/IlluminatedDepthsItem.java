package dev.draylar.descent.item;

import dev.draylar.descent.entity.SeekingSoulEntity;
import dev.draylar.descent.registry.EDEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class IlluminatedDepthsItem extends Item {

    public IlluminatedDepthsItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 30;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(!world.isClient) {
            SeekingSoulEntity soul = new SeekingSoulEntity(EDEntities.SEEKING_SOUL, world);
            Vec3d spawnPos = user.getEyePos();
            soul.updatePosition(spawnPos.getX(), spawnPos.getY() + 1, spawnPos.getZ());
            world.playSound(null, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.spawnEntity(soul);

            if(user instanceof PlayerEntity player) {
                player.getItemCooldownManager().set(this, 20 * 15);
                stack.damage(1, user, it -> it.sendToolBreakStatus(Hand.MAIN_HAND));
            }
        }

        return super.finishUsing(stack, world, user);
    }
}
