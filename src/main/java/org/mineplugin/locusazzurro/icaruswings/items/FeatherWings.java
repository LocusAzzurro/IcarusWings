package org.mineplugin.locusazzurro.icaruswings.items;

import javax.annotation.Nullable;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

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
public class FeatherWings extends ElytraItem {

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
		FEATHER(100), FEATHER_COLORED(150), FEATHER_GOLDEN(200);

		private final int durability;

		private FeatherWingsType(int durability) {
			this.durability = durability;
		}

		public int getDurability() {
			return this.durability;
		}
	}

	@Nullable
	@Override
	public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
		return EquipmentSlotType.CHEST;
	}

	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
		if (entity.level.dimension() == World.END) return false;
		return true;
	}

	// TODO: disable in the end
	@Override
	public boolean isValidRepairItem(ItemStack toBeRepaired, ItemStack repairMat) {
		Item item = toBeRepaired.getItem();
		if (item instanceof FeatherWings) {
			if (((FeatherWings) item).getType() == FeatherWingsType.FEATHER)
				return repairMat.getItem() == ItemRegistry.featherBunch.get();
			if (((FeatherWings) item).getType() == FeatherWingsType.FEATHER_COLORED)
				return repairMat.getItem() == ItemRegistry.coloredFeatherBunch.get();
			if (((FeatherWings) item).getType() == FeatherWingsType.FEATHER_GOLDEN)
				return repairMat.getItem() == ItemRegistry.goldenFeatherBunch.get();
		}
		return false;
	}

	@Override
	public boolean elytraFlightTick(ItemStack stack, net.minecraft.entity.LivingEntity entity, int flightTicks) {
		if (!entity.level.isClientSide() && (flightTicks + 1) % 20 == 0) {
			int dmg = 1;
			if (entity instanceof PlayerEntity) {
				if (entity.level.dimension() == World.NETHER) {
					dmg = 5;
					entity.setSecondsOnFire(10);
					stack.hurtAndBreak(dmg, entity,
							e -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.CHEST));
					return true;
				}
				int y = (int) entity.getY();
				dmg = Math.max(y >> 6, 1) + (Math.random() * 64 < (y & 63) ? 1 : 0);

				stack.hurtAndBreak(dmg, entity,
						e -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.CHEST));
				return true;
			}
			stack.hurtAndBreak(dmg, entity,
					e -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.CHEST));
			return true;
		}
		return true;
	}
	// TODO: penalty in nether (5 dura per tick + fire)

}
