package org.mineplugin.locusazzurro.icaruswings.datagen;

import com.google.common.collect.Streams;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.SlotRange;
import net.minecraft.world.inventory.SlotRanges;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.awt.image.ComponentSampleModel;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ModAdvancementProvider extends AdvancementProvider {

    private static final String ROOT = "root";

    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    static class ModAdvancementGenerator implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {

            AdvancementHolder PAST_ROOT = Advancement.Builder.advancement()
                    .display(makeDisplayInfo(Items.FEATHER, Category.PAST, ROOT, AdvancementType.TASK, true, false, false))
                    .addCriterion("has_feather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FEATHER))
                    .addCriterion("has_honeycomb", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HONEYCOMB))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .save(consumer, modLoc("past/root").toString());
            AdvancementHolder OBTAIN_BEESWAX = Advancement.Builder.advancement()
                    .parent(PAST_ROOT)
                    .display(makeDisplayInfo(ItemRegistry.BEESWAX.get(), Category.PAST, "obtain_beeswax", AdvancementType.TASK, true, true, false))
                    .addCriterion("has_beeswax", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.BEESWAX.get()))
                    .save(consumer, modLoc("past/obtain_beeswax").toString());
            AdvancementHolder ICARUS_FLIGHT = Advancement.Builder.advancement()
                    .parent(OBTAIN_BEESWAX)
                    .display(makeDisplayInfo(ItemRegistry.FEATHER_WINGS.get(), Category.PAST, "icarus_flight", AdvancementType.GOAL, true, true, false))
                    .addCriterion("has_wings", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.FEATHER_WINGS.get()))
                    .save(consumer, modLoc("past/icarus_flight").toString());
            AdvancementHolder REFINE_BEESWAX = Advancement.Builder.advancement()
                    .parent(OBTAIN_BEESWAX)
                    .display(makeDisplayInfo(ItemRegistry.REFINED_BEESWAX_BAR.get(), Category.PAST, "refine_beeswax", AdvancementType.TASK, true, true, false))
                    .addCriterion("has_refined_beeswax", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.REFINED_BEESWAX_BAR.get()))
                    .save(consumer, modLoc("past/refine_beeswax").toString());
            AdvancementHolder LINEN_CLOTH = Advancement.Builder.advancement()
                    .parent(PAST_ROOT)
                    .display(makeDisplayInfo(ItemRegistry.LINEN_CLOAK.get(), Category.PAST, "linen_cloth", AdvancementType.TASK, true, true, false))
                    .addCriterion("has_linen_cloak", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.LINEN_CLOAK.get()))
                    .addCriterion("has_linen_undergarment", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.LINEN_UNDERGARMENT.get()))
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .save(consumer, modLoc("past/linen_cloth").toString());
            AdvancementHolder WINGS_FALLING = Advancement.Builder.advancement()
                    .parent(ICARUS_FLIGHT)
                    .display(makeDisplayInfo(Items.PEONY, Category.PAST, "wings_falling", AdvancementType.CHALLENGE, true, true, true))
                    .addCriterion("broke_wings", ItemDurabilityTrigger.TriggerInstance.changedDurability(
                            Optional.of(ContextAwarePredicate.create(
                                    hasItemInInventoryCondition(Items.OXEYE_DAISY),
                                    hasItemInInventoryCondition(Items.LILY_OF_THE_VALLEY),
                                    hasItemInInventoryCondition(Items.PEONY)
                            )),
                            Optional.of(ItemPredicate.Builder.item().of(ItemRegistry.COLORED_FEATHER_WINGS.get()).build()),
                            MinMaxBounds.Ints.atMost(0)))
                    .rewards(AdvancementRewards.Builder.experience(50))
                    .save(consumer, modLoc("past/wings_falling").toString());



            AdvancementHolder ALL_COLORED_FEATHERS = Advancement.Builder.advancement()
                    .parent(PAST_ROOT)
                    .display(makeDisplayInfo(ItemRegistry.RED_FEATHER.get(), Category.PAST, "all_colored_feathers", AdvancementType.CHALLENGE, true, true, false))
                    .addCriterion("has_red_feather", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.RED_FEATHER.get()))
                    .addCriterion("has_blue_feather", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.BLUE_FEATHER.get()))
                    .addCriterion("has_green_feather", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.GREEN_FEATHER.get()))
                    .addCriterion("has_cyan_feather", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.CYAN_FEATHER.get()))
                    .addCriterion("has_gray_feather", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.GRAY_FEATHER.get()))
                    .requirements(AdvancementRequirements.Strategy.AND)
                    .rewards(AdvancementRewards.Builder.experience(50))
                    .save(consumer, modLoc("past/all_colored_feathers").toString());

        }

        private static DisplayInfo makeDisplayInfo(ItemLike display, Category category, String name, AdvancementType type, boolean showToast, boolean announceChat, boolean hidden){
            Optional<ResourceLocation> bg = name.equals(ROOT) ? Optional.of(ResourceLocation.fromNamespaceAndPath(DataGenerators.MOD_ID, "textures/gui/advancements/backgrounds/" + category + ".png")) : Optional.empty();
            return new DisplayInfo(display.asItem().getDefaultInstance(),
                    Component.translatable("advancements.locusazzurro_icaruswings." + category + "." + name + ".title"),
                    Component.translatable("advancements.locusazzurro_icaruswings." + category + "." + name + ".description"),
                    bg, type, showToast, announceChat, hidden);
        }

        @SuppressWarnings("ConstantConditions")
        private static LootItemCondition hasItemInInventoryCondition(ItemLike item){
            return AnyOfCondition.anyOf(
                    LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                            EntityPredicate.Builder.entity().slots(new SlotsPredicate(Map.of(SlotRanges.nameToIds("inventory.*"),
                                    ItemPredicate.Builder.item().of(item).build()))).build()),
                    LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                            EntityPredicate.Builder.entity().slots(new SlotsPredicate(Map.of(SlotRanges.nameToIds("hotbar.*"),
                                    ItemPredicate.Builder.item().of(item).build()))).build()),
                    LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                            EntityPredicate.Builder.entity().slots(new SlotsPredicate(Map.of(SlotRanges.nameToIds("weapon.*"),
                                    ItemPredicate.Builder.item().of(item).build()))).build())
            ).build();
        }


        private static ResourceLocation modLoc(String path) {
            return ResourceLocation.fromNamespaceAndPath(DataGenerators.MOD_ID, path);
        }

        private enum Category implements Serializable {
            PAST, FUTURE,;

            @Override
            public String toString() {
                return this.name().toLowerCase();
            }
        }


    }
}
