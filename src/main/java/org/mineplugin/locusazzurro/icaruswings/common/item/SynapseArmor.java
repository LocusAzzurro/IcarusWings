package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.mineplugin.locusazzurro.icaruswings.registry.ArmorMaterialRegistry;

public class SynapseArmor extends ArmorItem {

	private final ArmorItem.Type type;

	public SynapseArmor(ArmorItem.Type type) {
		super(ArmorMaterialRegistry.SYNAPSE, type, new Properties().durability(type.getDurability(40)).rarity(Rarity.RARE));
		this.type = type;
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers() {
		ItemAttributeModifiers defaultAttributeModifiers = super.getDefaultAttributeModifiers();
		return switch (type) {
			case HELMET -> defaultAttributeModifiers
					.withModifierAdded(Attributes.LUCK, new AttributeModifier(ResourceLocation.withDefaultNamespace("luck"), 2.0f, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.HEAD);
			case CHESTPLATE -> defaultAttributeModifiers
					.withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("attack_damage"), 2.0f, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.CHEST);
			case LEGGINGS -> defaultAttributeModifiers
					.withModifierAdded(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("knockback_resistance"), 0.2f, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.LEGS);
			case BOOTS -> defaultAttributeModifiers
					.withModifierAdded(Attributes.MOVEMENT_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("movement_speed"), 0.05f, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.FEET);
			default -> defaultAttributeModifiers;
		};
	}

	@Override
	public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
		return (this.type == Type.HELMET);
	}
	
}
