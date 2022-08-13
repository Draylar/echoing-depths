package dev.draylar.descent;

import dev.draylar.descent.client.renderer.DepthsMonsterRenderer;
import dev.draylar.descent.registry.EDEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EchoingDepthsClient implements ClientModInitializer {

    public static final EntityModelLayer DEPTHS_MONSTER = new EntityModelLayer(new Identifier("minecraft", "main"), "depths_monster");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EDEntities.SEEKING_SOUL, EmptyEntityRenderer::new);
        EntityRendererRegistry.register(EDEntities.MONSTER_OF_DEPTHS, DepthsMonsterRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DEPTHS_MONSTER, () -> TexturedModelData.of(BipedEntityModel.getModelData(Dilation.NONE, 0), 64, 32));
    }
}
