package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;

public class TimeBombModel extends EntityModel<TimeBombEntity> {

    private final ModelRenderer bomb;

    public TimeBombModel() {
        this.bomb = new ModelRenderer(this);

    }

    @Override
    public void setupAnim(TimeBombEntity bomb, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bomb.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
