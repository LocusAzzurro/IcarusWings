package org.mineplugin.locusazzurro.icaruswings.items;

import java.util.List;

import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class FallenRelic extends Item{
	
	protected RelicType relicType;
	private boolean isRestored;
	
	public FallenRelic(RelicType type, boolean restored){
		super(new Item.Properties().tab(ModGroup.itemGroup).stacksTo(1).fireResistant());
		this.relicType = type;
		this.isRestored = restored;
	}
	
	public FallenRelic(RelicType type) {
		this(type, false);
	}
	
	public boolean isRestored() {
		return this.isRestored;
	}
	
	public RelicType getType() {
		return this.relicType;
	}
	
	@Override
	public boolean isFoil(ItemStack stackIn) {
		return this.isRestored;
	}
	
	@Override
	public void appendHoverText(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		RelicType type = ((FallenRelic)itemstack.getItem()).getType();
		list.add(new TranslationTextComponent("item.locusazzurro_icaruswings.fallen_relic_"+ type +".tooltip")
				.setStyle(Style.EMPTY.withColor(TextFormatting.GRAY)));
	}
	
	public static enum RelicType implements IStringSerializable{
		CORE("core"),
		INTERFACE("interface"),
		OFFENSIVE("offensive"),
		DEFENSIVE("defensive"),
		PROPULSION("propulsion");
		
		private String name;
		private RelicType (String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public String getSerializedName() {
			return this.name;
		}
	}

}
