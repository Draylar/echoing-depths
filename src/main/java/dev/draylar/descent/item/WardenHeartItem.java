package dev.draylar.descent.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class WardenHeartItem extends Item {

    public WardenHeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(selected) {
            if(entity.age % 20 == 0) {
                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.PLAYERS, 0.5f, 1.0f);
            }
        }
    }
}
