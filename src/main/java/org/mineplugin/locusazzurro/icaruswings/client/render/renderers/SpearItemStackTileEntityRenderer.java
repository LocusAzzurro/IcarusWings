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
    private final PoseMode poseMode;

    public SpearItemStackTileEntityRenderer(SpecialModelRenderer.BakingContext context, PoseMode poseMode) {
        this.model = new SpearModel(context.entityModelSet().bakeLayer(ModelLayerRegistry.SPEAR));
        this.poseMode = poseMode;
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
        poseStack.pushPose();
        this.applyPose(poseStack);
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
        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.applyPose(poseStack);
        this.model.root().getExtentsForGui(poseStack, output);
    }

    private void applyPose(PoseStack poseStack) {
        switch (this.poseMode) {
            case FIRST_PERSON -> {
                poseStack.translate(0.5F, 1.4F, 0.6F);
                poseStack.scale(1.0F, -1.0F, -1.0F);
            }
            case THIRD_PERSON -> {
                poseStack.translate(0.5F, 1.5F, 0.6F);
                poseStack.scale(1.0F, -1.0F, -1.0F);
            }
            case THIRD_PERSON_THROWING -> {
                poseStack.translate(0.5F, 1.5F, 0.6F);
                poseStack.scale(1.0F, -1.0F, -1.0F);
                poseStack.scale(1.0F, -1.0F, -1.0F);
                poseStack.translate(0.0F, -2.0F, 0.0F);
            }
        }
    }

    private enum PoseMode {
        FIRST_PERSON,
        THIRD_PERSON,
        THIRD_PERSON_THROWING
    }

    public record FirstPersonUnbaked() implements SpecialModelRenderer.Unbaked<Identifier> {
        public static final FirstPersonUnbaked INSTANCE = new FirstPersonUnbaked();
        public static final MapCodec<FirstPersonUnbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public SpecialModelRenderer<Identifier> bake(SpecialModelRenderer.BakingContext context) {
            return new SpearItemStackTileEntityRenderer(context, PoseMode.FIRST_PERSON);
        }

        @Override
        public MapCodec<FirstPersonUnbaked> type() {
            return MAP_CODEC;
        }
    }

    public record ThirdPersonUnbaked() implements SpecialModelRenderer.Unbaked<Identifier> {
        public static final ThirdPersonUnbaked INSTANCE = new ThirdPersonUnbaked();
        public static final MapCodec<ThirdPersonUnbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public SpecialModelRenderer<Identifier> bake(SpecialModelRenderer.BakingContext context) {
            return new SpearItemStackTileEntityRenderer(context, PoseMode.THIRD_PERSON);
        }

        @Override
        public MapCodec<ThirdPersonUnbaked> type() {
            return MAP_CODEC;
        }
    }

    public record ThirdPersonThrowingUnbaked() implements SpecialModelRenderer.Unbaked<Identifier> {
        public static final ThirdPersonThrowingUnbaked INSTANCE = new ThirdPersonThrowingUnbaked();
        public static final MapCodec<ThirdPersonThrowingUnbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public SpecialModelRenderer<Identifier> bake(SpecialModelRenderer.BakingContext context) {
            return new SpearItemStackTileEntityRenderer(context, PoseMode.THIRD_PERSON_THROWING);
        }

        @Override
        public MapCodec<ThirdPersonThrowingUnbaked> type() {
            return MAP_CODEC;
        }
    }
}
