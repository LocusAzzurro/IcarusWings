package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Wearable;
import org.mineplugin.locusazzurro.icaruswings.data.ModArmorMaterial;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class GoldenFleeceArmor extends ArmorItem implements Wearable {

    private static final ModArmorMaterial material = ModArmorMaterial.GOLDEN_FLEECE;

    public GoldenFleeceArmor(EquipmentSlot pSlot) {
        super(material, pSlot, new Item.Properties().tab(ModGroup.itemGroup).defaultDurability(material.getDurabilityForSlot(pSlot)));
    }

}
