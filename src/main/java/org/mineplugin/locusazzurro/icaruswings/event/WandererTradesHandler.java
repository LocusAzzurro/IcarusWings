package org.mineplugin.locusazzurro.icaruswings.event;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.LazyValue;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class WandererTradesHandler {
	
	private static VillagerTrades.ITrade zephirEssenceTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.zephirEssence.get());}, 16, 1, 1, 1, 1.0f);
	private static VillagerTrades.ITrade amphoraTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.amphora.get());}, 4, 1, 2, 1, 1.0f);
	private static VillagerTrades.ITrade goldenFeatherTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.goldenFeather.get());}, 6, 1, 3, 1, 1.0f);
	
	
	private static VillagerTrades.ITrade redFeatherTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.redFeather.get());}, 2, 1, 3, 1, 1.0f);
	private static VillagerTrades.ITrade blueFeatherTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.blueFeather.get());}, 2, 1, 3, 1, 1.0f);
	private static VillagerTrades.ITrade cyanFeatherTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.cyanFeather.get());}, 2, 1, 3, 1, 1.0f);
	private static VillagerTrades.ITrade greenFeatherTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.greenFeather.get());}, 2, 1, 3, 1, 1.0f);
	private static VillagerTrades.ITrade grayFeatherTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.grayFeather.get());}, 2, 1, 3, 1, 1.0f);
	
	@SubscribeEvent
	public static void onWandererTradesInit(WandererTradesEvent event) {
		List<ITrade> rareList = event.getRareTrades();
		List<ITrade> commonList = event.getGenericTrades();
		commonList.addAll(Arrays.asList(redFeatherTrade, blueFeatherTrade, cyanFeatherTrade, greenFeatherTrade, grayFeatherTrade));
		rareList.addAll(Arrays.asList(zephirEssenceTrade, amphoraTrade, goldenFeatherTrade));
	}
	
	static class ItemsForEmeraldsTrade implements VillagerTrades.ITrade {
		private final LazyValue<ItemStack> itemStack;
		private final int emeraldCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public ItemsForEmeraldsTrade(Supplier<ItemStack> p_i50532_1_, int p_i50532_2_, int p_i50532_3_, int p_i50532_4_,
				int p_i50532_5_, float p_i50532_6_) {
			this.itemStack = new LazyValue<>(p_i50532_1_);
			this.emeraldCost = p_i50532_2_;
			this.numberOfItems = p_i50532_3_;
			this.maxUses = p_i50532_4_;
			this.villagerXp = p_i50532_5_;
			this.priceMultiplier = p_i50532_6_;
		}

		public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
			return new MerchantOffer(
					new ItemStack(Items.EMERALD, this.emeraldCost),
					new ItemStack(this.itemStack.get().getItem(), this.numberOfItems), 
					this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

}
