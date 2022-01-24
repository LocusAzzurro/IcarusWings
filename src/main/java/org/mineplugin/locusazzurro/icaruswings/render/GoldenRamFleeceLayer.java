package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.text.html.parser.Entity;

public class GoldenRamFleeceLayer extends LayerRenderer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> {

    private static final ResourceLocation FLEECE_TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/golden_ram_fleece.png");
    private final GoldenRamFleeceModel<GoldenRamEntity> model = new GoldenRamFleeceModel<>();

    public GoldenRamFleeceLayer(IEntityRenderer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>> renderer) {
        super(renderer);
    }

    @ParametersAreNonnullByDefault
    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int packedLightIn, GoldenRamEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isSheared() &&!entity.isInvisible()) {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, FLEECE_TEXTURE, stack, buffer, packedLightIn, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1,1,1);
        }
    }
}
