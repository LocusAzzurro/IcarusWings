package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.SpearModel;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModItemTiers;
import org.mineplugin.locusazzurro.icaruswings.common.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.common.item.SpearItem;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.MapUtils;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SpearRenderer extends EntityRenderer<SpearEntity> {

    private final SpearModel<SpearEntity> model;

    public SpearRenderer(Context context) {
        super(context);
        model = new SpearModel<>(context.bakeLayer(ModelLayerRegistry.SPEAR));
    }

    @Override
    public void render(SpearEntity spearEntity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, spearEntity.yRotO, spearEntity.getYRot()) - 90.0F));
        stack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, spearEntity.xRotO, spearEntity.getXRot()) + 90.0F));
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(spearEntity)), false, spearEntity.isFoil());
        this.model.renderToBuffer(stack, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 0xff_ff_ff_ff);
        stack.popPose();
        super.render(spearEntity, entityYaw, partialTicks, stack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(SpearEntity entity) {
        Tier tier = ((SpearItem)entity.getSpearItemData().getItem()).getTier();
        return getTexture(tier);
    }

    protected static final Map<? extends Tier, ResourceLocation> MATERIALS = MapUtils.Builder
            .<Tier, ResourceLocation>add(Tiers.WOOD, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/wooden_spear.png"))
            .add(Tiers.STONE, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/stone_spear.png"))
            .add(Tiers.IRON, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/iron_spear.png"))
            .add(ModItemTiers.STEEL, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/steel_spear.png"))
            .add(Tiers.GOLD, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/golden_spear.png"))
            .add(Tiers.DIAMOND, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/diamond_spear.png"))
            .add(Tiers.NETHERITE, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/netherite_spear.png"))
            .add(ModItemTiers.SYNAPSE_ALLOY, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/synapse_alloy_spear.png"));

    protected static final ResourceLocation FALLBACK_SPEAR_TEXTURE = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/wooden_spear.png");

    public static ResourceLocation getTexture(Tier tier) {
        return MATERIALS.getOrDefault(tier, FALLBACK_SPEAR_TEXTURE);
    }
}
