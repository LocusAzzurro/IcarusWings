package org.mineplugin.locusazzurro.icaruswings.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.ModItemTier;
import org.mineplugin.locusazzurro.icaruswings.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.utils.MapUtil;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SpearModel extends EntityModel<SpearEntity> {

    protected static final Map<? extends IItemTier, ResourceLocation> MATERIALS = MapUtil.Builder
            .<IItemTier, ResourceLocation>add(ItemTier.WOOD, new ResourceLocation(ModData.MOD_ID, "textures/entity/wooden_spear.png"))
            .add(ItemTier.STONE, new ResourceLocation(ModData.MOD_ID, "textures/entity/stone_spear.png"))
            .add(ItemTier.IRON, new ResourceLocation(ModData.MOD_ID, "textures/entity/iron_spear.png"))
            .add(ModItemTier.STEEL, new ResourceLocation(ModData.MOD_ID, "textures/entity/steel_spear.png"))
            .add(ItemTier.GOLD, new ResourceLocation(ModData.MOD_ID, "textures/entity/golden_spear.png"))
            .add(ItemTier.DIAMOND, new ResourceLocation(ModData.MOD_ID, "textures/entity/diamond_spear.png"))
            .add(ItemTier.NETHERITE, new ResourceLocation(ModData.MOD_ID, "textures/entity/netherite_spear.png"))
            .add(ModItemTier.SYNAPSE_ALLOY, new ResourceLocation(ModData.MOD_ID, "textures/entity/synapse_alloy_spear.png"));

    protected static final ResourceLocation FALLBACK_SPEAR_TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/wooden_spear.png");

    private final ModelRenderer spear;

    public SpearModel() {
        super(RenderType::entitySolid);
        texWidth = 16;
        texHeight = 32;

        spear = new ModelRenderer(this);
        spear.setPos(0.0F, 18.0F, 0.0F);
        setRotationAngle(spear, 0.0F, 0.0F, 0.0F);
        spear.texOffs(1, 2).addBox(-0.5F, -14.0F, -0.5F, 1.0F, 26.0F, 1.0F, 0.0F, false);
        spear.texOffs(6, 2).addBox(-1.5F, -17.0F, -0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        spear.texOffs(6, 7).addBox(-0.5F, -19.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

    }

    @Override
    public void setupAnim(SpearEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    @Override
    public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.spear.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public static ResourceLocation getTexture(IItemTier tier) {
        return MATERIALS.containsKey(tier) ? MATERIALS.get(tier) : FALLBACK_SPEAR_TEXTURE;
    }

}
