package dev.draylar.descent.registry;

import dev.draylar.descent.EchoingDepths;
import dev.draylar.descent.item.*;
import dev.draylar.descent.material.DescentToolMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static dev.draylar.descent.material.DescentArmorMaterial.INSTANCE;

public class EDItems {

    public static final ArmorItem DESCENT_HELMET = register("descent_helmet", new ArmorItem(INSTANCE, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE)));
    public static final ArmorItem DESCENT_CHESTPLATE = register("descent_chestplate", new ArmorItem(INSTANCE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE)));
    public static final ArmorItem DESCENT_LEGGINGS = register("descent_leggings", new ArmorItem(INSTANCE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE)));
    public static final ArmorItem DESCENT_BOOTS = register("descent_boots", new ArmorItem(INSTANCE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE)));
    public static final Item ECHO_PIERCER = register("echo_piercer", new EchoPiercerItem(new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE).maxDamage(1500)));
    public static final Item ILLUMINATED_DEPTHS = register("illuminated_depths", new IlluminatedDepthsItem(new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE).maxDamage(1500)));
    public static final Item SOUL_DESCENT = register("soul_descent", new SoulDescentItem(DescentToolMaterial.INSTANCE, 0, -2.4f, new Item.Settings().maxDamage(1500).group(ItemGroup.COMBAT).rarity(Rarity.RARE)));
    public static final Item WARDEN_HEART = register("warden_heart", new WardenHeartItem(new Item.Settings().group(ItemGroup.MATERIALS).rarity(Rarity.RARE)));
    public static final Item KNOWLEDGE_OF_DARK_DEPTHS = register("knowledge_of_dark_depths", new KnowledgeDarkDepthsItem(new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE).maxDamage(1500)));

    public static <T extends Item> T register(String name, T instance) {
        return Registry.register(Registry.ITEM, EchoingDepths.id(name), instance);
    }

    public static void register() {

    }
}
