package org.mineplugin.locusazzurro.icaruswings.common.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.function.Supplier;

public class ModAddItemLootModifier extends LootModifier {

    private final Item item;

    public static Supplier<MapCodec<ModAddItemLootModifier>> GLM_CODEC = Suppliers.memoize(() ->
        RecordCodecBuilder.mapCodec(instance -> ModAddItemLootModifier.codecStart(instance)
                .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(modifierInstance -> modifierInstance.item))
                .apply(instance, ModAddItemLootModifier::new)));

    public ModAddItemLootModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions){
            if (!condition.test(lootContext)){
                return generatedLoot;
            }
        }
        generatedLoot.add(new ItemStack(this.item));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return GLM_CODEC.get();
    }
}
