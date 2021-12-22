package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.SpearEntity;

@OnlyIn(Dist.CLIENT)
public class SpearModel extends EntityModel<SpearEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/trident.png");
    private final ModelRenderer bone;

    public SpearModel() {
        super(RenderType::entitySolid);
        texWidth = 16;
        texHeight = 16;

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, 18.0F, 0.0F);
        setRotationAngle(bone, 0.0F, 0.0F, 0.0F);
        bone.texOffs(0, 0).addBox(-0.5F, -14.0F, -0.5F, 1.0F, 26.0F, 1.0F, 0.0F, false);
        bone.texOffs(0, 0).addBox(-1.5F, -17.0F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        bone.texOffs(0, 0).addBox(-0.5F, -19.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

    }

    @Override
    public void setupAnim(SpearEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    @Override
    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.bone.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

}
