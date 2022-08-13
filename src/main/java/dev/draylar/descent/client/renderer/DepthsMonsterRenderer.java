package dev.draylar.descent.client.renderer;

import dev.draylar.descent.EchoingDepths;
import dev.draylar.descent.entity.DepthsMonsterEntity;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class DepthsMonsterRenderer extends BipedEntityRenderer<DepthsMonsterEntity, BipedEntityModel<DepthsMonsterEntity>> {

    private static final Identifier TEXTURE = EchoingDepths.id("textures/entity/depths_monster.png");

    public DepthsMonsterRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.SKELETON);
    }

    public DepthsMonsterRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer) {
        super(ctx, new BipedEntityModel<>(ctx.getPart(layer)), 0.5f);
    }

    @Override
    public Identifier getTexture(DepthsMonsterEntity abstractSkeletonEntity) {
        return TEXTURE;
    }
}