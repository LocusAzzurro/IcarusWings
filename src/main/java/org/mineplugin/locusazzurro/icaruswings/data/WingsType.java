package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.util.Lazy;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.Supplier;

public enum WingsType implements IWingsType{

	FEATHER("feather", 100, ItemRegistry.featherBunch,false, SoundRegistry.wingsEquipFeather),
	FEATHER_COLORED("colored_feather", 150, ItemRegistry.coloredFeatherBunch,false, SoundRegistry.wingsEquipFeather),
	FEATHER_GOLDEN("golden_feather", 200, ItemRegistry.goldenFeatherBunch,false, SoundRegistry.wingsEquipFeather),
	SYNAPSE_ALPHA("synapse_alpha", 500, ItemRegistry.synapseWingsRecharger,true, SoundRegistry.wingsEquipSynapse),
	SYNAPSE_BETA("synapse_beta", 450, ItemRegistry.synapseWingsRecharger,true, SoundRegistry.wingsEquipSynapse),
	SYNAPSE_DELTA("synapse_delta", 400, ItemRegistry.synapseWingsRecharger,true, SoundRegistry.wingsEquipSynapse),
	SYNAPSE_EPSILON("synapse_epsilon", 420, ItemRegistry.synapseWingsRecharger,true, SoundRegistry.wingsEquipSynapse),
	SYNAPSE_ZETA("synapse_zeta", 440, ItemRegistry.synapseWingsRecharger,true, SoundRegistry.wingsEquipSynapse),
	SYNAPSE_THETA("synapse_theta", 500, ItemRegistry.synapseWingsRecharger,true, SoundRegistry.wingsEquipSynapse),
	PAPER("paper", 10, () -> Items.PAPER,false, SoundRegistry.wingsEquipPaper),
	MAGIC("magic", 450, ItemRegistry.magicMembrane,false, SoundRegistry.wingsEquipMagic),
	PHI_STONE("philosopher_stone", 450, ItemRegistry.magicMembrane,false, SoundRegistry.wingsEquipMagic);
	
	private final String name;
	private final int durability;
	private final Lazy<Item> repairItem;
	private final boolean hasReversedTexture;
	private final ResourceLocation texture;
	private final ResourceLocation textureReversed;
	private final Lazy<SoundEvent> equipSound;

	WingsType(String name, int durability, Supplier<Item> repairItem, boolean hasReverse, Supplier<net.minecraft.sounds.SoundEvent> equipSound) {
		this.name = name;
		this.durability = durability;
		this.repairItem = Lazy.of(repairItem);
		this.hasReversedTexture = hasReverse;
		this.texture = new ResourceLocation(ModData.MOD_ID, "textures/entity/" + name + "_wings.png");
		this.textureReversed = new ResourceLocation(ModData.MOD_ID, "textures/entity/" + name + "_wings_reversed.png");
		this.equipSound = Lazy.of(equipSound);
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int getDurability() {
		return this.durability;
	}

	@Override
	public Item getRepairItem() {
		return this.repairItem.get();
	}

	@Override
	public ResourceLocation getTexture() {
		return this.texture;
	}

	@Override
	public net.minecraft.resources.ResourceLocation getTextureReversed() {
		return this.hasReversedTexture ? this.textureReversed : this.texture;
	}

	@Override
	public net.minecraft.sounds.SoundEvent getEquipSound() {
		return this.equipSound.get();
	}
}
