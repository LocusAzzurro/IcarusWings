package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;

@OnlyIn(Dist.CLIENT)
public class SpearModel extends Model {

    public static final ResourceLocation TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/trident.png");
    private final ModelRenderer pole = new ModelRenderer(32, 32, 0, 6);

    public SpearModel() {
        super(RenderType::entitySolid);
        this.pole.addBox(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F, 0.0F);
        ModelRenderer modelrenderer = new ModelRenderer(32, 32, 4, 0);
        modelrenderer.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F);
        this.pole.addChild(modelrenderer);
        ModelRenderer modelrenderer1 = new ModelRenderer(32, 32, 4, 3);
        modelrenderer1.addBox(-2.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F);
        this.pole.addChild(modelrenderer1);
        ModelRenderer modelrenderer2 = new ModelRenderer(32, 32, 0, 0);
        modelrenderer2.addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
        this.pole.addChild(modelrenderer2);
        ModelRenderer modelrenderer3 = new ModelRenderer(32, 32, 4, 3);
        modelrenderer3.mirror = true;
        modelrenderer3.addBox(1.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F);
        this.pole.addChild(modelrenderer3);
    }

    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.pole.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }

}
