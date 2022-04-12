package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author DustW
 **/
public class BlockStatesGenerator extends BlockStateProvider {

    public BlockStatesGenerator(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, DataGenerators.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}