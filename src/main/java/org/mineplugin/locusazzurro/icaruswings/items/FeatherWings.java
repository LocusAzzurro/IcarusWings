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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class FeatherWings extends ElytraItem{
	
	public FeatherWingsType type;
	public FeatherWings(FeatherWingsType type) {
		super(new Properties().tab(ModGroup.itemGroup).durability(type.getDurability()));
		this.type = type;
	}
	
	public FeatherWings() {
		super(new Properties().tab(ModGroup.itemGroup).durability(FeatherWingsType.FEATHER.getDurability()));
		this.type = FeatherWingsType.FEATHER;
	}
	
	public FeatherWingsType getType() {
		return this.type;
	}
	
	public static enum FeatherWingsType {
		FEATHER(100),
		FEATHER_COLORED(150),
		FEATHER_GOLDEN(200);
		
		private final int durability;
		private FeatherWingsType(int durability) {
			this.durability = durability;
		}
		
		public int getDurability()
		{
			return this.durability;
		}
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
	
	//speed mod test
	@SubscribeEvent (priority = EventPriority.LOW)
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		if (player.isFallFlying()) {
            Vector3d lookAngle = player.getLookAngle();
            double d0 = 1.5D;
            double d1 = 0.1D;
            Vector3d flyAngle = player.getDeltaMovement();
            player.setDeltaMovement(flyAngle.add(
            		lookAngle.x * 0.1D + (lookAngle.x * 1.5D - flyAngle.x) * 0.5D,
            		lookAngle.y * 0.1D + (lookAngle.y * 1.5D - flyAngle.y) * 0.5D,
            		lookAngle.z * 0.1D + (lookAngle.z * 1.5D - flyAngle.z) * 0.5D));
        }
	}
	
}
