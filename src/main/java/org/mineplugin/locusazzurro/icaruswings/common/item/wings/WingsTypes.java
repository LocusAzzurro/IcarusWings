package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.Supplier;

public enum WingsTypes implements WingsType {

	FEATHER("feather", 100, ItemRegistry.FEATHER_BUNCH,false, SoundRegistry.WINGS_EQUIP_FEATHER),
	FEATHER_COLORED("colored_feather", 150, ItemRegistry.COLORED_FEATHER_BUNCH,false, SoundRegistry.WINGS_EQUIP_FEATHER),
	FEATHER_GOLDEN("golden_feather", 200, ItemRegistry.GOLDEN_FEATHER_BUNCH,false, SoundRegistry.WINGS_EQUIP_FEATHER),
	SYNAPSE_ALPHA("synapse_alpha", 500, ItemRegistry.SYNAPSE_WINGS_RECHARGER,true, SoundRegistry.WINGS_EQUIP_SYNAPSE),
	SYNAPSE_BETA("synapse_beta", 450, ItemRegistry.SYNAPSE_WINGS_RECHARGER,true, SoundRegistry.WINGS_EQUIP_SYNAPSE),
	SYNAPSE_DELTA("synapse_delta", 400, ItemRegistry.SYNAPSE_WINGS_RECHARGER,true, SoundRegistry.WINGS_EQUIP_SYNAPSE),
	SYNAPSE_EPSILON("synapse_epsilon", 420, ItemRegistry.SYNAPSE_WINGS_RECHARGER,true, SoundRegistry.WINGS_EQUIP_SYNAPSE),
	SYNAPSE_ZETA("synapse_zeta", 440, ItemRegistry.SYNAPSE_WINGS_RECHARGER,true, SoundRegistry.WINGS_EQUIP_SYNAPSE),
	SYNAPSE_THETA("synapse_theta", 500, ItemRegistry.SYNAPSE_WINGS_RECHARGER,true, SoundRegistry.WINGS_EQUIP_SYNAPSE),
	PAPER("paper", 10, () -> Items.PAPER,false, SoundRegistry.WINGS_EQUIP_PAPER),
	MAGIC("magic", 450, ItemRegistry.MAGIC_MEMBRANE,false, SoundRegistry.WINGS_EQUIP_MAGIC),
	PHI_STONE("philosopher_stone", 495, ItemRegistry.MAGIC_MEMBRANE,false, SoundRegistry.WINGS_EQUIP_MAGIC);
	
	private final String name;
	private final int durability;
	private final Supplier<Item> repairItem;
	private final boolean hasReversedTexture;
	private final ResourceLocation texture;
	private final ResourceLocation textureReversed;
	private final Holder<SoundEvent> equipSound;

	WingsTypes(String name, int durability, Supplier<Item> repairItem, boolean hasReverse, Holder<SoundEvent> equipSound) {
		this.name = name;
		this.durability = durability;
		this.repairItem = Suppliers.memoize(repairItem::get);
		this.hasReversedTexture = hasReverse;
		this.texture = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/" + name + "_wings.png");
		this.textureReversed = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/" + name + "_wings_reversed.png");
		this.equipSound = equipSound;
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
	public ResourceLocation getTextureReversed() {
		return this.hasReversedTexture ? this.textureReversed : this.texture;
	}

	@Override
	public Holder<SoundEvent> getEquipSound() {
		return this.equipSound;
	}
}
