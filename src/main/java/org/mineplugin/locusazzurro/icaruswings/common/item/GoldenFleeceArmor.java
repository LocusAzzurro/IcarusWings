package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModArmorMaterial;

public class GoldenFleeceArmor extends ArmorItem implements Equipable {

    private static final ModArmorMaterial material = ModArmorMaterial.GOLDEN_FLEECE;

    public GoldenFleeceArmor(Type type) {
        super(material, type, new Item.Properties().defaultDurability(material.getDurabilityForType(type)));
    }

}
