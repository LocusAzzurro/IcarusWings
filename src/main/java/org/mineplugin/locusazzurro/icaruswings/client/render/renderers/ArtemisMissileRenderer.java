package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.ArtemisMissileModel;
import org.mineplugin.locusazzurro.icaruswings.client.render.state.ArtemisMissileRenderState;
import org.mineplugin.locusazzurro.icaruswings.common.entity.ArtemisMissileEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;
public class ArtemisMissileRenderer extends EntityRenderer<ArtemisMissileEntity, ArtemisMissileRenderState> {
    private static final Identifier MISSILE = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/artemis_missile.png");
    private final ArtemisMissileModel model;

    public ArtemisMissileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ArtemisMissileModel(context.bakeLayer(ModelLayerRegistry.ARTEMIS_MISSILE));
    }

    @Override
    public void submit(ArtemisMissileRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.scale(0.15F, 0.15F, 0.15F);
        poseStack.translate(0.0F, -0.25F, 0.0F);
        poseStack.mulPose(Axis.YN.rotationDegrees(state.yRot));
        poseStack.mulPose(Axis.XN.rotationDegrees(state.xRot));
        submitNodeCollector.submitModel(this.model, state, poseStack, MISSILE, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public ArtemisMissileRenderState createRenderState() {
        return new ArtemisMissileRenderState();
    }

    @Override
    public void extractRenderState(ArtemisMissileEntity entity, ArtemisMissileRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yRot = entity.getYRot(partialTicks);
        state.xRot = entity.getXRot(partialTicks);
    }
}

