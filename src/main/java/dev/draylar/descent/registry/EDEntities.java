package dev.draylar.descent.registry;

import dev.draylar.descent.EchoingDepths;
import dev.draylar.descent.entity.DepthsMonsterEntity;
import dev.draylar.descent.entity.SeekingSoulEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class EDEntities {

    public static final EntityType<SeekingSoulEntity> SEEKING_SOUL = register("seeking_soul", FabricEntityTypeBuilder
            .create()
            .spawnGroup(SpawnGroup.MISC)
            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
            .disableSaving()
            .entityFactory(SeekingSoulEntity::new)
            .build());

    public static final EntityType<DepthsMonsterEntity> MONSTER_OF_DEPTHS = register("monster_of_depths", FabricEntityTypeBuilder
            .createMob()
            .spawnGroup(SpawnGroup.MISC)
            .defaultAttributes(DepthsMonsterEntity::createDepthsMonsterAttributes)
            .dimensions(EntityDimensions.fixed(0.75f, 1.7f))
            .entityFactory(DepthsMonsterEntity::new)
            .build());

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> entity) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, EchoingDepths.id(name), entity);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entity) {
        return Registry.register(Registry.ENTITY_TYPE, EchoingDepths.id(name), entity);
    }

    public static void register() {
        // NO-OP
    }

    private EDEntities() {
        // NO-OP
    }
}
