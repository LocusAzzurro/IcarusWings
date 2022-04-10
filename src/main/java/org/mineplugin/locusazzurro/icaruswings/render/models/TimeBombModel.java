package org.mineplugin.locusazzurro.icaruswings.render.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.TimeBombEntity;

@OnlyIn(Dist.CLIENT)
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
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bomb.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
