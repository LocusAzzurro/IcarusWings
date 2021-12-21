package org.mineplugin.locusazzurro.icaruswings.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

@OnlyIn(Dist.CLIENT)
public class GoldenRamRenderer extends MobRenderer<GoldenRamEntity, GoldenRamModel<GoldenRamEntity>>{
    private static final ResourceLocation RAM = new ResourceLocation("textures/entity/ram.png");//texture to be added
    private GoldenRamEntity p_110775_1_;

    public GoldenRamRenderer(EntityRendererManager p_i47195_1_) {
        super(p_i47195_1_, new GoldenRamModel<>(), 0.7F);
        //this.addLayer(new SheepWoolLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GoldenRamEntity p_110775_1_) {
        return RAM;
    }

}
