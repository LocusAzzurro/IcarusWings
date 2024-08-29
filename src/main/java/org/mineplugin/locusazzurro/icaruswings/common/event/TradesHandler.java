package org.mineplugin.locusazzurro.icaruswings.common.event;

import com.google.common.base.Suppliers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber
public class TradesHandler {

	private final static VillagerTrades.ItemListing ZEPHIR_ESSENCE_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.ZEPHIR_ESSENCE.get()), 16, 1, 1, 2, 1.0f);
	private static final VillagerTrades.ItemListing AMPHORA_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.AMPHORA.get()), 4, 1, 2, 1, 1.0f);
	private static final VillagerTrades.ItemListing GOLDEN_FEATHER_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GOLDEN_FEATHER.get()), 6, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing ANEMONE_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.ANEMONE.get()), 8, 1, 2, 2, 1.0f);
	private static final VillagerTrades.ItemListing GREEK_FIRE_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GREEK_FIRE_BUCKET.get()), 18, 1, 1, 2, 1.0f);
	
	private static final VillagerTrades.ItemListing RED_FEATHER_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.RED_FEATHER.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing BLUE_FEATHER_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.BLUE_FEATHER.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing CYAN_FEATHER_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.CYAN_FEATHER.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing GREEN_FEATHER_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GREEN_FEATHER.get()), 2, 1, 3, 1, 1.0f);
	private static final VillagerTrades.ItemListing GRAY_FEATHER_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GRAY_FEATHER.get()), 2, 1, 3, 1, 1.0f);

	private static final VillagerTrades.ItemListing FLAX_SEEDS_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.FLAX_SEEDS.get()), 4, 2, 2, 1, 1.0f);

	private static final VillagerTrades.ItemListing LINEN_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.LINEN.get()), 4, 1, 5, 1, 1.0f);

	private static final VillagerTrades.ItemListing GOLDEN_FLEECE_TRADE = new ItemsForEmeraldsTrade(
			() -> new ItemStack(ItemRegistry.GOLDEN_FLEECE.get()), 20, 1, 1, 3, 1.0f);

	@SubscribeEvent
	public static void onWandererTradesInit(WandererTradesEvent event) {
		List<VillagerTrades.ItemListing> rareList = event.getRareTrades();
		List<VillagerTrades.ItemListing> commonList = event.getGenericTrades();
		commonList.addAll(Arrays.asList(RED_FEATHER_TRADE, BLUE_FEATHER_TRADE, CYAN_FEATHER_TRADE, GREEN_FEATHER_TRADE, GRAY_FEATHER_TRADE));
		rareList.addAll(Arrays.asList(ZEPHIR_ESSENCE_TRADE, AMPHORA_TRADE, GOLDEN_FEATHER_TRADE, ANEMONE_TRADE, GREEK_FIRE_TRADE));
	}

	@SubscribeEvent
	public static void onVillagerTradesInit(VillagerTradesEvent event){
		VillagerProfession profession = event.getType();
		Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
		if (profession == VillagerProfession.FARMER){
			trades.get(2).add(FLAX_SEEDS_TRADE);
		}
		if (profession == VillagerProfession.LEATHERWORKER){
			trades.get(2).add(LINEN_TRADE);
		}
		if (profession == VillagerProfession.SHEPHERD){
			trades.get(4).add(GOLDEN_FLEECE_TRADE);
		}
		if (profession == VillagerProfession.MASON){
			trades.get(2).add(AMPHORA_TRADE);
		}
		if (profession == VillagerProfession.FLETCHER){
			trades.get(3).addAll(Arrays.asList(RED_FEATHER_TRADE, BLUE_FEATHER_TRADE, CYAN_FEATHER_TRADE, GREEN_FEATHER_TRADE, GRAY_FEATHER_TRADE));
		}
		if (profession == VillagerProfession.WEAPONSMITH){
			trades.get(5).add(GREEK_FIRE_TRADE);
		}
	}


	static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
		private final Supplier<ItemStack> itemStack;
		private final int emeraldCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public ItemsForEmeraldsTrade(Supplier<ItemStack> forSale, int emeraldCost, int numItems, int maxUses,
				int xp, float mul) {
			this.itemStack = Suppliers.memoize(forSale::get);
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
