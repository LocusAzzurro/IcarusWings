package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.DamageTypeRegistry;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModDamageTagsGenerator extends DamageTypeTagsProvider {

    public ModDamageTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper fh) {
        super(output, provider, IcarusWings.MOD_ID, fh);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DamageTypeTags.BYPASSES_ARMOR).add(DamageTypeRegistry.TIME_RIFT);
    }
}
