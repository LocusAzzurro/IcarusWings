package org.mineplugin.locusazzurro.icaruswings.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.render.models.GoldenRamFleeceModel;
import org.mineplugin.locusazzurro.icaruswings.render.models.GoldenRamModel;

import javax.annotation.ParametersAreNonnullByDefault;

public class GoldenRamFleeceLayer extends RenderLayer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> {

    private static final ResourceLocation FLEECE_TEXTURE = new net.minecraft.resources.ResourceLocation(ModData.MOD_ID, "textures/entity/golden_ram_fleece.png");
    private final GoldenRamFleeceModel<GoldenRamEntity> model;

    public GoldenRamFleeceLayer(EntityModelSet set, RenderLayerParent<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> renderer) {
        super(renderer);
        model = new GoldenRamFleeceModel<>(set.bakeLayer(GoldenRamModel.LAYER_LOCATION));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(PoseStack stack, MultiBufferSource buffer, int packedLightIn, GoldenRamEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isSheared() &&!entity.isInvisible()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, FLEECE_TEXTURE, stack, buffer, packedLightIn, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1,1,1);
        }
    }
}