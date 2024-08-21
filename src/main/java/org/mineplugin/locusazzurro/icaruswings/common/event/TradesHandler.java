package org.mineplugin.locusazzurro.icaruswings.common.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.bus.api.SubscribeEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.util.IWLazy;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber
public class TradesHandler {

	private final static VillagerTrades.ItemListing zephirEssenceTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.ZEPHIR_ESSENCE.get()), 16, 1, 1, 2, 1.0f);
	private static final VillagerTrades.ItemListing amphoraTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.AMPHORA.get()), 4, 1, 2, 1, 1.0f);
	private static final VillagerTrades.ItemListing goldenFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GOLDEN_FEATHER.get()), 6, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing anemoneTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.ANEMONE.get()), 8, 1, 2, 2, 1.0f);
	private static final VillagerTrades.ItemListing greekFireTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GREEK_FIRE_BUCKET.get()), 18, 1, 1, 2, 1.0f);
	
	private static final VillagerTrades.ItemListing redFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.RED_FEATHER.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing blueFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.BLUE_FEATHER.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing cyanFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.CYAN_FEATHER.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing greenFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GREEN_FEATHER.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing grayFeatherTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GRAY_FEATHER.get()), 2, 1, 3, 1, 1.0f);

	private static final VillagerTrades.ItemListing flaxSeedsTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.FLAX_SEEDS.get()), 4, 2, 2, 1, 1.0f);

	private static final VillagerTrades.ItemListing linenTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.LINEN.get()), 4, 1, 5, 1, 1.0f);

	private static final VillagerTrades.ItemListing goldenFleeceTrade = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GOLDEN_FLEECE.get()), 20, 1, 1, 3, 1.0f);

	@SubscribeEvent
	public static void onWandererTradesInit(WandererTradesEvent event) {
		List<VillagerTrades.ItemListing> rareList = event.getRareTrades();
		List<VillagerTrades.ItemListing> commonList = event.getGenericTrades();
		commonList.addAll(Arrays.asList(redFeatherTrade, blueFeatherTrade, cyanFeatherTrade, greenFeatherTrade, grayFeatherTrade));
		rareList.addAll(Arrays.asList(zephirEssenceTrade, amphoraTrade, goldenFeatherTrade, anemoneTrade, greekFireTrade));
	}

	@SubscribeEvent
	public static void onVillagerTradesInit(VillagerTradesEvent event){
		VillagerProfession profession = event.getType();
		Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
		if (profession == VillagerProfession.FARMER){
			trades.get(2).add(flaxSeedsTrade);
		}
		if (profession == VillagerProfession.LEATHERWORKER){
			trades.get(2).add(linenTrade);
		}
		if (profession == VillagerProfession.SHEPHERD){
			trades.get(4).add(goldenFleeceTrade);
		}
		if (profession == VillagerProfession.MASON){
			trades.get(2).add(amphoraTrade);
		}
		if (profession == VillagerProfession.FLETCHER){
			trades.get(3).addAll(Arrays.asList(redFeatherTrade, blueFeatherTrade, cyanFeatherTrade, greenFeatherTrade, grayFeatherTrade));
		}
		if (profession == VillagerProfession.WEAPONSMITH){
			trades.get(5).add(greekFireTrade);
		}
	}


	static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
		private final IWLazy<ItemStack> itemStack;
		private final int emeraldCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public ItemsForEmeraldsTrade(Supplier<ItemStack> forSale, int emeraldCost, int numItems, int maxUses,
				int xp, float mul) {
			this.itemStack = IWLazy.of(forSale);
			this.emeraldCost = emeraldCost;
			this.numberOfItems = numItems;
			this.maxUses = maxUses;
			this.villagerXp = xp;
			this.priceMultiplier = mul;
		}

		@Override
		public MerchantOffer getOffer(Entity p_221182_1_, RandomSource p_221182_2_) {
			return new MerchantOffer(
					new ItemCost(Items.EMERALD, this.emeraldCost),
					new ItemStack(this.itemStack.get().getItem(), this.numberOfItems), 
					this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

}
