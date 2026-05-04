package org.mineplugin.locusazzurro.icaruswings.client.render.models.properties;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;

public record SpearThrowingProperty() implements ConditionalItemModelProperty {
    public static final SpearThrowingProperty INSTANCE = new SpearThrowingProperty();
    public static final MapCodec<SpearThrowingProperty> MAP_CODEC = MapCodec.unit(INSTANCE);

    @Override
    public boolean get(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner, int seed, ItemDisplayContext displayContext) {
        if (owner != null) {
            if (!owner.isUsingItem()) {
                return false;
            }
            ItemStack using = owner.getUseItem();
            return ItemStack.isSameItemSameComponents(using, itemStack) || Boolean.TRUE.equals(itemStack.get(DataComponentRegistry.THROWING));
        }
        return Boolean.TRUE.equals(itemStack.get(DataComponentRegistry.THROWING));
    }

    @Override
    public MapCodec<SpearThrowingProperty> type() {
        return MAP_CODEC;
    }
}
