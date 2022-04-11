package org.mineplugin.locusazzurro.icaruswings.render.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;
import org.mineplugin.locusazzurro.icaruswings.render.layers.GoldenRamFleeceLayer;
import org.mineplugin.locusazzurro.icaruswings.render.models.GoldenRamModel;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class GoldenRamRenderer extends MobRenderer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>>{
    private static final net.minecraft.resources.ResourceLocation GOLDEN_RAM_TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/golden_ram.png");

    public GoldenRamRenderer(Context entityRendererManager) {
        super(entityRendererManager, new GoldenRamModel<>(entityRendererManager.bakeLayer(GoldenRamModel.LAYER_LOCATION)), 0.7F);
        this.addLayer(new GoldenRamFleeceLayer(entityRendererManager.getModelSet(), this));
    }

    @Override
    @ParametersAreNonnullByDefault
    public net.minecraft.resources.ResourceLocation getTextureLocation(GoldenRamEntity entity) {
        return GOLDEN_RAM_TEXTURE;
    }
}
