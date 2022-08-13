package dev.draylar.descent.item;

import dev.draylar.descent.entity.DepthsMonsterEntity;
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
import net.minecraft.world.World;

public class KnowledgeDarkDepthsItem extends Item {

    public KnowledgeDarkDepthsItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 60;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);

        if(remainingUseTicks % 20 == 0) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.PLAYERS, 0.5f, 1.0f);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(!world.isClient) {

            // Summon Skeleton beasts around the player to defend them
            for (int i = 0; i < 5; i++) {
                double randX = world.random.nextDouble() * 2 - 1;
                double randZ = world.random.nextDouble() * 2 - 1;

                DepthsMonsterEntity monster = new DepthsMonsterEntity(EDEntities.MONSTER_OF_DEPTHS, world);
                monster.updatePosition(user.getX() + randX, user.getY(), user.getZ() + randZ);
                world.spawnEntity(monster);
            }

            // sfx
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_EMERGE, SoundCategory.PLAYERS, 1.0f, 1.0f);

            // damage
            if(user instanceof PlayerEntity player) {
                player.getItemCooldownManager().set(this, 20 * 30);
                stack.damage(1, user, x -> x.sendToolBreakStatus(Hand.MAIN_HAND));
            }
        }

        return super.finishUsing(stack, world, user);
    }
}
