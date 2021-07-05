package org.mineplugin.locusazzurro.icaruswings.event;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyValue;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class WandererTradesHandler {
	
	private static VillagerTrades.ITrade zephirEssenceTrade = new ItemsForEmeraldsTrade(
			() -> {return new ItemStack(ItemRegistry.zephirEssence.get());}, 20, 1, 1, 1, 1.0f);
	
	@SubscribeEvent
	public static void onWandererTradesInit(WandererTradesEvent event) {
		List<ITrade> rareList = event.getRareTrades();
		List<ITrade> commonList = event.getGenericTrades();
		rareList.add(zephirEssenceTrade);
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
