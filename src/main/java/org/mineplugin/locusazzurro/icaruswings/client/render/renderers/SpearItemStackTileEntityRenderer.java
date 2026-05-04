package org.mineplugin.locusazzurro.icaruswings.client.render.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.SpearModel;
import org.mineplugin.locusazzurro.icaruswings.registry.ModelLayerRegistry;

import java.util.function.Consumer;
public class SpearItemStackTileEntityRenderer implements SpecialModelRenderer<Identifier> {
    private final SpearModel model;

    public SpearItemStackTileEntityRenderer(SpecialModelRenderer.BakingContext context) {
        this.model = new SpearModel(context.entityModelSet().bakeLayer(ModelLayerRegistry.SPEAR));
    }

    @Override
    public @Nullable Identifier extractArgument(ItemStack stack) {
        return SpearRenderer.getTexture(stack);
    }

    @Override
    public void submit(
            @Nullable Identifier texture,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            int overlayCoords,
            boolean hasFoil,
            int outlineColor
    ) {
        Identifier renderTexture = texture == null ? SpearRenderer.FALLBACK : texture;
        submitNodeCollector.submitModelPart(
                this.model.root(),
                poseStack,
                RenderTypes.entitySolid(renderTexture),
                lightCoords,
                overlayCoords,
                null,
                false,
                hasFoil,
                -1,
                null,
                outlineColor
        );
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, output);
    }
    public record Unbaked() implements SpecialModelRenderer.Unbaked<Identifier> {
        public static final Unbaked INSTANCE = new Unbaked();
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public SpecialModelRenderer<Identifier> bake(SpecialModelRenderer.BakingContext context) {
            return new SpearItemStackTileEntityRenderer(context);
        }

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }
}

