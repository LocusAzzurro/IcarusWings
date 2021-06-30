package org.mineplugin.locusazzurro.icaruswings.data;

import java.util.function.Supplier;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;

public enum WingsMaterial {

	FEATHER("feather", 100, () -> {return ItemRegistry.featherBunch.get();},"textures/entity/feather_wings.png"),
	FEATHER_COLORED("colored_feather", 150, () -> {return ItemRegistry.coloredFeatherBunch.get();},"textures/entity/colored_feather_wings.png"), 
	FEATHER_GOLDEN("golden_feather", 200, () -> {return ItemRegistry.goldenFeatherBunch.get();},"textures/entity/golden_feather_wings.png"),
	IKAROS("ikaros", 400, () -> {return ItemRegistry.synapseWingsRecharger.get();},"textures/entity/ikaros_wings.png"),
	PAPER("paper", 10, () -> {return Items.PAPER;},"textures/entity/paper_wings.png");
	
	private final String name;
	private final int durability;
	private final LazyValue<Item> repairItem;
	private final ResourceLocation texture;

	private WingsMaterial(String name, int durability, Supplier<Item> repairItem, String textureDir) {
		this.name = name;
		this.durability = durability;
		this.repairItem = new LazyValue<>(repairItem);
		this.texture = new ResourceLocation(Utils.MOD_ID, textureDir);
		
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public int getDurability() {
		return this.durability;
	}
	
	public Item getRepairItem() {
		return this.repairItem.get();
	}
	
	public ResourceLocation getTexture() {
		return this.texture;
	}
}
