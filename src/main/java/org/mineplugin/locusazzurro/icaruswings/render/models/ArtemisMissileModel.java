package org.mineplugin.locusazzurro.icaruswings.render.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.ArtemisMissileEntity;

@OnlyIn(Dist.CLIENT)
public class ArtemisMissileModel extends EntityModel<ArtemisMissileEntity> {
    private final ModelRenderer missile;
    private final ModelRenderer bulgemiddle_r1;
    private final ModelRenderer payload_r1;
    private final ModelRenderer head_r1;

    public ArtemisMissileModel() {
        texWidth = 128;
        texHeight = 64;

        missile = new ModelRenderer(this);
        missile.setPos(0.0F, 22.0F, -9.0F);
        missile.texOffs(63, 0).addBox(-3.0F, -2.0F, 11.0F, 6.0F, 3.0F, 14.0F, 0.0F, false);
        missile.texOffs(63, 19).addBox(-2.0F, -1.0F, 18.0F, 4.0F, 1.0F, 14.0F, 0.0F, false);

        bulgemiddle_r1 = new ModelRenderer(this);
        bulgemiddle_r1.setPos(0.0F, 0.0F, 9.0F);
        missile.addChild(bulgemiddle_r1);
        setRotationAngle(bulgemiddle_r1, 0.0F, -0.7854F, 0.0F);
        bulgemiddle_r1.texOffs(38, 38).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
        bulgemiddle_r1.texOffs(0, 0).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);

        payload_r1 = new ModelRenderer(this);
        payload_r1.setPos(0.0F, 0.0F, -8.0F);
        missile.addChild(payload_r1);
        setRotationAngle(payload_r1, 0.0F, -0.7854F, 0.0F);
        payload_r1.texOffs(0, 38).addBox(-3.0F, -3.0F, -3.0F, 8.0F, 5.0F, 8.0F, 0.0F, false);

        head_r1 = new ModelRenderer(this);
        head_r1.setPos(0.0F, 0.0F, 0.0F);
        missile.addChild(head_r1);
        setRotationAngle(head_r1, -0.0436F, -0.7854F, 0.0F);
        head_r1.texOffs(0, 19).addBox(-6.9792F, -2.0308F, -7.2936F, 14.0F, 3.0F, 14.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(ArtemisMissileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.missile.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
