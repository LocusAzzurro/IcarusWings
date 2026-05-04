package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.SpearModel;
import org.mineplugin.locusazzurro.icaruswings.client.render.state.SpearRenderState;
import org.mineplugin.locusazzurro.icaruswings.common.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;

import java.util.Map;
public class SpearRenderer extends EntityRenderer<SpearEntity, SpearRenderState> {
    public static final Identifier FALLBACK = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/wooden_spear.png");
    private static final Map<Item, Identifier> TEXTURES = Map.ofEntries(
            Map.entry(ItemRegistry.WOODEN_SPEAR.get(), Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/wooden_spear.png")),
            Map.entry(ItemRegistry.STONE_SPEAR.get(), Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/stone_spear.png")),
            Map.entry(ItemRegistry.IRON_SPEAR.get(), Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/iron_spear.png")),
            Map.entry(ItemRegistry.STEEL_SPEAR.get(), Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/steel_spear.png")),
            Map.entry(ItemRegistry.GOLDEN_SPEAR.get(), Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/golden_spear.png")),
            Map.entry(ItemRegistry.DIAMOND_SPEAR.get(), Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/diamond_spear.png")),
            Map.entry(ItemRegistry.NETHERITE_SPEAR.get(), Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/netherite_spear.png")),
            Map.entry(ItemRegistry.SYNAPSE_ALLOY_SPEAR.get(), Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/synapse_alloy_spear.png"))
    );
    private final SpearModel model;

    public SpearRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SpearModel(context.bakeLayer(ModelLayerRegistry.SPEAR));
    }

    @Override
    public void submit(SpearRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(state.xRot + 90.0F));
        submitNodeCollector.submitModelPart(
                this.model.root(),
                poseStack,
                RenderTypes.entitySolid(state.texture),
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                null,
                false,
                state.hasFoil,
                -1,
                null,
                state.outlineColor
        );
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public SpearRenderState createRenderState() {
        return new SpearRenderState();
    }

    @Override
    public void extractRenderState(SpearEntity entity, SpearRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yRot = entity.getYRot(partialTicks);
        state.xRot = entity.getXRot(partialTicks);
        state.texture = getTexture(entity.getSpearItemData());
        state.hasFoil = entity.isFoil();
    }

    public Identifier getTextureLocation(SpearRenderState state) {
        return state.texture;
    }

    public static Identifier getTexture(ItemStack stack) {
        return getTexture(stack.getItem());
    }

    public static Identifier getTexture(Item item) {
        return TEXTURES.getOrDefault(item, FALLBACK);
    }
}

