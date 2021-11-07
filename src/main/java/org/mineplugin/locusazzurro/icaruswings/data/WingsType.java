package org.mineplugin.locusazzurro.icaruswings.data;

import java.util.function.Supplier;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

public enum WingsType implements IWingsType{

	FEATHER("feather", 100, () -> {return ItemRegistry.featherBunch.get();},false),
	FEATHER_COLORED("colored_feather", 150, () -> {return ItemRegistry.coloredFeatherBunch.get();},false), 
	FEATHER_GOLDEN("golden_feather", 200, () -> {return ItemRegistry.goldenFeatherBunch.get();},false),
	SYNAPSE_ALPHA("synapse_alpha", 500, () -> {return ItemRegistry.synapseWingsRecharger.get();},true),
	SYNAPSE_BETA("synapse_beta", 450, () -> {return ItemRegistry.synapseWingsRecharger.get();},true),
	SYNAPSE_DELTA("synapse_delta", 400, () -> {return ItemRegistry.synapseWingsRecharger.get();},true),
	SYNAPSE_EPSILON("synapse_epsilon", 420, () -> {return ItemRegistry.synapseWingsRecharger.get();},true),
	SYNAPSE_ZETA("synapse_zeta", 440, () -> {return ItemRegistry.synapseWingsRecharger.get();},true),
	SYNAPSE_THETA("synapse_theta", 500, () -> {return ItemRegistry.synapseWingsRecharger.get();},true),
	PAPER("paper", 10, () -> {return Items.PAPER;},false),
	MAGIC("magic", 450, () -> {return ItemRegistry.magicMembrane.get();},false),
	PHI_STONE("philosopher_stone", 450, () -> {return ItemRegistry.magicMembrane.get();},false);
	
	private final String name;
	private final int durability;
	private final LazyValue<Item> repairItem;
	private final boolean hasReversedTexture;
	private final ResourceLocation texture;
	private final ResourceLocation textureReversed;

	WingsType(String name, int durability, Supplier<Item> repairItem, boolean hasReverse) {
		this.name = name;
		this.durability = durability;
		this.repairItem = new LazyValue<>(repairItem);
		this.hasReversedTexture = hasReverse;
		this.texture = new ResourceLocation(ModData.MOD_ID, "textures/entity/" + name + "_wings.png");
		this.textureReversed = new ResourceLocation(ModData.MOD_ID, "textures/entity/" + name + "_wings_reversed.png");
		
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
	
	public ResourceLocation getTextureReversed() {
		return this.hasReversedTexture ? this.textureReversed : this.texture;
	}
}
