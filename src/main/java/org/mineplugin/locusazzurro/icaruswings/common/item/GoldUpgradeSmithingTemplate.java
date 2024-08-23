package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

import java.util.List;

public class GoldUpgradeSmithingTemplate extends SmithingTemplateItem {

    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component GOLD_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "smithing_template.gold_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component GOLD_UPGRADE_INGREDIENT = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "smithing_template.gold_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component GOLD_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "gold_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component GOLD_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "smithing_template.gold_upgrade.base_slot_description")));
    private static final Component GOLD_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "smithing_template.gold_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");
    private static final ResourceLocation EMPTY_SLOT_STRING = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "item/empty_slot_string");
    private static final ResourceLocation EMPTY_SLOT_FEATHER = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "item/empty_slot_feather");

    public GoldUpgradeSmithingTemplate() {
        super(GOLD_UPGRADE_APPLIES_TO, GOLD_UPGRADE_INGREDIENT, GOLD_UPGRADE,
                GOLD_UPGRADE_BASE_SLOT_DESCRIPTION, GOLD_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                List.of(EMPTY_SLOT_STRING, EMPTY_SLOT_FEATHER), List.of(EMPTY_SLOT_INGOT));
    }
}
