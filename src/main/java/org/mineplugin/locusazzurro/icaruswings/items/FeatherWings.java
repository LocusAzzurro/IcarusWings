package org.mineplugin.locusazzurro.icaruswings.items;

import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.data.WingsType;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class FeatherWings extends AbstractWings {

	public FeatherWings(WingsType type) {
		super(type);
	}
	
	@Deprecated
	public FeatherWings() {
		this(WingsType.FEATHER);
	}

	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
		if (entity.level.dimension() == World.END) return false;
		return true;
	}

	@Override
	public boolean elytraFlightTick(ItemStack stack, net.minecraft.entity.LivingEntity entity, int flightTicks) {
		if (!entity.level.isClientSide() && (flightTicks + 1) % 20 == 0) {
			int dmg = 1;
			if ((entity instanceof PlayerEntity) && (stack.getItem() instanceof FeatherWings)) {
				if (entity.level.dimension() == World.NETHER) {
					dmg = 5;
					if(((FeatherWings)stack.getItem()).getType() != WingsType.FEATHER_GOLDEN)
					entity.setSecondsOnFire(10);
				}
				else {
					int y = (int) entity.getY();
					dmg = Math.max(y >> 6, 1) + (Math.random() * 64 < (y & 63) ? 1 : 0);
				}
			}
			stack.hurtAndBreak(dmg, entity,	e -> e.broadcastBreakEvent(EquipmentSlotType.CHEST));
			return true;
		}
		return true;
	}

}
