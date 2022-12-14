package dev.draylar.descent.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class DescentArmorMaterial implements ArmorMaterial {

    public static final DescentArmorMaterial INSTANCE = new DescentArmorMaterial();
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int DURABILITY_MULTIPLIER = 35;
    private static final int[] PROTECTION_AMOUNT = new int[]{4, 7, 9, 4};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * DURABILITY_MULTIPLIER;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_AMOUNT[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.PARTICLE_SOUL_ESCAPE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.ECHO_SHARD);
    }

    @Override
    public String getName() {
        return "descent";
    }

    @Override
    public float getToughness() {
        return 2;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.2f;
    }
}
