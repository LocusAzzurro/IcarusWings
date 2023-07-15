package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author DustW
 **/
public class BlockStatesGenerator extends BlockStateProvider {

    public BlockStatesGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DataGenerators.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}