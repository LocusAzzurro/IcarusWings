package org.mineplugin.locusazzurro.icaruswings.render;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class GoldenRamRenderer extends MobRenderer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>>{
    private static final ResourceLocation GOLDEN_RAM_TEXTURE = new ResourceLocation(ModData.MOD_ID, "textures/entity/golden_ram.png");

    public GoldenRamRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager, new GoldenRamModel<>(), 0.7F);
        this.addLayer(new GoldenRamFleeceLayer(this));
    }

    @Override
    @ParametersAreNonnullByDefault
    public ResourceLocation getTextureLocation(GoldenRamEntity entity) {
        return GOLDEN_RAM_TEXTURE;
    }

}
