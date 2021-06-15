package org.mineplugin.locusazzurro.icaruswings.items;

import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.items.FeatherWings.FeatherWingsType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class IkarosWings extends ElytraItem{
	public IkarosWings() {
		super(new Properties().tab(ModGroup.itemGroup).durability(40));
	}
	
	@Nullable
	@Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack)
    {
        return EquipmentSlotType.CHEST;
    }
	
	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
		return stack.getDamageValue() < stack.getMaxDamage() - 10;
	}
	
	//TODO: add core repair
	@Override
	public boolean isValidRepairItem(ItemStack p_82789_1_, ItemStack p_82789_2_) {
		//return p_82789_2_.getItem() == Items.PHANTOM_MEMBRANE;
		return false;
	}
	
	@SubscribeEvent (priority = EventPriority.LOW)
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		if (player.isFallFlying() && player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ItemRegistry.ikarosWings.get()) {
            Vector3d lookAngle = player.getLookAngle();
            Vector3d flyAngle = player.getDeltaMovement();
            player.setDeltaMovement(flyAngle.add(
            		lookAngle.x * 0.1D + (lookAngle.x * 1.5D - flyAngle.x) * 0.5D,
            		lookAngle.y * 0.1D + (lookAngle.y * 1.5D - flyAngle.y) * 0.5D,
            		lookAngle.z * 0.1D + (lookAngle.z * 1.5D - flyAngle.z) * 0.5D));
        }
	}
}
