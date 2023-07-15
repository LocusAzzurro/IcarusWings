package org.mineplugin.locusazzurro.icaruswings.blocks;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import org.joml.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.Consumer;

public class GreekFireFluidType extends FluidType {

    public GreekFireFluidType() {
        super(FluidType.Properties.create()
                .density(4000).viscosity(7000).lightLevel(15).temperature(1500).canDrown(false)
                .descriptionId("block.locusazzurro_icaruswings.greek_fire")
                .sound(SoundActions.BUCKET_FILL, SoundRegistry.bucketFillGreekFire.get())
                .sound(SoundActions.BUCKET_EMPTY, SoundRegistry.bucketEmptyGreekFire.get()));
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture()
            {
                return GreekFireFluid.GREEK_FIRE_STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture()
            {
                return GreekFireFluid.GREEK_FIRE_FLOWING;
            }

            @Override
            public ResourceLocation getOverlayTexture()
            {
                return new ResourceLocation(ModData.MOD_ID, "block/greek_fire_still");
            }

            @Override
            public ResourceLocation getRenderOverlayTexture(Minecraft mc)
            {
                return new ResourceLocation(ModData.MOD_ID, "textures/block/greek_fire_overlay.png");
            }

            @Override
            public int getTintColor()
            {
                return 0xfff79059;
            }

            @Override
            public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor)
            {
                int color = this.getTintColor();
                return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape)
            {
                nearDistance = -8F;
                farDistance = 24F;

                if (farDistance > renderDistance)
                {
                    farDistance = renderDistance;
                    shape = FogShape.CYLINDER;
                }

                RenderSystem.setShaderFogStart(nearDistance);
                RenderSystem.setShaderFogEnd(farDistance);
                RenderSystem.setShaderFogShape(shape);
            }

        });
    }
}
