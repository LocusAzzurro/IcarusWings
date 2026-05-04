package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {

    public static final ResourceKey<VillagerTrade> RED_FEATHER = key("shared/red_feather");
    public static final ResourceKey<VillagerTrade> BLUE_FEATHER = key("shared/blue_feather");
    public static final ResourceKey<VillagerTrade> CYAN_FEATHER = key("shared/cyan_feather");
    public static final ResourceKey<VillagerTrade> GREEN_FEATHER = key("shared/green_feather");
    public static final ResourceKey<VillagerTrade> GRAY_FEATHER = key("shared/gray_feather");

    public static final ResourceKey<VillagerTrade> ZEPHIR_ESSENCE = key("wandering_trader/uncommon/zephir_essence");
    public static final ResourceKey<VillagerTrade> AMPHORA = key("shared/amphora");
    public static final ResourceKey<VillagerTrade> GOLDEN_FEATHER = key("wandering_trader/uncommon/golden_feather");
    public static final ResourceKey<VillagerTrade> ANEMONE = key("wandering_trader/uncommon/anemone");
    public static final ResourceKey<VillagerTrade> GREEK_FIRE_BUCKET = key("shared/greek_fire_bucket");

    public static final ResourceKey<VillagerTrade> FLAX_SEEDS = key("farmer/2/flax_seeds");
    public static final ResourceKey<VillagerTrade> LINEN = key("leatherworker/2/linen");
    public static final ResourceKey<VillagerTrade> GOLDEN_FLEECE = key("shepherd/4/golden_fleece");

    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        registerForEmeralds(context, RED_FEATHER, ItemRegistry.RED_FEATHER.get(), 2, 1, 3, 1, 1.0F);
        registerForEmeralds(context, BLUE_FEATHER, ItemRegistry.BLUE_FEATHER.get(), 2, 1, 3, 1, 1.0F);
        registerForEmeralds(context, CYAN_FEATHER, ItemRegistry.CYAN_FEATHER.get(), 2, 1, 3, 1, 1.0F);
        registerForEmeralds(context, GREEN_FEATHER, ItemRegistry.GREEN_FEATHER.get(), 2, 1, 3, 1, 1.0F);
        registerForEmeralds(context, GRAY_FEATHER, ItemRegistry.GRAY_FEATHER.get(), 2, 1, 3, 1, 1.0F);

        registerForEmeralds(context, ZEPHIR_ESSENCE, ItemRegistry.ZEPHIR_ESSENCE.get(), 16, 1, 1, 2, 1.0F);
        registerForEmeralds(context, AMPHORA, ItemRegistry.AMPHORA.get(), 4, 1, 2, 1, 1.0F);
        registerForEmeralds(context, GOLDEN_FEATHER, ItemRegistry.GOLDEN_FEATHER.get(), 6, 1, 3, 1, 1.0F);
        registerForEmeralds(context, ANEMONE, ItemRegistry.ANEMONE.get(), 8, 1, 2, 2, 1.0F);
        registerForEmeralds(context, GREEK_FIRE_BUCKET, ItemRegistry.GREEK_FIRE_BUCKET.get(), 18, 1, 1, 2, 1.0F);

        registerForEmeralds(context, FLAX_SEEDS, ItemRegistry.FLAX_SEEDS.get(), 4, 2, 2, 1, 1.0F);
        registerForEmeralds(context, LINEN, ItemRegistry.LINEN.get(), 4, 1, 5, 1, 1.0F);
        registerForEmeralds(context, GOLDEN_FLEECE, ItemRegistry.GOLDEN_FLEECE.get(), 20, 1, 1, 3, 1.0F);
    }

    private static void registerForEmeralds(
            BootstrapContext<VillagerTrade> context,
            ResourceKey<VillagerTrade> key,
            Item item,
            int emeraldCost,
            int amount,
            int maxUses,
            int xp,
            float priceMultiplier
    ) {
        context.register(
                key,
                new VillagerTrade(
                        new TradeCost(Items.EMERALD, emeraldCost),
                        new ItemStackTemplate(item, amount),
                        maxUses,
                        xp,
                        priceMultiplier,
                        Optional.empty(),
                        List.of()
                )
        );
    }

    private static ResourceKey<VillagerTrade> key(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, path));
    }
}
