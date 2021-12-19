package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;

public class TimeBombModel extends EntityModel<TimeBombEntity> {

    private final ModelRenderer bomb;

    public TimeBombModel() {
        texWidth = 16;
        texHeight = 8;

        bomb = new ModelRenderer(this);
        bomb.setPos(0.0F, 24.0F, 0.0F);
        bomb.texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

    }

    @Override
    public void setupAnim(TimeBombEntity bomb, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bomb.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
