package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;
import org.mineplugin.locusazzurro.icaruswings.registry.ParticleRegistry;

public class ModParticleProvider extends ParticleDescriptionProvider {


    protected ModParticleProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(ParticleRegistry.NULLITY.get(), modLoc("nullity"));
        spriteSet(ParticleRegistry.PLASMA_TRAIL.get(), modLoc("plasma_trail"), 12, false);
        spriteSet(ParticleRegistry.ELECTRONIC_BIT.get(), modLoc("electronic_bit"), 10, false);
        spriteSet(ParticleRegistry.GOLDEN_SPARKLE.get(), modLoc("golden_sparkle"));
        spriteSet(ParticleRegistry.GOLDEN_SPARKLE_BASE.get(), modLoc("golden_sparkle"));
        spriteSet(ParticleRegistry.TIME_RIFT_EXPLOSION.get(), modLoc("time_rift_explosion"));
    }

    private Identifier modLoc(String name){
        return Identifier.fromNamespaceAndPath(DataGenerators.MOD_ID, name);
    }

}
