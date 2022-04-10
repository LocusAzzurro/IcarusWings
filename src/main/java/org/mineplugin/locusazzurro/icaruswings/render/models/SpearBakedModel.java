package org.mineplugin.locusazzurro.icaruswings.render.models;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class SpearBakedModel implements BakedModel {

    private BakedModel existingModel;

    public SpearBakedModel(BakedModel existingModel) {
        this.existingModel = existingModel;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, Random rand) {
        return this.existingModel.getQuads(state, direction, rand);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.existingModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return this.existingModel.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return this.existingModel.getParticleIcon();
    }

    @Override
    public ItemTransforms getTransforms() {
        return this.existingModel.getTransforms();
    }

    @Override
    public BakedModel handlePerspective(ItemTransforms.TransformType cameraTransformType, PoseStack mat) {
        if (cameraTransformType == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND ||
                cameraTransformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND ||
                cameraTransformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND ||
                cameraTransformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND) {
            return this;
        }
        return this.existingModel.handlePerspective(cameraTransformType, mat);
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.existingModel.getOverrides();
    }
}
