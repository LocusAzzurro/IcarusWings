package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.commons.lang3.tuple.Pair;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.BindList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber
public class MobLootHandler {
	
	@SubscribeEvent
	public static void onMobKill(LivingDropsEvent event)
	{	
		Entity entity = event.getEntity();
		int looting = event.getLootingLevel();
		
		List<Pair<Supplier<net.minecraft.world.item.ItemStack>, Float>> tables = new ArrayList<>();
		List<net.minecraft.world.item.ItemStack> stacks = new ArrayList<>();
		
		if (event.getSource().getEntity() instanceof Player) {
		LOOTREF.find(entity.getClass()).forEach((key, val) -> tables.add(val));
		
		for (Pair<Supplier<ItemStack>, Float> i: tables) {
			if (Math.random() < i.getRight() * Math.pow(1.2, looting)) { stacks.add(i.getLeft().get()); }
		}
		stacks.forEach( i -> event.getDrops().add(new ItemEntity(entity.level, entity.getX(), entity.getY(), entity.getZ(), i)));
		}
    }
	
	private static final BindList<Class<? extends net.minecraft.world.entity.Entity>, Pair<Supplier<net.minecraft.world.item.ItemStack>, Float>> LOOTREF = new BindList<>();
	
	static {
		
		LOOTREF.setPayload(Pair.of(() -> new net.minecraft.world.item.ItemStack(ItemRegistry.zephirEssence.get()), 0.1f)).setInto(Bat.class);
		LOOTREF.setPayload(Pair.of(() -> new net.minecraft.world.item.ItemStack(ItemRegistry.zephirEssence.get()), 0.1f)).setInto(Phantom.class);
		LOOTREF.setPayload(Pair.of(() -> new net.minecraft.world.item.ItemStack(ItemRegistry.netherEssence.get()), 0.1f)).setInto(WitherSkeleton.class);
		LOOTREF.setPayload(Pair.of(() -> new net.minecraft.world.item.ItemStack(ItemRegistry.netherEssence.get()), 0.05f)).setInto(Blaze.class);
		LOOTREF.setPayload(Pair.of(() -> new net.minecraft.world.item.ItemStack(ItemRegistry.voidEssence.get()), 0.2f)).setInto(Shulker.class);
		LOOTREF.setPayload(Pair.of(() -> new net.minecraft.world.item.ItemStack(ItemRegistry.voidEssence.get()), 0.05f)).setInto(EnderMan.class);
	}
	
}
