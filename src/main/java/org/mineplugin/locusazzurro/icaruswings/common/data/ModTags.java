package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

public class ModTags {

    public static final TagKey<Item> COLORED_FEATHERS = createItemTag("colored_feathers");
    public static final TagKey<Item> HERBS = createItemTag("herbs");
    public static final TagKey<Item> CROP_STRINGS = createItemTag("crop_strings");
    public static final TagKey<Item> CROP_GRAINS = createItemTag("crop_grains");
    public static final TagKey<Item> FALLEN_RELICS = createItemTag("fallen_relics");
    public static final TagKey<Item> RESTORED_FALLEN_RELICS = createItemTag("restored_fallen_relics");
    public static final TagKey<Item> WORLD_ESSENCES = createItemTag("world_essences");
    public static final TagKey<Item> WORLD_INGOTS = createItemTag("world_ingots");
    public static final TagKey<Item> SYNAPSE_ARMOR = createItemTag("synapse_armor");
    public static final TagKey<Item> ELYTRA_ENCHANTABLE = createItemTag("enchantable/elytra");

    public static final TagKey<DamageType> IS_COLLISION = createDamageTypeTag("is_collision");//todo populate damage tags

    private static TagKey<Item> createItemTag(String name){
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }

    private static TagKey<DamageType> createDamageTypeTag(String name)
    {
        return TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }


}
