package org.mineplugin.locusazzurro.icaruswings.common.block;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import org.jspecify.annotations.Nullable;
import org.joml.Vector4f;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class GreekFireFluidType extends FluidType {
    public static final int TINT_COLOR = 0xFFF79059;
    public static final Identifier FLUID_OVERLAY_TEXTURE = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "block/greek_fire_still");
    public static final Identifier RENDER_OVERLAY_TEXTURE = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/block/greek_fire_overlay.png");

    public GreekFireFluidType() {
        super(FluidType.Properties.create()
                .density(4000)
                .viscosity(7000)
                .lightLevel(15)
                .temperature(1500)
                .canDrown(false)
                .descriptionId("block.locusazzurro_icaruswings.greek_fire")
                .sound(SoundActions.BUCKET_FILL, SoundRegistry.BUCKET_FILL_GREEK_FIRE.get())
                .sound(SoundActions.BUCKET_EMPTY, SoundRegistry.BUCKET_EMPTY_GREEK_FIRE.get()));
    }

    public static IClientFluidTypeExtensions createClientExtensions() {
        return new IClientFluidTypeExtensions() {
            @Override
            public Identifier getRenderOverlayTexture(Minecraft mc) {
                return RENDER_OVERLAY_TEXTURE;
            }

            @Override
            public void modifyFogColor(
                Camera camera,
                float partialTick,
                ClientLevel level,
                int renderDistance,
                float darkenWorldAmount,
                Vector4f fluidFogColor
            ) {
                int color = TINT_COLOR;
                fluidFogColor.set((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F, 1.0F);
            }

            @Override
            public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
                fogData.environmentalStart = -8F;
                fogData.environmentalEnd = Math.min(24F, renderDistance);

//                fogData.renderDistanceStart = fogData.environmentalStart;
//                fogData.renderDistanceEnd = fogData.environmentalEnd;
            }
        };
    }
}
