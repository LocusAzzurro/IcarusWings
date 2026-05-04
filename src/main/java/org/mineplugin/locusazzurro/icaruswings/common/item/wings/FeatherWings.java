package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public class FeatherWings extends AbstractWings {

	public FeatherWings(WingsTypes type) {
		super(type);
	}

	public FeatherWings(WingsTypes type, Item.Properties properties) {
		super(type, properties);
	}

	@Deprecated
	public FeatherWings() {
		this(WingsTypes.FEATHER);
	}

	@Override
	public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
		return entity.level().dimension() != Level.END;
	}

	@Override
	public void elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
		if (entity.level().isClientSide()) {
			return;
		}
		if ((flightTicks + 1) % 20 == 0) {
			stack.hurtAndBreak(getFlightDamage(entity), entity, EquipmentSlot.CHEST);
		}
	}

	public int getFlightDamage(LivingEntity entity) {
		if (entity.level().dimension() == Level.NETHER) {
			if (this.getType() != WingsTypes.FEATHER_GOLDEN) {
				entity.setRemainingFireTicks(10);
			}
			return 5;
		}
		int y = (int)entity.getY();
		return randomRound(Math.max(1, y / 64.0), entity.level().getRandom());
	}

	int randomRound(double value, RandomSource random) {
		int intValue = (int)Math.floor(value);
		if (random.nextDouble() < value - intValue) intValue++;
		return intValue;
	}


}
