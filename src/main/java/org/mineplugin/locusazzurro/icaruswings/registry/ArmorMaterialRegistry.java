package org.mineplugin.locusazzurro.icaruswings.registry;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ArmorMaterialRegistry {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, IcarusWings.MOD_ID);

    public static final Holder<ArmorMaterial> FEATHER = ARMOR_MATERIALS.register("feather", () -> new ArmorMaterial(
            partsDefenceValues(1,2,3,1,3), 15, SoundRegistry.ARMOR_EQUIP_FEATHER,
            () -> Ingredient.of(ItemRegistry.FEATHER_BUNCH.get()), layerByName("feather"),0.0f, 0.0f)); //durability 4

    public static final Holder<ArmorMaterial> GOLDEN_FEATHER = ARMOR_MATERIALS.register("golden_feather", () -> new ArmorMaterial(
            partsDefenceValues(1,3,5,2,5), 20, SoundRegistry.ARMOR_EQUIP_FEATHER,
            () -> Ingredient.of(ItemRegistry.GOLDEN_FEATHER_BUNCH.get()), layerByName("golden_feather"),0.5f, 0.0f)); //durability 7

    public static final Holder<ArmorMaterial> BEESWAX = ARMOR_MATERIALS.register("beeswax", () -> new ArmorMaterial(
            partsDefenceValues(1,2,3,1,3), 10, SoundRegistry.ARMOR_EQUIP_BEESWAX,
            () -> Ingredient.of(ItemRegistry.REFINED_BEESWAX_BAR.get()), layerByName("beeswax"),0.0f, 0.0f)); //durability 5

    public static final Holder<ArmorMaterial> LINEN = ARMOR_MATERIALS.register("linen", () -> new ArmorMaterial(
            partsDefenceValues(1,2,2,1,2), 12, SoundRegistry.ARMOR_EQUIP_LINEN,
            () -> Ingredient.of(ItemRegistry.LINEN.get()), layerByName("linen"),0.0f, 0.0f)); //durability 4

    public static final Holder<ArmorMaterial> GOLDEN_FLEECE = ARMOR_MATERIALS.register("golden_fleece", () -> new ArmorMaterial(
            partsDefenceValues(1,3,5,2,4), 20, SoundRegistry.ARMOR_EQUIP_GOLDEN_FLEECE,
            () -> Ingredient.of(ItemRegistry.GOLDEN_FLEECE.get()), layerByName("golden_fleece"),0.5f, 0.0f)); //durability 7

    public static final Holder<ArmorMaterial> HERBAL = ARMOR_MATERIALS.register("herbal", () -> new ArmorMaterial(
            partsDefenceValues(1,1,1,1,1), 14, SoundRegistry.ARMOR_EQUIP_HERBAL,
            () -> Ingredient.of(ItemRegistry.HERB_BUNCH.get()), layerByName("herbal"),0.0f, 0.0f)); //durability 1

    public static final Holder<ArmorMaterial> SYNAPSE = ARMOR_MATERIALS.register("synapse_tech", () -> new ArmorMaterial(
            partsDefenceValues(3,6,8,3,8), 12, SoundRegistry.ARMOR_EQUIP_SYNAPSE,
            () -> Ingredient.of(ItemRegistry.SYNAPSE_REPAIR_KIT.get()), layerByName("synapse_tech"),4.0f, 0.1f)); //durability 40

    private static Map<ArmorItem.Type, Integer> partsDefenceValues(int boots, int leggings, int chestplate, int helmet, int body){
        return Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, boots);
            map.put(ArmorItem.Type.LEGGINGS, leggings);
            map.put(ArmorItem.Type.CHESTPLATE, chestplate);
            map.put(ArmorItem.Type.HELMET, helmet);
            map.put(ArmorItem.Type.BODY, body);
        });
    }

    private static List<ArmorMaterial.Layer> layerByName(String name){
        return List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name)));
    }


}
