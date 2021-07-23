package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.List;
import java.util.UUID;

import org.mineplugin.locusazzurro.icaruswings.data.Utils;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class SynapseWings extends AbstractWings{
	
	protected final UUID MODIFIER_UUID = Utils.ARMOR_MODIFIER_UUID_PER_SLOT[2];
	
	public SynapseWings(WingsType type) {
		super(type, Rarity.RARE);
	}
	
	protected UUID getModifierUUID() {return MODIFIER_UUID;}
	
	@Override
	public float getXpRepairRatio(ItemStack stackIn) {
		return 0.0f;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType slot) {
		return slot == EquipmentSlotType.CHEST ? this.getModifiers() : super.getDefaultAttributeModifiers(slot);
	}
	
	protected abstract Multimap<Attribute, AttributeModifier> getModifiers();
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TranslationTextComponent("item.locusazzurro_icaruswings." + this.type.getName() + ".tooltip")
				.setStyle(Style.EMPTY.withColor(TextFormatting.GRAY)));
	}
}
