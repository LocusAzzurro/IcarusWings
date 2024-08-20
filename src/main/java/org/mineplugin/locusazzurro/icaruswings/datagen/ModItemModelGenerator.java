package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * @author DustW
 * @author LocusAzzurro
 **/
public class ModItemModelGenerator extends ItemModelProvider {

    public ModItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DataGenerators.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //ForgeRegistries.ITEMS.forEach(item -> {
        //    if (item.getRegistryName().getNamespace().equals(DataGenerators.MOD_ID) && item instanceof BlockItem) {
        //        withExistingParent(item.getRegistryName().getPath(), modLoc("block/" + item.getRegistryName().getPath()));
        //    }
        //});
    }
}