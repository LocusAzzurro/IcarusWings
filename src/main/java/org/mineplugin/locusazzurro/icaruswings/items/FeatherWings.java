package org.mineplugin.locusazzurro.icaruswings.items;

import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.ModGroup;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class FeatherWings extends ElytraItem{
	public FeatherWings() {
		super(new Properties().tab(ModGroup.itemGroup).durability(100));
	}
	
	@Nullable
	@Override
    public EquipmentSlotType getEquipmentSlot(ItemStack stack)
    {
        return EquipmentSlotType.CHEST;
    }
	
	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
	    return true;
	}
	//TODO: disable in the end
	@Override
	public boolean isValidRepairItem(ItemStack p_82789_1_, ItemStack p_82789_2_) {
		//return p_82789_2_.getItem() == Items.PHANTOM_MEMBRANE;
		return false;
	}
	
	@Override
	public boolean elytraFlightTick(ItemStack stack, net.minecraft.entity.LivingEntity entity, int flightTicks) {
	    if (!entity.level.isClientSide && (flightTicks + 1) % 20 == 0) {
	    	if (entity instanceof PlayerEntity) {
	    		int dmg = 1;
	    		int y = (int)entity.getY();
	    		dmg = Math.max( y >> 6, 1 ) + (Math.random() * 64 < (y & 63) ? 1 : 0);
	    				
	    	    stack.hurtAndBreak(dmg, entity, e -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.CHEST));
	    	    
	    	}
	    	//TODO: penalty in nether (5 dura per tick + fire)
	    }
	    return true;
	}
	
}
