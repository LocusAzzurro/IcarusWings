package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;

public class ModParticleProvider extends ParticleDescriptionProvider {


    protected ModParticleProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    protected void addDescriptions() {
        sprite(ParticleRegistry.NULLITY.get(), modLoc("nullity"));
        spriteSet(ParticleRegistry.PLASMA_TRAIL.get(), modLoc("plasma_trail"), 12, false);
        spriteSet(ParticleRegistry.ELECTRONIC_BIT.get(), modLoc("electronic_bit"), 10, false);
        sprite(ParticleRegistry.GOLDEN_SPARKLE.get(), modLoc("golden_sparkle"));
        sprite(ParticleRegistry.GOLDEN_SPARKLE_BASE.get(), modLoc("golden_sparkle"));
        sprite(ParticleRegistry.TIME_RIFT_EXPLOSION.get(), modLoc("time_rift_explosion"));
    }

    private ResourceLocation modLoc(String name){
        return ResourceLocation.fromNamespaceAndPath(DataGenerators.MOD_ID, name);
    }

}
