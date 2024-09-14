package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

public class ModTags {

    public static final TagKey<Item> COLORED_FEATHERS = createModItemTag("colored_feathers");
    public static final TagKey<Item> HERBS = createModItemTag("herbs");
    public static final TagKey<Item> CROP_STRINGS = createModItemTag("crop_strings");
    public static final TagKey<Item> CROP_GRAINS = createModItemTag("crop_grains");
    public static final TagKey<Item> FALLEN_RELICS = createModItemTag("fallen_relics");
    public static final TagKey<Item> RESTORED_FALLEN_RELICS = createModItemTag("restored_fallen_relics");
    public static final TagKey<Item> WORLD_ESSENCES = createModItemTag("world_essences");
    public static final TagKey<Item> WORLD_INGOTS = createModItemTag("world_ingots");
    public static final TagKey<Item> SYNAPSE_ARMOR = createModItemTag("synapse_armor");
    public static final TagKey<Item> ELYTRA_ENCHANTABLE = createModItemTag("enchantable/elytra");

    public static final TagKey<Item> C_STEEL_INGOT = createUnifiedItemTag("ingots/steel");

    public static final TagKey<DamageType> IS_COLLISION = createDamageTypeTag("is_collision");//todo populate damage tags

    private static TagKey<Item> createModItemTag(String name){
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }

    private static TagKey<Item> createUnifiedItemTag(String name){
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private static TagKey<DamageType> createDamageTypeTag(String name)
    {
        return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }


}
