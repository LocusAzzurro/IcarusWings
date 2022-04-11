package org.mineplugin.locusazzurro.icaruswings.mixin;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.mineplugin.locusazzurro.icaruswings.render.layers.WingsLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author DustW
 **/
@Mixin(LivingEntityRenderer.class)
public abstract class MixinEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> {
    @Shadow
    public abstract boolean addLayer(RenderLayer<T, M> pLayer);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void newRenderer(EntityRendererProvider.Context context, EntityModel p_174290_, float p_174291_, CallbackInfo ci) {
        addLayer(new WingsLayer<>((LivingEntityRenderer<T, M>) (Object) this, context.getModelSet()));
    }
}
