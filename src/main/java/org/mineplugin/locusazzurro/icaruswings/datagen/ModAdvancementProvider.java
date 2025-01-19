package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.awt.image.ComponentSampleModel;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {

    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    static class ModAdvancementGenerator implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {

            AdvancementHolder PAST_ROOT = Advancement.Builder.advancement().display(
                    Items.FEATHER.getDefaultInstance(),
                    Component.translatable("advancements.locusazzurro_icaruswings.past.root.title"),
                    Component.translatable("advancements.locusazzurro_icaruswings.past.root.description"),
                    ResourceLocation.fromNamespaceAndPath(DataGenerators.MOD_ID, "textures/gui/advancements/backgrounds/past.png"),
                    AdvancementType.TASK, true, false, false)
                    .addCriterion("has_feather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FEATHER))
                    .addCriterion("has_honeycomb", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEYCOMB))
                    .requirements(AdvancementRequirements.Strategy.AND).save(consumer, modLoc("past/root").toString());


        }

        private static ResourceLocation modLoc(String path) {
            return ResourceLocation.fromNamespaceAndPath(DataGenerators.MOD_ID, path);
        }

    }
}
