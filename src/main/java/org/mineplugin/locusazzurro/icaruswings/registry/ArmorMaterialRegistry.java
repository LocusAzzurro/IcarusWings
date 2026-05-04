package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

import java.util.EnumMap;
import java.util.Map;

public final class ArmorMaterialRegistry {
    private ArmorMaterialRegistry() {
    }

    public static final ArmorMaterial FEATHER = new ArmorMaterial(
        4,
        partsDefenceValues(1, 2, 3, 1, 3),
        15,
        SoundRegistry.ARMOR_EQUIP_FEATHER,
        0.0f,
        0.0f,
        repairTag("feather_wings"),
        asset("feather")
    );

    public static final ArmorMaterial GOLDEN_FEATHER = new ArmorMaterial(
        7,
        partsDefenceValues(1, 3, 5, 2, 5),
        20,
        SoundRegistry.ARMOR_EQUIP_FEATHER,
        0.5f,
        0.0f,
        repairTag("golden_feather_wings"),
        asset("golden_feather")
    );

    public static final ArmorMaterial BEESWAX = new ArmorMaterial(
        5,
        partsDefenceValues(1, 2, 3, 1, 3),
        10,
        SoundRegistry.ARMOR_EQUIP_BEESWAX,
        0.0f,
        0.0f,
        repairTag("beeswax_armor"),
        asset("beeswax")
    );

    public static final ArmorMaterial LINEN = new ArmorMaterial(
        4,
        partsDefenceValues(1, 2, 2, 1, 2),
        12,
        SoundRegistry.ARMOR_EQUIP_LINEN,
        0.0f,
        0.0f,
        repairTag("linen_armor"),
        asset("linen")
    );

    public static final ArmorMaterial GOLDEN_FLEECE = new ArmorMaterial(
        7,
        partsDefenceValues(1, 3, 5, 2, 4),
        20,
        SoundRegistry.ARMOR_EQUIP_GOLDEN_FLEECE,
        0.5f,
        0.0f,
        repairTag("golden_fleece_armor"),
        asset("golden_fleece")
    );

    public static final ArmorMaterial HERBAL = new ArmorMaterial(
        1,
        partsDefenceValues(1, 1, 1, 1, 1),
        14,
        SoundRegistry.ARMOR_EQUIP_HERBAL,
        0.0f,
        0.0f,
        repairTag("herbal_armor"),
        asset("herbal")
    );

    public static final ArmorMaterial SYNAPSE = new ArmorMaterial(
        40,
        partsDefenceValues(3, 6, 8, 3, 8),
        12,
        SoundRegistry.ARMOR_EQUIP_SYNAPSE,
        4.0f,
        0.1f,
        repairTag("synapse_armor"),
        asset("synapse_tech")
    );

    private static Map<ArmorType, Integer> partsDefenceValues(int boots, int leggings, int chestplate, int helmet, int body) {
        Map<ArmorType, Integer> map = new EnumMap<>(ArmorType.class);
        map.put(ArmorType.BOOTS, boots);
        map.put(ArmorType.LEGGINGS, leggings);
        map.put(ArmorType.CHESTPLATE, chestplate);
        map.put(ArmorType.HELMET, helmet);
        map.put(ArmorType.BODY, body);
        return map;
    }

    private static TagKey<Item> repairTag(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, "repairs/" + name));
    }

    private static ResourceKey<EquipmentAsset> asset(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }
}
