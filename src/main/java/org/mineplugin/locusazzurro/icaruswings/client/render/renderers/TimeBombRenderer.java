package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.TimeBombModel;
import org.mineplugin.locusazzurro.icaruswings.common.entity.TimeBombEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;
public class TimeBombRenderer extends EntityRenderer<TimeBombEntity, EntityRenderState> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/time_bomb.png");
    private final TimeBombModel model;

    public TimeBombRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TimeBombModel(context.bakeLayer(ModelLayerRegistry.TIME_BOMB));
    }

    @Override
    public void submit(EntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.scale(0.1F, 0.1F, 0.1F);
        submitNodeCollector.submitModel(this.model, state, poseStack, TEXTURE, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}

