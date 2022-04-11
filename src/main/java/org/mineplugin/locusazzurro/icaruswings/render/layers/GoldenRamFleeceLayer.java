package org.mineplugin.locusazzurro.icaruswings.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

import javax.annotation.ParametersAreNonnullByDefault;

public class GoldenRamFleeceLayer extends RenderLayer<GoldenRamEntity, EntityModel<GoldenRamEntity>> {

    private static final ResourceLocation FLEECE_TEXTURE = new net.minecraft.resources.ResourceLocation(ModData.MOD_ID, "textures/entity/golden_ram_fleece.png");
    private final EntityModel<GoldenRamEntity> model;

    public GoldenRamFleeceLayer(EntityModelSet set, RenderLayerParent<GoldenRamEntity, EntityModel<GoldenRamEntity>> renderer) {
        super(renderer);
        model = new EntityModel<GoldenRamEntity>() {
            @Override
            public void setupAnim(GoldenRamEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

            }

            @Override
            public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {

            }
        };
        //model = new GoldenRamFleeceModel<>(set.bakeLayer(GoldenRamModel.LAYER_LOCATION));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(PoseStack stack, MultiBufferSource buffer, int packedLightIn, GoldenRamEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isSheared() &&!entity.isInvisible()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, FLEECE_TEXTURE, stack, buffer, packedLightIn, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1,1,1);
        }
    }
}
