package org.mineplugin.locusazzurro.icaruswings.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;
import org.mineplugin.locusazzurro.icaruswings.utils.BindList;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class MobLootHandler {
	
	@SubscribeEvent
	public static void onMobKill(LivingDropsEvent event)
	{	
		Entity entity = event.getEntity();
		int looting = event.getLootingLevel();
		
		List<Pair<Supplier<ItemStack>, Float>> tables = new ArrayList<>();
		List<ItemStack> stacks = new ArrayList<>();
		
		if (event.getSource().getEntity() instanceof PlayerEntity) {
		LOOTREF.find(entity.getClass()).forEach((key, val) -> tables.add(val));
		
		for (Pair<Supplier<ItemStack>, Float> i: tables) {
			if (Math.random() < i.getRight() * Math.pow(1.2, looting)) { stacks.add(i.getLeft().get()); }
		}
		stacks.forEach( i -> event.getDrops().add(new ItemEntity(entity.level, entity.getX(), entity.getY(), entity.getZ(), i)));
		}
    }
	
	private static final BindList<Class<? extends Entity>, Pair<Supplier<ItemStack>, Float>> LOOTREF = new BindList<>();
	
	static {
		
		LOOTREF.setPayload(Pair.of(() -> {return new ItemStack(ItemRegistry.zephirEssence.get());}, 0.1f)).setInto(BatEntity.class);
		LOOTREF.setPayload(Pair.of(() -> {return new ItemStack(ItemRegistry.zephirEssence.get());}, 0.1f)).setInto(PhantomEntity.class);
		LOOTREF.setPayload(Pair.of(() -> {return new ItemStack(ItemRegistry.netherEssence.get());}, 0.1f)).setInto(WitherSkeletonEntity.class);
		LOOTREF.setPayload(Pair.of(() -> {return new ItemStack(ItemRegistry.netherEssence.get());}, 0.05f)).setInto(BlazeEntity.class);
		LOOTREF.setPayload(Pair.of(() -> {return new ItemStack(ItemRegistry.voidEssence.get());}, 0.2f)).setInto(ShulkerEntity.class);
		LOOTREF.setPayload(Pair.of(() -> {return new ItemStack(ItemRegistry.voidEssence.get());}, 0.05f)).setInto(EndermanEntity.class);
	}
	
}
