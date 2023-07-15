package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.data.ModTags;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.concurrent.CompletableFuture;

/**
 * @author DustW
 **/
public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, TagsProvider<Block> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider.contentsGetter(), DataGenerators.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {

        tag(ModTags.COLORED_FEATHERS)
                .add(ItemRegistry.redFeather.get())
                .add(ItemRegistry.blueFeather.get())
                .add(ItemRegistry.greenFeather.get())
                .add(ItemRegistry.cyanFeather.get())
                .add(ItemRegistry.grayFeather.get());
        tag(ModTags.HERBS)
                .addTag(ItemTags.SMALL_FLOWERS)
                .remove(Items.WITHER_ROSE)
                .addTag(ItemTags.TALL_FLOWERS)
                .addTag(ItemTags.SAPLINGS)
                .add(Items.FERN)
                .add(Items.SEA_PICKLE)
                .add(Items.SWEET_BERRIES);
        tag(ModTags.CROP_STRINGS)
                .add(ItemRegistry.wheatString.get())
                .add(ItemRegistry.linenString.get());
        tag(ModTags.CROP_GRAINS)
                .add(ItemRegistry.wheatGrains.get())
                .add(ItemRegistry.flaxSeeds.get());
        tag(ModTags.WORLD_ESSENCES)
                .add(ItemRegistry.zephirEssence.get())
                .add(ItemRegistry.netherEssence.get())
                .add(ItemRegistry.voidEssence.get());
        tag(ModTags.WORLD_INGOTS)
                .add(ItemRegistry.steelIngot.get())
                .add(ItemRegistry.magmaIngot.get())
                .add(ItemRegistry.purpurIngot.get());
        tag(ModTags.FALLEN_RELICS)
                .add(ItemRegistry.fallenRelicCore.get())
                .add(ItemRegistry.fallenRelicOffensive.get())
                .add(ItemRegistry.fallenRelicDefensive.get())
                .add(ItemRegistry.fallenRelicInterface.get())
                .add(ItemRegistry.fallenRelicPropulsion.get());
        tag(ModTags.RESTORED_FALLEN_RELICS)
                .add(ItemRegistry.restoredFallenRelicCore.get())
                .add(ItemRegistry.restoredFallenRelicOffensive.get())
                .add(ItemRegistry.restoredFallenRelicDefensive.get())
                .add(ItemRegistry.restoredFallenRelicInterface.get())
                .add(ItemRegistry.restoredFallenRelicPropulsion.get());
        tag(ModTags.SYNAPSE_ARMOR)
                .add(ItemRegistry.synapseHelmet.get())
                .add(ItemRegistry.synapseChestplate.get())
                .add(ItemRegistry.synapseLeggings.get())
                .add(ItemRegistry.synapseBoots.get());
    }

    @Override
    public String getName() {
        return DataGenerators.MOD_NAME + "Item Tags";
    }
}
