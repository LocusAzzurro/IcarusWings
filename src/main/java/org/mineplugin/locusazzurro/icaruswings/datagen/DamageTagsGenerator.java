package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.DamageTypeRegistry;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DamageTagsGenerator extends TagsProvider<DamageType> {

    public DamageTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper fh) {
        super(output, Registries.DAMAGE_TYPE, provider, CompletableFuture.completedFuture(TagsProvider.TagLookup.empty()), DataGenerators.MOD_ID, fh);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DamageTypeTags.BYPASSES_ARMOR).add(DamageTypeRegistry.TIME_RIFT);
    }
}
