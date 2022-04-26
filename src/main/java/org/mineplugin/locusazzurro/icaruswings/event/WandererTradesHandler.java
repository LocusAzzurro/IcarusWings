package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.utils.IWLazy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@EventBusSubscriber
public class WandererTradesHandler {
	
	private final static VillagerTrades.ItemListing zephirEssenceTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.zephirEssence.get()), 16, 1, 1, 1, 1.0f);
	private static final VillagerTrades.ItemListing amphoraTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.amphora.get()), 4, 1, 2, 1, 1.0f);
	private static final VillagerTrades.ItemListing goldenFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.goldenFeather.get()), 6, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing anemoneTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.anemone.get()), 8, 1, 2, 1, 1.0f);
	private static final VillagerTrades.ItemListing greekFireTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.greekFireBucket.get()), 18, 1, 1, 1, 1.0f);
	
	private static final VillagerTrades.ItemListing redFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.redFeather.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing blueFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.blueFeather.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing cyanFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.cyanFeather.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing greenFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.greenFeather.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing grayFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.grayFeather.get()), 2, 1, 3, 1, 1.0f);

	@SubscribeEvent
	public static void onWandererTradesInit(WandererTradesEvent event) {
		List<VillagerTrades.ItemListing> rareList = event.getRareTrades();
		List<VillagerTrades.ItemListing> commonList = event.getGenericTrades();
		commonList.addAll(Arrays.asList(redFeatherTrade, blueFeatherTrade, cyanFeatherTrade, greenFeatherTrade, grayFeatherTrade));
		rareList.addAll(Arrays.asList(zephirEssenceTrade, amphoraTrade, goldenFeatherTrade, anemoneTrade, greekFireTrade));
	}
	
	static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
		private final IWLazy<ItemStack> itemStack;
		private final int emeraldCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public ItemsForEmeraldsTrade(Supplier<ItemStack> p_i50532_1_, int p_i50532_2_, int p_i50532_3_, int p_i50532_4_,
				int p_i50532_5_, float p_i50532_6_) {
			this.itemStack = IWLazy.of(p_i50532_1_);
			this.emeraldCost = p_i50532_2_;
			this.numberOfItems = p_i50532_3_;
			this.maxUses = p_i50532_4_;
			this.villagerXp = p_i50532_5_;
			this.priceMultiplier = p_i50532_6_;
		}

		@Override
		public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
			return new MerchantOffer(
					new ItemStack(Items.EMERALD, this.emeraldCost),
					new ItemStack(this.itemStack.get().getItem(), this.numberOfItems), 
					this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

}
