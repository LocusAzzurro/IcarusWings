package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

import java.util.List;

public class FallenRelic extends Item{
	
	protected RelicType relicType;
	private final boolean isRestored;
	private final boolean isUpgraded = false;
	
	public FallenRelic(RelicType type, boolean restored){
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.UNCOMMON));
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
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		RelicType type = ((FallenRelic)itemstack.getItem()).getType();
		list.add(Component.translatable("item.locusazzurro_icaruswings.fallen_relic_"+ type +".tooltip")
				.setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
	}

	@Override
	public String getDescriptionId(){
		return "item." + ModData.MOD_ID + "." + (this.getType().isUpgraded() ? "upgraded_" : (this.isRestored ? "restored_" : "")) + "fallen_relic";
	}
	
	public enum RelicType implements StringRepresentable {
		CORE("core", false),
		SECOND_GEN_CORE("second_gen_core", true),
		INTERFACE("interface", false),
		OFFENSIVE("offensive", false),
		DEFENSIVE("defensive", false),
		PROPULSION("propulsion", false);
		
		private final String name;
		private final boolean upgraded;
		RelicType (String name, boolean upgraded) {
			this.name = name;
			this.upgraded = upgraded;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public String getSerializedName() {
			return this.name;
		}

		public boolean isUpgraded(){
			return this.upgraded;
		}

	}

}
