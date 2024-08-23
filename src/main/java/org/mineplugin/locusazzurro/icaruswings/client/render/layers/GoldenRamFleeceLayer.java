package org.mineplugin.locusazzurro.icaruswings.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.GoldenRamFleeceModel;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.GoldenRamModel;
import org.mineplugin.locusazzurro.icaruswings.common.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class GoldenRamFleeceLayer extends RenderLayer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> {

    private static final ResourceLocation FLEECE_TEXTURE = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/golden_ram_fleece.png");
    private final GoldenRamFleeceModel<GoldenRamEntity> model;

    public GoldenRamFleeceLayer(RenderLayerParent<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> renderer, EntityModelSet set) {
        super(renderer);
        this.model = new GoldenRamFleeceModel<>(set.bakeLayer(ModelLayerRegistry.GOLDEN_RAM_FLEECE));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(PoseStack stack, MultiBufferSource buffer, int packedLightIn, GoldenRamEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isSheared() &&!entity.isInvisible()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, FLEECE_TEXTURE, stack, buffer, packedLightIn, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 0xff_ff_ff_ff);
        }
    }
}
