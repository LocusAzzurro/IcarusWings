package org.mineplugin.locusazzurro.icaruswings.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;
import org.mineplugin.locusazzurro.icaruswings.render.models.GoldenRamFleeceModel;
import org.mineplugin.locusazzurro.icaruswings.render.models.GoldenRamModel;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class GoldenRamFleeceLayer extends RenderLayer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> {

    private static final ResourceLocation FLEECE_TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/golden_ram_fleece.png");
    private final GoldenRamFleeceModel<GoldenRamEntity> model;

    public GoldenRamFleeceLayer(RenderLayerParent<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> renderer, EntityModelSet set) {
        super(renderer);
        this.model = new GoldenRamFleeceModel<>(set.bakeLayer(ModelLayerRegistry.GOLDEN_RAM_FLEECE));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(PoseStack stack, MultiBufferSource buffer, int packedLightIn, GoldenRamEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isSheared() &&!entity.isInvisible()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, FLEECE_TEXTURE, stack, buffer, packedLightIn, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1,1,1);
        }
    }
}
